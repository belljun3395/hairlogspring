package jongjun.hairlog.app.domain.event.listener;

import jongjun.hairlog.app.domain.request.EditMemberRequest;
import jongjun.hairlog.data.log.entity.MemberInfoLog;
import jongjun.hairlog.data.log.repository.MemberLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class FailEditMemberListener {

	private final MemberLogRepository logRepository;

	@EventListener
	@Transactional
	public void handleEvent(final EditMemberRequest event) {
		log.error(
				"concurrent update error id : {}, time : {}, record : {}",
				event.getId(),
				System.currentTimeMillis(),
				event);
		MemberInfoLog failLog =
				MemberInfoLog.builder()
						.editedId(event.getId())
						.name(event.getName())
						.cycle(event.getCycle())
						.success(false)
						.build();
		logRepository.save(failLog);
	}
}
