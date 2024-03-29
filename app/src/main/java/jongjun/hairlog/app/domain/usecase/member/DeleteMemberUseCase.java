package jongjun.hairlog.app.domain.usecase.member;

import jongjun.hairlog.app.exception.MemberNotFoundException;
import jongjun.hairlog.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteMemberUseCase {

	private final MemberRepository repository;

	public Long execute(Long memberId) {

		Long id =
				repository
						.findById(memberId)
						.orElseThrow(() -> new MemberNotFoundException(memberId))
						.getId();

		repository.deleteById(id);

		return id;
	}
}
