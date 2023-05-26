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

[POSTMAN DOCS LINK](https://documenter.getpostman.com/view/17873656/2s93kz551P)

<img width="389" alt="Hairlog_API목록" src="https://github.com/belljun3395/hairlog-react/assets/102807742/bc4a6349-fd76-4c89-99a7-1332cc2a980e">

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

### OpenApi Specification 기반 문서화

기존에 문서화를 위한 Swagger을 작성할 때 가장 문제가 되는 것은 Swagger을 위한 어노테이션이 코드 가독성을 해친다는 것 이었다.

이번에 활용한 restdocs-api-spec은 Controller Mock 테스트를 통해 OAS를 생성할 수 있고

이를 활용하여 swagger 문서 그리고 postman json을 생성할 수 있었다.

자동 문서화와 더불어 또 하나의 특징은 정확한 문서 작성을 돕는다는 점이다.

위의 언급한 바와 같이 테스트를 통해 OAS를 생성하기 때문에 정확한 타입 그리고 형식을 보장 할 수 있다.

