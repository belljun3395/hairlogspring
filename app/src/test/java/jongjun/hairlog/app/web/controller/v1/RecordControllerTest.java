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
import java.util.Date;
import java.util.List;
import jongjun.hairlog.app.AppMain;
import jongjun.hairlog.app.domain.model.record.CutRecord;
import jongjun.hairlog.app.domain.model.record.DyeingRecord;
import jongjun.hairlog.app.domain.model.record.PermRecord;
import jongjun.hairlog.app.domain.model.record.RecordIndex;
import jongjun.hairlog.app.domain.usecase.record.GetRecordUseCase;
import jongjun.hairlog.app.domain.usecase.record.SaveRecordUseCase;
import jongjun.hairlog.app.support.Page;
import jongjun.hairlog.app.web.controller.request.record.CutRecordRequest;
import jongjun.hairlog.app.web.controller.request.record.DyeingRecordRequest;
import jongjun.hairlog.app.web.controller.request.record.PermRecordRequest;
import jongjun.hairlog.app.web.controller.v1.description.Description;
import jongjun.hairlog.app.web.controller.v1.description.RecordDescription;
import jongjun.hairlog.data.enums.HurtRate;
import jongjun.hairlog.data.enums.RecordCategory;
import jongjun.hairlog.data.enums.SatisfactionRate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles(value = "test")
@AutoConfigureRestDocs
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = AppMain.class)
class RecordControllerTest {

	private static final Long RECORD_ID = 1L;
	private static final Long RECORD_COST = 1L;
	private static final String RECORD_ETC = "testRecordEtc";
	private static final Long MEMBER_ID = 1L;
	private static final Long DESIGNER_ID = 1L;
	private static final String CUT_NAME = "testCutName";
	private static final Long CUT_LENGTH = 1L;
	private static final String PERM_NAME = "testPermName";
	private static final Long PERM_TIME = 1L;
	private static final String DYEING_DECOLORIZATION = "testDyeingDecolorization";
	private static final Long DYEING_TIME = 1L;
	private static final String DYEING_COLOR = "testDyeingColor";

	private static final String BASE_URL = "/api/v1/records";
	private static final String TAG = "Record";

	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;
	@MockBean private SaveRecordUseCase saveRecordUseCase;
	@MockBean private GetRecordUseCase getRecordUseCase;

	@Test
	void addCutRecord() throws Exception {
		CutRecordRequest request =
				CutRecordRequest.builder()
						.recordDate(new Date())
						.recordCost(RECORD_COST)
						.recordEtc(RECORD_ETC)
						.recordGrade(SatisfactionRate.H)
						.memberId(MEMBER_ID)
						.designerId(DESIGNER_ID)
						.cutName(CUT_NAME)
						.cutLength(CUT_LENGTH)
						.build();

		when(saveRecordUseCase.execute(request)).thenReturn(RECORD_ID);

		String content = objectMapper.writeValueAsString(request);

		mockMvc
				.perform(
						post(BASE_URL + "/cut", 0).content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"saveCutRecord",
								resource(
										ResourceSnippetParameters.builder()
												.description("cut 기록 추가")
												.tag(TAG)
												.requestSchema(Schema.schema("CutRequest"))
												.responseSchema(Schema.schema("CutResponse"))
												.responseFields(Description.success(RecordDescription.saveRecord()))
												.build())));
	}

	@Test
	void addPermRecord() throws Exception {
		PermRecordRequest request =
				PermRecordRequest.builder()
						.recordDate(new Date())
						.recordCost(RECORD_COST)
						.recordEtc(RECORD_ETC)
						.recordGrade(SatisfactionRate.H)
						.memberId(MEMBER_ID)
						.designerId(DESIGNER_ID)
						.permName(PERM_NAME)
						.permTime(PERM_TIME)
						.permHurt(HurtRate.H)
						.build();

		when(saveRecordUseCase.execute(request)).thenReturn(RECORD_ID);

		String content = objectMapper.writeValueAsString(request);

		mockMvc
				.perform(
						post(BASE_URL + "/perm", 0).content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"savePermRecord",
								resource(
										ResourceSnippetParameters.builder()
												.description("perm 기록 추가")
												.tag(TAG)
												.requestSchema(Schema.schema("PermRequest"))
												.responseSchema(Schema.schema("PermResponse"))
												.responseFields(Description.success(RecordDescription.saveRecord()))
												.build())));
	}

	@Test
	void addDyeingRecord() throws Exception {
		DyeingRecordRequest request =
				DyeingRecordRequest.builder()
						.recordDate(new Date())
						.recordCost(RECORD_COST)
						.recordEtc(RECORD_ETC)
						.recordGrade(SatisfactionRate.H)
						.memberId(MEMBER_ID)
						.designerId(DESIGNER_ID)
						.dyeingDecolorization(DYEING_DECOLORIZATION)
						.dyeingTime(DYEING_TIME)
						.dyeingColor(DYEING_COLOR)
						.dyeingHurt(HurtRate.H)
						.build();

		when(saveRecordUseCase.execute(request)).thenReturn(RECORD_ID);

		String content = objectMapper.writeValueAsString(request);

		mockMvc
				.perform(
						post(BASE_URL + "/dyeing", 0).content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"saveDyeingRecord",
								resource(
										ResourceSnippetParameters.builder()
												.description("dyeing 기록 추가")
												.tag(TAG)
												.requestSchema(Schema.schema("DyeingRequest"))
												.responseSchema(Schema.schema("DyeingResponse"))
												.responseFields(Description.success(RecordDescription.saveRecord()))
												.build())));
	}

	@Test
	void readRecordPage() throws Exception {
		Integer page = 0;
		Integer size = 5;
		PageRequest pageRequest = PageRequest.of(page, size);

		List<RecordIndex> recordIdxs = new ArrayList<>();
		recordIdxs.add(
				RecordIndex.builder()
						.recordId(RECORD_ID)
						.recordCategory(RecordCategory.CUT)
						.recordDate(new Date())
						.build());

		int totalCount = 10;
		PageImpl<RecordIndex> source = new PageImpl<>(recordIdxs, pageRequest, totalCount);

		Page<RecordIndex> response = new Page<>(source);

		when(getRecordUseCase.execute(MEMBER_ID, pageRequest)).thenReturn(response);

		mockMvc
				.perform(
						get(BASE_URL + "/pages", 3)
								.param("id", MEMBER_ID.toString())
								.param("page", page.toString())
								.param("size", size.toString())
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"readPageRecords",
								resource(
										ResourceSnippetParameters.builder()
												.description("record 인덱스 조회")
												.tag(TAG)
												.responseSchema(Schema.schema("RecordIndexResponse"))
												.responseFields(Description.success(RecordDescription.recordIndex()))
												.build())));
	}

	@Test
	void readCutRecord() throws Exception {
		CutRecord response =
				CutRecord.builder()
						.recordDate(new Date())
						.recordCost(RECORD_COST)
						.recordEtc(RECORD_ETC)
						.recordGrade(SatisfactionRate.H)
						.designer(DESIGNER_ID)
						.cutName(CUT_NAME)
						.cutLength(CUT_LENGTH)
						.build();

		when(getRecordUseCase.execute(MEMBER_ID, RECORD_ID, RecordCategory.CUT)).thenReturn(response);

		mockMvc
				.perform(
						get(BASE_URL, 1)
								.param("id", MEMBER_ID.toString())
								.param("rid", RECORD_ID.toString())
								.param("c", RecordCategory.CUT.toString())
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"readCutRecord",
								resource(
										ResourceSnippetParameters.builder()
												.description("Cut Record 조회 | 기록 id 기반")
												.tag(TAG)
												.responseSchema(Schema.schema("CutRecordResponse"))
												.responseFields(
														Description.success(RecordDescription.recordRecord(RecordCategory.CUT)))
												.build())));
	}

	@Test
	void readPermRecord() throws Exception {
		PermRecord response =
				PermRecord.builder()
						.recordDate(new Date())
						.recordCost(RECORD_COST)
						.recordEtc(RECORD_ETC)
						.recordGrade(SatisfactionRate.H)
						.designer(DESIGNER_ID)
						.permName(PERM_NAME)
						.permTime(PERM_TIME)
						.permHurt(HurtRate.H)
						.build();

		when(getRecordUseCase.execute(MEMBER_ID, RECORD_ID, RecordCategory.PERM)).thenReturn(response);

		mockMvc
				.perform(
						get(BASE_URL, 1)
								.param("id", MEMBER_ID.toString())
								.param("rid", RECORD_ID.toString())
								.param("c", RecordCategory.PERM.toString())
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"readPermRecord",
								resource(
										ResourceSnippetParameters.builder()
												.description("Perm Record 조회 | 기록 id 기반")
												.tag(TAG)
												.responseSchema(Schema.schema("PermRecordResponse"))
												.responseFields(
														Description.success(
																RecordDescription.recordRecord(RecordCategory.PERM)))
												.build())));
	}

	@Test
	void readDyeing() throws Exception {
		DyeingRecord response =
				DyeingRecord.builder()
						.recordDate(new Date())
						.recordCost(RECORD_COST)
						.recordEtc(RECORD_ETC)
						.recordGrade(SatisfactionRate.H)
						.designer(DESIGNER_ID)
						.dyeingDecolorization(DYEING_DECOLORIZATION)
						.dyeingTime(DYEING_TIME)
						.dyeingColor(DYEING_COLOR)
						.dyeingHurt(HurtRate.H)
						.build();

		when(getRecordUseCase.execute(MEMBER_ID, RECORD_ID, RecordCategory.DYEING))
				.thenReturn(response);

		mockMvc
				.perform(
						get(BASE_URL, 1)
								.param("id", MEMBER_ID.toString())
								.param("rid", RECORD_ID.toString())
								.param("c", RecordCategory.DYEING.toString())
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful())
				.andDo(
						document(
								"readDyeingRecord",
								resource(
										ResourceSnippetParameters.builder()
												.description("Dyeing Record 조회 | 기록 id 기반")
												.tag(TAG)
												.responseSchema(Schema.schema("DyeingRecordResponse"))
												.responseFields(
														Description.success(
																RecordDescription.recordRecord(RecordCategory.DYEING)))
												.build())));
	}
}