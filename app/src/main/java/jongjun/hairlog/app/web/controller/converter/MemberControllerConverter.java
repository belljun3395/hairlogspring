package jongjun.hairlog.app.web.controller.converter;

import jongjun.hairlog.app.config.security.AuditorHolder;
import jongjun.hairlog.app.domain.request.EditMemberRequest;
import jongjun.hairlog.app.domain.request.SaveMemberRequest;
import jongjun.hairlog.app.domain.request.SignMemberRequest;
import jongjun.hairlog.app.web.controller.request.member.MemberEditRequest;
import jongjun.hairlog.app.web.controller.request.member.MemberRequest;
import jongjun.hairlog.app.web.controller.request.member.MemberSignRequest;
import org.springframework.stereotype.Component;

@Component
public class MemberControllerConverter {

	public SaveMemberRequest to(MemberRequest request) {
		return SaveMemberRequest.builder()
				.id(AuditorHolder.get())
				.email(request.getEmail())
				.password(request.getPassword())
				.name(request.getName())
				.sex(request.getSex())
				.cycle(request.getCycle())
				.build();
	}

	public EditMemberRequest to(MemberEditRequest request) {
		return EditMemberRequest.builder()
				.id(AuditorHolder.get())
				.name(request.getName())
				.cycle(request.getCycle())
				.build();
	}

	public SignMemberRequest to(MemberSignRequest request) {
		return SignMemberRequest.builder()
				.id(AuditorHolder.get())
				.email(request.getEmail())
				.password(request.getPassword())
				.build();
	}
}
