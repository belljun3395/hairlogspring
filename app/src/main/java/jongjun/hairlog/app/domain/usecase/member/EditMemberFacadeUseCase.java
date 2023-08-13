package jongjun.hairlog.app.domain.usecase.member;

import jongjun.hairlog.app.domain.request.EditMemberRequest;
import jongjun.hairlog.app.exception.AlreadyEditException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EditMemberFacadeUseCase {

	private final EditMemberUseCase editMemberUseCase;
	private final ApplicationEventPublisher publisher;

	public Long execute(final EditMemberRequest request) {
		while (true) {
			try {
				return editMemberUseCase.execute(request);
			} catch (RuntimeException e) {
				publisher.publishEvent(request);
				throw new AlreadyEditException(request.getId());
			}
		}
	}
}
