package jongjun.hairlog.data.repository;

import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.repository.query.DesignerCustomQuery;
import org.springframework.data.repository.CrudRepository;

public interface DesignerRepository
		extends CrudRepository<DesignerEntity, Long>, DesignerCustomQuery {}
