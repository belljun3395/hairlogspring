package jongjun.hairlog.app.domain.converter.member;

import jongjun.hairlog.app.domain.model.member.Member;
import jongjun.hairlog.app.domain.model.member.MemberInfo;
import jongjun.hairlog.app.web.controller.request.MemberRequest;
import jongjun.hairlog.data.dto.member.MemberInfoDTO;
import jongjun.hairlog.data.entity.MemberEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {

	public Member from(MemberEntity source) {
		return Member.builder()
				.id(source.getId())
				.email(source.getEmail())
				.password(source.getPassword())
				.name(source.getName())
				.sex(source.getSex())
				.cycle(source.getCycle())
				.createAt(source.getCreateAt())
				.build();
	}

	public MemberInfo from(MemberInfoDTO source) {
		return MemberInfo.builder().email(source.getEmail()).name(source.getName()).build();
	}
}
