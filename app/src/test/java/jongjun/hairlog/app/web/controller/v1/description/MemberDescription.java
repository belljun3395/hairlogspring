package jongjun.hairlog.app.web.controller.v1.description;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

public class MemberDescription {

	public static FieldDescriptor[] member() {
		return new FieldDescriptor[] {
			fieldWithPath("data").type(JsonFieldType.OBJECT).description("멤버"),
			fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("멤버 id"),
			fieldWithPath("data.email").type(JsonFieldType.STRING).description("멤버 이메일"),
			fieldWithPath("data.password").type(JsonFieldType.STRING).description("멤버 비밀번호"),
			fieldWithPath("data.name").type(JsonFieldType.STRING).description("멤버 이름"),
			fieldWithPath("data.sex").type(JsonFieldType.STRING).description("멤버 성별"),
			fieldWithPath("data.cycle").type(JsonFieldType.NUMBER).description("멤버 시술주기"),
			fieldWithPath("data.createAt").type(JsonFieldType.STRING).description("멤버 생성일자"),
		};
	}

	public static FieldDescriptor[] memberInfo() {
		return new FieldDescriptor[] {
			fieldWithPath("data").type(JsonFieldType.OBJECT).description("멤버"),
			fieldWithPath("data.email").type(JsonFieldType.STRING).description("멤버 이메일"),
			fieldWithPath("data.name").type(JsonFieldType.STRING).description("멤버 이름"),
			fieldWithPath("data.sex").type(JsonFieldType.STRING).description("멤버 성별"),
			fieldWithPath("data.cycle").type(JsonFieldType.NUMBER).description("멤버 주기"),
		};
	}

	public static FieldDescriptor[] memberId() {
		return new FieldDescriptor[] {
			fieldWithPath("data").type(JsonFieldType.NUMBER).description("멤버 id")
		};
	}

	public static FieldDescriptor[] loginMember() {
		return new FieldDescriptor[] {
			fieldWithPath("data").type(JsonFieldType.OBJECT).description("멤버"),
			fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("멤버 id"),
			fieldWithPath("data.name").type(JsonFieldType.STRING).description("멤버 이름"),
			fieldWithPath("data.token").type(JsonFieldType.OBJECT).description("멤버 토큰"),
			fieldWithPath("data.token.accessToken").type(JsonFieldType.STRING).description("멤버 엑세스 토큰"),
			fieldWithPath("data.token.refreshToken").type(JsonFieldType.STRING).description("멤버 리프레시 토큰"),
		};
	}

	public static FieldDescriptor[] refreshToken() {
		return new FieldDescriptor[] {
			fieldWithPath("data.accessToken").type(JsonFieldType.STRING).description("멤버 엑세스 토큰"),
			fieldWithPath("data.refreshToken").type(JsonFieldType.STRING).description("멤버 리프레시 토큰"),
		};
	}
}
