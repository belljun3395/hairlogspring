package jongjun.hairlog.app.domain.usecase.designer;

import java.util.List;
import java.util.stream.Collectors;
import jongjun.hairlog.app.domain.converter.designer.DesignerConverter;
import jongjun.hairlog.app.domain.model.designer.Designer;
import jongjun.hairlog.app.exception.ResourceNotFoundException;
import jongjun.hairlog.app.support.aop.ValidateRequestMemberId;
import jongjun.hairlog.data.repository.DesignerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetDesignerUseCase {

	private final DesignerRepository repository;
	private final DesignerConverter converter;

	@ValidateRequestMemberId
	public List<Designer> execute(Long memberId) {
		return repository.findAllByMemberIdQuery(memberId).stream()
				.map(converter::from)
				.collect(Collectors.toList());
	}

	@ValidateRequestMemberId
	public Designer execute(Long memberId, Long designerId) {
		return repository
				.findById(designerId)
				.map(converter::from)
				.orElseThrow(() -> new ResourceNotFoundException("not found designer"));
	}

	@ValidateRequestMemberId
	public List<Designer> execute(Long memberId, String designerName) {
		return repository.searchByNameAndMemberIdQuery(designerName, memberId).stream()
				.map(converter::from)
				.collect(Collectors.toList());
	}
}
