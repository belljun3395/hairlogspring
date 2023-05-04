package jongjun.hairlog.app.web.controller.v1.description;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

public class DesignerDescription {

	public static FieldDescriptor[] designers() {
		return new FieldDescriptor[] {
			fieldWithPath("data[]").type(JsonFieldType.ARRAY).description("디자이너"),
			fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("디자이너 id"),
			fieldWithPath("data[].designerName").type(JsonFieldType.STRING).description("디자이너 이름"),
			fieldWithPath("data[].designerSalon").type(JsonFieldType.STRING).description("디자이너 미용실"),
			fieldWithPath("data[].designerFav").type(JsonFieldType.BOOLEAN).description("디자이너 선호"),
		};
	}

	public static FieldDescriptor[] designer() {
		return new FieldDescriptor[] {
			fieldWithPath("data").type(JsonFieldType.OBJECT).description("디자이너"),
			fieldWithPath("data.id").type(JsonFieldType.NUMBER).description("디자이너 id"),
			fieldWithPath("data.designerName").type(JsonFieldType.STRING).description("디자이너 이름"),
			fieldWithPath("data.designerSalon").type(JsonFieldType.STRING).description("디자이너 미용실"),
			fieldWithPath("data.designerFav").type(JsonFieldType.BOOLEAN).description("디자이너 선호"),
		};
	}

	public static FieldDescriptor[] saveDesigner() {
		return new FieldDescriptor[] {
			fieldWithPath("data").type(JsonFieldType.NUMBER).description("디자이너 id"),
		};
	}
}
