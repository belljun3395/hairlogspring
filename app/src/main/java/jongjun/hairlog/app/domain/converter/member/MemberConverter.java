package jongjun.hairlog.app.domain.converter.member;

import jongjun.hairlog.app.domain.model.member.Member;
import jongjun.hairlog.app.domain.model.member.MemberInfo;
import jongjun.hairlog.app.domain.request.SaveMemberRequest;
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

	public MemberInfo infoFrom(MemberEntity source) {
		return MemberInfo.builder()
				.email(source.getEmail())
				.name(source.getName())
				.sex(source.getSex())
				.cycle(source.getCycle())
				.build();
	}

	public MemberEntity to(SaveMemberRequest request) {
		return MemberEntity.builder()
				.email(request.getEmail())
				.password(request.getPassword())
				.name(request.getName())
				.sex(request.getSex())
				.cycle(request.getCycle())
				.build();
	}
}
