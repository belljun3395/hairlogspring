package jongjun.hairlog.app.domain.converter.member;

import jongjun.hairlog.app.web.controller.request.member.MemberEditRequest;
import jongjun.hairlog.data.entity.MemberEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberUpdateConverter {

	public void to(MemberEntity source, MemberEditRequest request) {
		if (request.getName() != null) {
			source.changeName(request.getName());
		}
		if (request.getCycle() != null) {
			source.changeCycle(request.getCycle());
		}
	}
}
