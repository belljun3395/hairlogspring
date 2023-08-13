package jongjun.hairlog.data.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.AttributeConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class EditedFieldsConverter implements AttributeConverter<List<String>, String> {

	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
		return attribute.toString();
	}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {
		String[] tokens = StringUtils.splitPreserveAllTokens(dbData, "[,]");
		List<String> rtn = new ArrayList<>();
		for (String token : tokens) {
			if (!Objects.equals(token, "")) {
				rtn.add(token.trim());
			}
		}
		return rtn;
	}
}
