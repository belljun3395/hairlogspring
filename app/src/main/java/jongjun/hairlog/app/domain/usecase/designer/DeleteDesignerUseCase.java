package jongjun.hairlog.app.domain.usecase.designer;

import jongjun.hairlog.app.exception.ResourceNotFoundException;
import jongjun.hairlog.app.support.aop.ValidateRequestMemberId;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.repository.DesignerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteDesignerUseCase {

	private final DesignerRepository repository;

	@ValidateRequestMemberId
	public Long execute(Long memberId, Long designerId) {
		DesignerEntity source =
				repository
						.findById(designerId)
						.orElseThrow(() -> new ResourceNotFoundException("not found designer"));

		if (!source.getId().equals(memberId)) {
			throw new RuntimeException("not member's designer");
		}

		repository.delete(source);
		return source.getId();
	}
}