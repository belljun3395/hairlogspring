package jongjun.hairlog.app.domain.converter.member;

import jongjun.hairlog.app.domain.request.EditMemberRequest;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.log.entity.MemberInfoLog;
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
		return source;
	}

	public MemberInfoLog log(MemberEntity source, EditMemberRequest request) {
		MemberInfoLog memberInfoLog =
				MemberInfoLog.builder()
						.editedId(source.getId())
						.email(source.getEmail())
						.password(source.getPassword())
						.name(source.getName())
						.sex(source.getSex())
						.cycle(source.getCycle())
						.build();

		if (request.getName() != null) {
			memberInfoLog.addEditedField("name");
		}
		if (request.getCycle() != null) {
			memberInfoLog.addEditedField("cycle");
		}
		return memberInfoLog;
	}
}
