package jongjun.hairlog.app.support.validate;

import jongjun.hairlog.data.entity.record.RecordEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SourceValidator {

	public static void validate(Long memberId, RecordEntity source) {
		if (!memberId.equals(source.getId())) {
			throw new RuntimeException("not member's record");
		}
	}
}
