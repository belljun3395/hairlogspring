package jongjun.hairlog.app.domain.usecase.designer;

import java.util.List;
import java.util.stream.Collectors;
import jongjun.hairlog.app.config.security.AuditorHolder;
import jongjun.hairlog.app.domain.converter.designer.DesignerConverter;
import jongjun.hairlog.app.domain.model.designer.Designer;
import jongjun.hairlog.app.exception.ResourceNotFoundException;
import jongjun.hairlog.app.web.controller.request.designer.DesignerIdParam;
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

	public List<Designer> execute() {
		Long memberId = AuditorHolder.get();
		return repository.findAllByMemberIdAndDeletedFalse(memberId).stream()
				.map(converter::from)
				.collect(Collectors.toList());
	}

	public Designer execute(DesignerIdParam designerIdParam) {
		return repository
				.findById(designerIdParam.getDesignerId())
				.map(converter::from)
				.orElseThrow(() -> new ResourceNotFoundException("not found designer"));
	}

	public List<Designer> execute(String designerName) {
		Long memberId = AuditorHolder.get();
		return repository.findAllByDesignerNameAndMemberAndDeletedFalse(designerName, memberId).stream()
				.map(converter::from)
				.collect(Collectors.toList());
	}
}
