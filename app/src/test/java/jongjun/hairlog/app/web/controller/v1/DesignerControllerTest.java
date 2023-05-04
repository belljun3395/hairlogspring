package jongjun.hairlog.app.web.controller.v1;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import jongjun.hairlog.app.AppMain;
import jongjun.hairlog.app.domain.model.designer.Designer;
import jongjun.hairlog.app.domain.usecase.designer.GetDesignerUseCase;
import jongjun.hairlog.app.domain.usecase.designer.SaveDesignerUseCase;
import jongjun.hairlog.app.web.controller.request.designer.DesignerRequest;
import jongjun.hairlog.app.web.controller.v1.description.Description;
import jongjun.hairlog.app.web.controller.v1.description.DesignerDescription;
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
class DesignerControllerTest {

	private static String DESIGNER_NAME = "testDesignerName";
	private static String DESIGNER_SALON = "testDesignerSalon";
	private static Long MEMBER_ID = 1L;
	private static Long DESIGNER_RETURN_ID = 1L;

	private static final String BASE_URL = "/api/v1/designers";
	private static final String TAG = "Designer";

	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;

	@MockBean private SaveDesignerUseCase saveDesignerUseCase;
	@MockBean private GetDesignerUseCase getDesignerUseCase;

	@Test
	void addDesigner() throws Exception {
		DesignerRequest request =
				DesignerRequest.builder()
						.designerName(DESIGNER_NAME)
						.designerSalon(DESIGNER_SALON)
						.designerFav(true)
						.memberId(MEMBER_ID)
						.build();

		when(saveDesignerUseCase.execute(request)).thenReturn(DESIGNER_RETURN_ID);

		String content = objectMapper.writeValueAsString(request);

		mockMvc
				.perform(post(BASE_URL, 0).content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"saveDesigner",
								resource(
										ResourceSnippetParameters.builder()
												.description("designer 추가")
												.tag(TAG)
												.requestSchema(Schema.schema("DesignerRequest"))
												.responseSchema(Schema.schema("Designer"))
												.responseFields(Description.success(DesignerDescription.saveDesigner()))
												.build())));
	}

	@Test
	void readDesignerById() throws Exception {
		List<Designer> response = new ArrayList<>();
		response.add(
				Designer.builder()
						.id(DESIGNER_RETURN_ID)
						.designerName(DESIGNER_NAME)
						.designerSalon(DESIGNER_SALON)
						.designerFav(true)
						.build());

		when(getDesignerUseCase.execute(MEMBER_ID)).thenReturn(response);

		mockMvc
				.perform(
						get(BASE_URL, 1)
								.param("id", DESIGNER_RETURN_ID.toString())
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"readDesigner",
								resource(
										ResourceSnippetParameters.builder()
												.description("designer 조회 | 멤버 id 기반")
												.tag(TAG)
												.responseSchema(Schema.schema("DesignerResponse"))
												.responseFields(Description.success(DesignerDescription.designers()))
												.build())));
	}

	@Test
	void readDesignerByIdAndEmail() throws Exception {
		List<Designer> response = new ArrayList<>();
		response.add(
				Designer.builder()
						.id(DESIGNER_RETURN_ID)
						.designerName(DESIGNER_NAME)
						.designerSalon(DESIGNER_SALON)
						.designerFav(true)
						.build());

		when(getDesignerUseCase.execute(MEMBER_ID, DESIGNER_NAME)).thenReturn(response);

		mockMvc
				.perform(
						get(BASE_URL, 2)
								.param("id", DESIGNER_RETURN_ID.toString())
								.param("dn", DESIGNER_NAME)
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"searchDesigner",
								resource(
										ResourceSnippetParameters.builder()
												.description("designer 조회 | 이름 기반")
												.tag(TAG)
												.responseSchema(Schema.schema("DesignerResponse"))
												.responseFields(Description.success(DesignerDescription.designers()))
												.build())));
	}
}
