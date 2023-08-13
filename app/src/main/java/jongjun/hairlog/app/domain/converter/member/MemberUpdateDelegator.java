package jongjun.hairlog.app.domain.converter.member;

import jongjun.hairlog.app.domain.request.EditMemberRequest;
import jongjun.hairlog.data.entity.MemberEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberUpdateDelegator {

	public MemberEntity update(MemberEntity source, EditMemberRequest request) {
		if (request.getName() != null) {
			source.changeName(request.getName());
		}
		if (request.getCycle() != null) {
			source.changeCycle(request.getCycle());
		}
		return source.toBuilder().id(null).deleted(false).build();
	}
}
