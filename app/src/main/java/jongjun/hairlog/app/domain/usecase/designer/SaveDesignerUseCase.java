package jongjun.hairlog.app.domain.usecase.designer;

import jongjun.hairlog.app.domain.converter.designer.DesignerConverter;
import jongjun.hairlog.app.domain.converter.designer.DesignerUpdateConverter;
import jongjun.hairlog.app.exception.MemberNotFoundException;
import jongjun.hairlog.app.support.aop.ValidateRequestMemberId;
import jongjun.hairlog.app.support.validate.SourceValidator;
import jongjun.hairlog.app.web.controller.request.designer.DesignerRequest;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.repository.DesignerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SaveDesignerUseCase {

	private final DesignerRepository repository;
	private final DesignerConverter converter;
	private final DesignerUpdateConverter updateConverter;

	public Long execute(DesignerRequest request) {
		DesignerEntity data = converter.to(request);
		repository.save(data);
		return data.getId();
	}

	@ValidateRequestMemberId
	public Long execute(Long memberId, Long designerId, Boolean fav) {
		DesignerEntity source =
				repository.findById(designerId).orElseThrow(() -> new MemberNotFoundException(memberId));
		SourceValidator.validate(memberId, source);
		updateConverter.to(source, fav);
		return source.getId();
	}
}
