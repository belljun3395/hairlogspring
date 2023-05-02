package jongjun.hairlog.app.domain.converter.record;

import jongjun.hairlog.app.support.Page;
import org.springframework.stereotype.Component;

@Component
public class RecordConverter {

	/** fixme domain 객체를 통해 받도록 수정 */
	public Page from(org.springframework.data.domain.Page source) {
		return new Page(source);
	}
}
