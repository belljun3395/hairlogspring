package jongjun.hairlog.data.repository.query;

import jongjun.hairlog.data.entity.DesignerEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class DesignerCustomQueryImpl extends QuerydslRepositorySupport
		implements DesignerCustomQuery {
	public DesignerCustomQueryImpl() {
		super(DesignerEntity.class);
	}
}
