package jongjun.hairlog.app.web.controller.v1.description;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import jongjun.hairlog.data.enums.RecordCategory;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

public class RecordDescription {

	public static FieldDescriptor[] recordIndex() {
		return new FieldDescriptor[] {
			fieldWithPath("data").type(JsonFieldType.OBJECT).description("기록 페이지"),
			fieldWithPath("data.pageSize").type(JsonFieldType.NUMBER).description("기록 페이지 사이즈"),
			fieldWithPath("data.pageNumber").type(JsonFieldType.NUMBER).description("기록 페이지"),
			fieldWithPath("data.totalPageCount").type(JsonFieldType.NUMBER).description("기록 페이지 갯수"),
			fieldWithPath("data.totalCount").type(JsonFieldType.NUMBER).description("기록 총 갯수"),
			fieldWithPath("data.data").type(JsonFieldType.ARRAY).description("기록 페이지 데이터"),
			fieldWithPath("data.data[].recordId").type(JsonFieldType.NUMBER).description("기록 데이터 id"),
			fieldWithPath("data.data[].recordDate")
					.type(JsonFieldType.STRING)
					.description("기록 데이터 기록 일자"),
			fieldWithPath("data.data[].recordCategory")
					.type(JsonFieldType.STRING)
					.description("기록 데이터 기록 종류"),
		};
	}

	public static FieldDescriptor[] recordRecord(RecordCategory category) {
		FieldDescriptor[] defaultDescript = {
			fieldWithPath("data").type(JsonFieldType.OBJECT).description("기록"),
				fieldWithPath("data.recordDate").type(JsonFieldType.STRING).description(
					"기록 날짜"),
				fieldWithPath("data.recordCost").type(JsonFieldType.NUMBER).description(
					"기록 가격"),
				fieldWithPath("data.recordEtc").type(JsonFieldType.STRING).description("기록 기타"),
				fieldWithPath("data.recordGrade").type(JsonFieldType.STRING).description(
					"기록 만족도"),
				fieldWithPath("data.designer").type(JsonFieldType.NUMBER).description(
					"기록 디자이너 id")
		};
		switch (category) {
			case CUT:
				FieldDescriptor[] cut = {
					fieldWithPath("data.cutName").type(JsonFieldType.STRING).description("기록 컷 이름"),
					fieldWithPath("data.cutLength").type(JsonFieldType.NUMBER).description(
						"기록 컷 길이"),
				};
				return ArrayUtils.addAll(defaultDescript, cut);
			case PERM:
				FieldDescriptor[] perm = {
					fieldWithPath("data.permTime").type(JsonFieldType.NUMBER).description("기록 펌 시간"),
					fieldWithPath("data.permName").type(JsonFieldType.STRING).description("기록 펌 이름"),
					fieldWithPath("data.permHurt").type(JsonFieldType.STRING).description("기록 펌 손상정도"),
				};
				return ArrayUtils.addAll(defaultDescript, perm);
			case DYEING:
				FieldDescriptor[] dyeing = {
					fieldWithPath("data.dyeingColor").type(JsonFieldType.STRING).description("기록 염색 색상"),
					fieldWithPath("data.dyeingDecolorization")
							.type(JsonFieldType.STRING)
							.description("기록 염색 변색"),
					fieldWithPath("data.dyeingTime").type(JsonFieldType.NUMBER).description("기록 염색 탈색 횟수"),
					fieldWithPath("data.dyeingHurt").type(JsonFieldType.STRING).description("기록 염색 손상 정도"),
				};
				return ArrayUtils.addAll(defaultDescript, dyeing);
			default:
				throw new IllegalStateException("choose right category");
		}
	}

	public static FieldDescriptor[] saveRecord() {
		return new FieldDescriptor[] {
			fieldWithPath("data").type(JsonFieldType.NUMBER).description("기록 id")
		};
	}
}
