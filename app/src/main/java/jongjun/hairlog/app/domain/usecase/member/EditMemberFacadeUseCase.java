package jongjun.hairlog.app.domain.usecase.member;

import jongjun.hairlog.app.domain.request.EditMemberRequest;
import jongjun.hairlog.app.web.controller.response.SaveMemberResponse;
import jongjun.hairlog.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EditMemberFacadeUseCase {

	private final MemberRepository repository;
	private final EditMemberUseCase editMemberUseCase;

	@Transactional
	public Long execute(final EditMemberRequest request) {
		try {
			repository.getLock(request.getId().toString());

			return editMemberUseCase.execute(request);
		} finally {
			// todo finally 이전에 AlreadyEditException 에 대한 처리 필요
			repository.releaseLock(request.getId().toString());
		}
	}
}
