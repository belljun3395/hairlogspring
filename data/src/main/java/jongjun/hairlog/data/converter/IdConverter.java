package jongjun.hairlog.data.converter;

import java.math.BigInteger;
import javax.persistence.AttributeConverter;
import org.springframework.stereotype.Component;

@Component
public class IdConverter implements AttributeConverter<Long, BigInteger> {

	@Override
	public BigInteger convertToDatabaseColumn(Long attribute) {
		return BigInteger.valueOf(attribute);
	}

	@Override
	public Long convertToEntityAttribute(BigInteger dbData) {
		return Long.valueOf(dbData.toString());
	}
}
