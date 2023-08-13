package jongjun.hairlog.app.domain.usecase.member;

import jongjun.hairlog.app.domain.converter.member.MemberUpdateDelegator;
import jongjun.hairlog.app.domain.request.EditMemberRequest;
import jongjun.hairlog.app.exception.MemberNotFoundException;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.log.entity.MemberInfoLog;
import jongjun.hairlog.data.log.repository.MemberLogRepository;
import jongjun.hairlog.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EditMemberUseCase {

	private final MemberRepository repository;
	private final MemberLogRepository logRepository;
	private final MemberUpdateDelegator updateDelegator;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Long execute(final EditMemberRequest request) {
		MemberEntity memberEntity =
				repository
						.findById(request.getId())
						.orElseThrow(() -> new MemberNotFoundException(request.getId()));

		saveLog(memberEntity, request);

		MemberEntity updateMemberEntity = getUpdateMemberEntity(memberEntity, request);

		return updateMemberEntity.getId();
	}

	private void saveLog(MemberEntity memberEntity, EditMemberRequest request) {
		MemberInfoLog logInfo = updateDelegator.log(memberEntity, request);
		logRepository.save(logInfo);
	}

	private MemberEntity getUpdateMemberEntity(MemberEntity memberEntity, EditMemberRequest request) {
		MemberEntity updateMember = updateDelegator.update(memberEntity, request);
		return repository.save(updateMember);
	}
}
