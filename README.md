# Hairlog-Spring

Hairlog-Spring은 [Econovation](https://econovation.kr/about)에서 2022년 1학기에 진행한 [기존 프로젝트](https://github.com/belljun3395/Hairlog)를 Spring 기반으로 전환한 프로젝트 입니다.

<br/>

### 프로젝트 환경을 구성하는 방법

```
#프로젝트를 다운 받습니다.

git clone https://github.com/belljun3395/hairlogspring.git
```

```
#프로젝트 환경을 구성합니다. (도커가 필요합니다.)

cd scripts/

./local-develop-env-reset
```

<br/>

## API 목록

[POSTMAN DOCS LINK](https://documenter.getpostman.com/view/17873656/2s93m8zLPq)

<img width="389" alt="Hairlog_API목록" src="https://github.com/belljun3395/hairlog-react/assets/102807742/bc4a6349-fd76-4c89-99a7-1332cc2a980e">

<br/>

## 쿼리 확인을 위한 테스트 코드 결과 확인

[테스트 코드 실행 결과 쿼리 링크](https://hairlog.jongjun.com/reports/data/index.html)

[테스트 코드 링크](https://github.com/belljun3395/hairlogspring/tree/main/data/src/test/java/jongjun/hairlog/data/repository/query)

<img width="1216" alt="스크린샷 2023-05-30 오전 2 30 20" src="https://github.com/belljun3395/hairlogspring/assets/102807742/6bc63a4d-bcb3-49cc-b17f-ba8d8b61bd1a">

확인하고 싶은 테스트 코드를 클릭하고

<img width="1025" alt="스크린샷 2023-05-30 오전 2 32 03" src="https://github.com/belljun3395/hairlogspring/assets/102807742/7b73497e-3c5e-46ee-8080-0b817f8ff866">

Standard Output 탭을 클릭하면 쿼리를 확인할 수 있다.

<br/>

## 프로젝트 구조

```
hairlog
| - app
| - data
| - resource
| - scripts
```

<br/>

### 특징

멀티 모듈 구조를 통해 App와 Data의 분리를 명확히 할 수 있다.

추가로 App에서 필요한 의존성과 Data에서 필요한 의존성이 다른데 **각 모듈의 의존성을 공통 gradle 그리고 각 모듈의 gradle을 통해 분리하여 관리할 수 있다.**

그리고 SpringBoot를 공부할 때 가장 많이 마주하는 MVC 패턴에서는 명확하지 않았던 **엔티티와 도메인의 경계를 명확히 해준다는 점** 역시 멀티 모듈의 특징이라 생각한다.

다만 Hairlog-Spring의 경우 단순 CRUD 기능의 프로젝트로 모듈 분리를 조금 단순화하였다.

_(단순화 하지 않은 모듈 분리를 확인하려면 [multimoduledemo](https://github.com/belljun3395/multimoduledemo)를 확인하면 좋을 것 같다.)_

<br/>

## 프로젝트 코드의 특징

<br/>

### 단순한 CRUD => Index, Query에 집중

해당 프로젝트의 경우 API 목록에서 확인 할 수 있는 대부분의 API가 단순 CRUD 기능이다.

그렇기에 이번 프로젝트에서는 Index, Query에 집중하여 기능을 구현하였다.

<br/>

#### Index

````sql
alter table designer_entity add fulltext index idx_ft_name (designer_name);
````

위의 sql문의 designer_entity 테이블의 designer_name 칼럼에 fulltext 인덱스를 거는 코드다.

이는 "추후 프런트에서 기록 작성 기능에서 디자이너 검색 기능을 추가한다면?"이라는 가정하에 적용한 인덱스이다.

이를 통해 like의 느린 성능을 개선할 수 있을 것이라 생각하였다.

##### 쿼리 성능 테스트

환경

500000개의 데이터
<img width="1015" alt="스크린샷 2023-06-01 오후 2 05 21" src="https://github.com/belljun3395/hairlogspring/assets/102807742/21fc5b29-9f2d-4c5a-9ea1-458182036471">

zzoong이라는 designer_name 값을 가진 id가 250000 ~ 270000, 350000 ~ 370000인 연속된 데이터

<img width="1004" alt="스크린샷 2023-06-01 오후 2 10 21" src="https://github.com/belljun3395/hairlogspring/assets/102807742/d28a7e7e-364d-4d19-b2ad-1b5ccf8743b3">

<img width="1006" alt="스크린샷 2023-06-01 오후 2 10 54" src="https://github.com/belljun3395/hairlogspring/assets/102807742/c36314b1-837b-45af-b4a7-816fc0a90f1c">

<img width="1004" alt="스크린샷 2023-06-01 오후 2 09 35" src="https://github.com/belljun3395/hairlogspring/assets/102807742/4394e952-2711-4a9c-b000-c6d1e2a187ca">

<img width="1006" alt="스크린샷 2023-06-01 오후 2 09 49" src="https://github.com/belljun3395/hairlogspring/assets/102807742/0681a768-1056-4b43-9963-9b89f015ea9d">

zzong 쿼리
```sql
select d.designer_id from designer_entity d where match(d.designer_name) against('*zzong*' in natural language mode);

select d.designer_id from designer_entity d where d.designer_name like '%zzong%';
```

실행 계획

<img width="1034" alt="스크린샷 2023-06-01 오후 2 08 24" src="https://github.com/belljun3395/hairlogspring/assets/102807742/ceec1763-ca4e-42ca-b1c3-eac0bf2f1e93">

결과

<img width="821" alt="스크린샷 2023-06-01 오후 2 18 06" src="https://github.com/belljun3395/hairlogspring/assets/102807742/d0e05dde-7567-4bd2-8246-87f5d81dd5a3">

<img width="958" alt="스크린샷 2023-06-01 오후 2 17 57" src="https://github.com/belljun3395/hairlogspring/assets/102807742/afc5e9bc-a846-4468-a299-5091bfa21f13">

```
fulltext index : 0.118 초
like : 0.107 초
```

예상치 못한 결과가 였는데 이를 통해 **찾아야하는 데이터의 수가 동일할 때는 fulltext index가 적용된 칼럼의 성능이 떨진다는 것을 확인 할 수 있었다.**

<br/>
그럼 zong이라는 designer_name 값을 가진 데이터를 추가하고 난 이후에 zong을 fulltext와 like로 조회해 본다면?

id가 450000 ~ 470000인 연속된 데이터 추가

<img width="996" alt="스크린샷 2023-06-01 오후 2 12 51" src="https://github.com/belljun3395/hairlogspring/assets/102807742/46f58bd5-c990-451a-9458-65a902e9ce04">

<img width="1005" alt="스크린샷 2023-06-01 오후 2 13 04" src="https://github.com/belljun3395/hairlogspring/assets/102807742/faf0c44c-7cbb-431d-adb1-12017714beaf">

쿼리

```sql
select d.designer_id from designer_entity d where match(d.designer_name) against('*zong*' in natural language mode);

select d.designer_id from designer_entity d where d.designer_name like '%zong%';
```

결과

<img width="949" alt="스크린샷 2023-06-01 오후 2 30 05" src="https://github.com/belljun3395/hairlogspring/assets/102807742/072f470d-f069-4bdc-b34c-5cc6addeb390">

<img width="828" alt="스크린샷 2023-06-01 오후 2 30 16" src="https://github.com/belljun3395/hairlogspring/assets/102807742/c3c4bba2-a66c-4718-898f-a4982542a234">

```
fulltext index : 20,001개 행, 0.110 초
like : 60,003개 행, 0.167 초
```

왜 다른 조회 결과가 나왔을까?

full text index는 단어별로 index를 만든다. (`WITH PARSER NGRAM`를 사용하면 글자별로 만들 수 있다.)

그렇기에 해당 index를 사용하는 full text index 쿼리는 **zong이라는 단어**만 조회할 수 있는 것이고

하지만 like 쿼리는 **zong이라는 글자가 포함되는 모든 데이터**를 조회하여 데이터 조회 결과가 달라진 것이다.

<br/>

지금까지는 연속된 데이터를 조회하였다면 이번에는 **랜덤한 순서의 데이터**를 조회해보자.

데이터 생성

```sql
update designer_entity d set d.designer_name = 'find me jong' where d.designer_id = 3;
update designer_entity d set d.designer_name = 'find me jong' where d.designer_id = 3040;
update designer_entity d set d.designer_name = 'find me jong' where d.designer_id = 30400;
update designer_entity d set d.designer_name = 'find me jong' where d.designer_id = 300400;
update designer_entity d set d.designer_name = 'find me jong' where d.designer_id = 300400;
```

쿼리

```sql
select d.designer_id from designer_entity d where match(d.designer_name) against('*find*' in natural language mode);

select d.designer_id from designer_entity d where d.designer_name like '%find%';
```

결과

<img width="962" alt="스크린샷 2023-06-01 오후 2 46 23" src="https://github.com/belljun3395/hairlogspring/assets/102807742/d675536f-ac1d-418e-adfb-e5600f060be1">

<img width="837" alt="스크린샷 2023-06-01 오후 2 46 32" src="https://github.com/belljun3395/hairlogspring/assets/102807742/f475f828-3f68-4c86-8a88-a0588bfd9806">

```
fulltext index : 0.012 초
like : 0.156 초
```

연속된 데이터에서의 결과에 비해 확연한 성능 차이를 보여주었다.

이 역시 fulltext index는 단어별로 생성되어 있는 index를 사용하기 때문에 차이를 보이는 것이다.

<br/>

마지막으로 NGRAM을 활용시 주의사항과 예제를 쿼리를 통해 확인해보자.

쿼리

```sql
select d.designer_id from designer_entity d where match(d.designer_name) against('*fi*' in natural language mode);

select d.designer_id from designer_entity d where match(d.designer_name) against('*nd*' in natural language mode);
```

결과

<img width="944" alt="스크린샷 2023-06-01 오후 3 06 30" src="https://github.com/belljun3395/hairlogspring/assets/102807742/872c930d-dabf-4a1f-9d1c-313d0d18bf2f">

`fi` 로 조회한 쿼리는 아무것도 조회되지 않았는데 이는 Stopwords 때문이다.

_자세한 것은 더 잘 작성한 [블로그](https://gongzza.github.io/database/mysql-fulltext-search/) 참고_

간단히 설명하면 Stopwords에 `i`가 포함되어 있어 `i`로 시작하거나 끝나는 단어는 인덱스로 저장해두지 않기에 조회되지 않는 것이다.

하지만 `nd`의 경우 Stopwords에 포함되어 있지 않기에 조회가 되는 것을 확인할 수 있다.

```
full text index with parser ngram : 4개 행, 0.005초
``` 

<br/>

#### Query

이번 프로젝트에서는 JPQL과 SQL을 혼합하여 작성하였다.

하지만 모든 Query의 목적은 **필요한 컬럼만 조회**해온다는 하나였다.

```java
private static final String MEMBER_FINDBYEMAIL_QUERY =
        "select new "
                + MEMBER_DTO_PACKAGE
                + ".MemberInfoDTO(m.email, m.name) from MemberEntity m where m.email = :email";
```

위의 Query는 이메일 정보를 통해 멤버 이름을 조회하는 JPQL이다.

기존 DataJPA를 활용하였다면 `findByEmail`과 같이 Query를 작성하여 필요한 컬럼만 조회하는 것이 아닌 모든 컬럼을 조회하였을 것이다.

하지만 위와 같이 필요한 정보만 조회하는 Query 작성을 통해 불필요한 정보 조회를 줄이고 성능 개선이 가능해 질 것이라 생각하였다.

<br/>

## 프로젝트 품질을 위한 시도

<br/>

### spotless

spotless는 여러 개발자가 협업하는 환경에서 코드 컨벤션을 지키고 쉽게 유지보수 하기 위해 사용하는 플러그인.

````java
@PostMapping("/login")
public ApiResponse<ApiResponse.SuccessBody<SaveMemberResponse>> login(@RequestBody SignMemberRequest signMemberRequest) {
    return ApiResponseGenerator.success(signMemberUseCase.execute(signMemberRequest), HttpStatus.CREATED);
}
````

````java
@PostMapping("/login")
public ApiResponse<ApiResponse.SuccessBody<SaveMemberResponse>>
    login(@RequestBody SignMemberRequest signMemberRequest) {
    return ApiResponseGenerator.success(signMemberUseCase.execute(signMemberRequest), HttpStatus.CREATED);
}
````

````java
@PostMapping("/login")
public ApiResponse<ApiResponse.SuccessBody<SaveMemberResponse>>
    login(@RequestBody SignMemberRequest signMemberRequest) {
    return ApiResponseGenerator.success(
        signMemberUseCase.execute(signMemberRequest),
        HttpStatus.CREATED);
}
````

위와 같이 다양한 코드 작성 스타일이 존재할 수 있다.


```java
@PostMapping("/login")
public ApiResponse<ApiResponse.SuccessBody<SaveMemberResponse>> login(
        @RequestBody SignMemberRequest signMemberRequest) {
    return ApiResponseGenerator.success(
            signMemberUseCase.execute(signMemberRequest), HttpStatus.CREATED);
}
```

하지만 spotless를 적용한다면 위와 같이 통일된 규칙을 적용한 코드로 포멧팅 해주기 때문에 모든 개발자가 같은 스타일의 코드를 읽을 수 있다는 장점이 있다.

<br/>

## 프로젝트 개발 환경을 위한 시도

<br/>

### flyway

docker 환경에서 mysql과 flyway를 활용하여 개발 환경을 구성하였다.

scripts 폴더 아래 스크립트에서 flyway를 통하 DB 마이그래이션을 진행한다.

```
./gradlew :data:flywayClean :data:flywayMigrate
```

<br/>

<img width="404" alt="스크린샷 2023-05-18 오전 12 19 41" src="https://github.com/belljun3395/hairlog-react/assets/102807742/2d3d8bcd-67be-4128-8890-eca5695f4711">

<br/>

DB의 변경 사항을 위의 사진처럼 버전 관리할 수 있다는 장점이 있었다.

```groovy
ext.profile = (!project.hasProperty('profile') || !profile) ? 'local' : profile
if (profile == "data") {
    print("data")
    flyway {
        url = "jdbc:mysql://localhost:13306/demodb?useSSL=false&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true"
        user = "root"
        password = "root"
        locations = ["filesystem:${file('migrationdata').absolutePath}"]
        encoding = 'UTF-8'
        outOfOrder = true
        validateOnMigrate = true
    }
} else {
    flyway {
        url = "jdbc:mysql://localhost:13306/demodb?useSSL=false&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true"
        user = "root"
        password = "root"
        locations = ["filesystem:${file('migration').absolutePath}"]
        encoding = 'UTF-8'
        outOfOrder = true
        validateOnMigrate = true
    }
}
```

그리고 위와 같이 profile에 따라 task를 다르게 지정한다면 상황에 따라 다른 데이터를 개발 환경 구성에서 선택할 수 있을 것이라 생각한다.

이번 프로젝트에서는 우선 테스터 유저를 위한 데이터 삽입을 위의 코드와 아래 사진과 같이 구성하였다.

<br/>

<img width="289" alt="스크린샷 2023-05-18 오전 12 24 02" src="https://github.com/belljun3395/hairlog-react/assets/102807742/f24c05c2-849e-4fcc-bbaf-0287e5829f1b">

<br/>

#### 수정 사항

기존 gradle을 활용한 설정에서 bean 설정으로 변경

**문제 사항**

```java
/**
 * Locations of migrations scripts. Can contain the special "{vendor}" placeholder to
 * use vendor-specific locations.
 */
private List<String> locations = new ArrayList<>(Collections.singletonList("classpath:db/migration"));
```

위의 코드를 보면 locations의 기본 값은 classpath:db/migration이고

``` java
public void setLocations(List<String> locations) {
    this.locations = locations;
}
```

setLocations이 있지만 시도해본 결과 변경이 되지 않는다.

그래서 이전의 gradle 설정을 통해 data 폴더로 분리한 방법을 사용하지 못하게 되었다.

### OpenApi Specification 기반 문서화

기존에 문서화를 위한 Swagger을 작성할 때 가장 문제가 되는 것은 Swagger을 위한 어노테이션이 코드 가독성을 해친다는 것 이었다.

이번에 활용한 restdocs-api-spec은 Controller Mock 테스트를 통해 OAS를 생성할 수 있고

이를 활용하여 swagger 문서 그리고 postman json을 생성할 수 있었다.

자동 문서화와 더불어 또 하나의 특징은 정확한 문서 작성을 돕는다는 점이다.

위의 언급한 바와 같이 테스트를 통해 OAS를 생성하기 때문에 정확한 타입 그리고 형식을 보장 할 수 있다.
