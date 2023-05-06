package jongjun.hairlog.app.web.controller.v1;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import jongjun.hairlog.app.AppMain;
import jongjun.hairlog.app.domain.model.member.Member;
import jongjun.hairlog.app.domain.model.member.MemberInfo;
import jongjun.hairlog.app.domain.model.member.Token;
import jongjun.hairlog.app.domain.usecase.member.DeleteMemberUseCase;
import jongjun.hairlog.app.domain.usecase.member.GetMemberUseCase;
import jongjun.hairlog.app.domain.usecase.member.GetTokenUseCase;
import jongjun.hairlog.app.domain.usecase.member.SaveMemberUseCase;
import jongjun.hairlog.app.domain.usecase.member.SignMemberUseCase;
import jongjun.hairlog.app.support.token.TokenGenerator;
import jongjun.hairlog.app.web.controller.request.member.MemberEditRequest;
import jongjun.hairlog.app.web.controller.request.member.MemberRequest;
import jongjun.hairlog.app.web.controller.request.member.SignMemberRequest;
import jongjun.hairlog.app.web.controller.response.SaveMemberResponse;
import jongjun.hairlog.app.web.controller.response.TokenResponse;
import jongjun.hairlog.app.web.controller.v1.description.Description;
import jongjun.hairlog.app.web.controller.v1.description.MemberDescription;
import jongjun.hairlog.data.enums.MemberSex;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles(value = "test")
@AutoConfigureRestDocs
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = AppMain.class)
class MemberControllerTest {

	private static final String EMAIL = "test@test.com";
	private static final String PASSWORD = "testPassword@";
	private static final String NAME = "testName";
	private static final Long CYCLE = 1L;
	private static final Long MEMBER_RETURN_ID = 1L;

	private static final String BASE_URL = "/api/v1/members";
	private static final String TAG = "Member";

	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;
	@Autowired private TokenGenerator tokenGenerator;
	@MockBean private SaveMemberUseCase saveMemberUseCase;
	@MockBean private GetMemberUseCase getMemberUseCase;
	@MockBean private SignMemberUseCase signMemberUseCase;
	@MockBean private GetTokenUseCase getTokenUseCase;
	@MockBean private DeleteMemberUseCase deleteMemberUseCase;

	@Test
	void addMember() throws Exception {
		MemberRequest request =
				MemberRequest.builder()
						.email(EMAIL)
						.password(PASSWORD)
						.name(NAME)
						.sex(MemberSex.M)
						.cycle(CYCLE)
						.build();

		when(saveMemberUseCase.execute(request)).thenReturn(MEMBER_RETURN_ID);

		String content = objectMapper.writeValueAsString(request);

		mockMvc
				.perform(post(BASE_URL, 0).content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"saveMember",
								resource(
										ResourceSnippetParameters.builder()
												.description("member 추가")
												.tag(TAG)
												.requestSchema(Schema.schema("MemberRequest"))
												.responseSchema(Schema.schema("MemberResponse"))
												.responseFields(Description.success(MemberDescription.saveMember()))
												.build())));
	}

	@Test
	void editMember() throws Exception {
		MemberEditRequest request =
				MemberEditRequest.builder()
						.id(MEMBER_RETURN_ID)
						.name(NAME + "edit")
						.cycle(CYCLE + 1L)
						.build();

		when(saveMemberUseCase.execute(request.getId(), request)).thenReturn(MEMBER_RETURN_ID);

		String content = objectMapper.writeValueAsString(request);

		mockMvc
				.perform(patch(BASE_URL, 0).content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"editMember",
								resource(
										ResourceSnippetParameters.builder()
												.description("member 수정")
												.tag(TAG)
												.requestSchema(Schema.schema("MemberEditRequest"))
												.responseSchema(Schema.schema("MemberEditResponse"))
												.responseFields(Description.success(MemberDescription.saveMember()))
												.build())));
	}

	@Test
	void deleteMember() throws Exception {

		when(deleteMemberUseCase.execute(MEMBER_RETURN_ID)).thenReturn(MEMBER_RETURN_ID);

		mockMvc
				.perform(
						delete(BASE_URL, 1)
								.param("id", MEMBER_RETURN_ID.toString())
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"deleteMember",
								resource(
										ResourceSnippetParameters.builder()
												.description("member 삭제")
												.tag(TAG)
												.requestSchema(Schema.schema("MemberDeleteRequest"))
												.responseSchema(Schema.schema("MemberDeleteResponse"))
												.responseFields(Description.success(MemberDescription.saveMember()))
												.build())));
	}

	@Test
	void login() throws Exception {
		SignMemberRequest request = SignMemberRequest.builder().email(EMAIL).password(PASSWORD).build();

		SaveMemberResponse response =
				SaveMemberResponse.builder()
						.id(MEMBER_RETURN_ID)
						.name(NAME)
						.tokenResponse(TokenResponse.from(tokenGenerator.generateToken(MEMBER_RETURN_ID)))
						.build();

		when(signMemberUseCase.execute(request)).thenReturn(response);

		String content = objectMapper.writeValueAsString(request);

		mockMvc
				.perform(
						post(BASE_URL + "/login", 0).content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"loginMember",
								resource(
										ResourceSnippetParameters.builder()
												.description("member 로그인")
												.tag(TAG)
												.requestSchema(Schema.schema("MemberLoginRequest"))
												.responseSchema(Schema.schema("MemberLoginResponse"))
												.responseFields(Description.success(MemberDescription.loginMember()))
												.build())));
	}

	@Test
	void readMember() throws Exception {
		Member returnMember =
				Member.builder()
						.id(MEMBER_RETURN_ID)
						.name(NAME)
						.sex(MemberSex.M)
						.cycle(CYCLE)
						.password(PASSWORD)
						.email(EMAIL)
						.createAt(LocalDateTime.now())
						.build();

		when(getMemberUseCase.execute(MEMBER_RETURN_ID)).thenReturn(returnMember);

		mockMvc
				.perform(
						get(BASE_URL, 1)
								.param("id", MEMBER_RETURN_ID.toString())
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"readMember",
								resource(
										ResourceSnippetParameters.builder()
												.description("Member 조회 | 멤버 id 기반")
												.tag(TAG)
												.responseSchema(Schema.schema("MemberResponse"))
												.responseFields(Description.success(MemberDescription.member()))
												.build())));
	}

	@Test
	void readMemberInfo() throws Exception {
		MemberInfo returnMemberInfo = MemberInfo.builder().name(NAME).email(EMAIL).build();

		when(getMemberUseCase.execute(EMAIL)).thenReturn(returnMemberInfo);

		mockMvc
				.perform(
						get(BASE_URL + "/info", 1)
								.param("email", EMAIL)
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"readMemberInfo",
								resource(
										ResourceSnippetParameters.builder()
												.description("Member 조회 | 멤버 email 기반")
												.tag(TAG)
												.responseSchema(Schema.schema("MemberResponse"))
												.responseFields(Description.success(MemberDescription.memberInfo()))
												.build())));
	}

	@Test
	void refreshToken() throws Exception {
		String request = tokenGenerator.generateToken(MEMBER_RETURN_ID).getRefreshToken();
		Token response = tokenGenerator.generateToken(MEMBER_RETURN_ID);

		when(getTokenUseCase.execute(request)).thenReturn(response);

		mockMvc
				.perform(
						post(BASE_URL + "/tokens", 0)
								.header("X-REFRESH-TOKEN", request)
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"refreshTokenMember",
								resource(
										ResourceSnippetParameters.builder()
												.description("Member 리프레시 토큰 갱신")
												.tag(TAG)
												.requestHeaders(
														headerWithName("X-REFRESH-TOKEN").description("put refreshToken here"))
												.responseSchema(Schema.schema("MemberResponseTokenResponse"))
												.responseFields(Description.success(MemberDescription.refreshToken()))
												.build())));
	}
}
