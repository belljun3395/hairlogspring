package jongjun.hairlog.data.entity.record;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import jongjun.hairlog.data.enums.HurtRate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@DiscriminatorValue("dyeing")
@SuperBuilder
@Table(name = "record_dyeing_entity")
public class DyeingEntity extends RecordEntity {

	@Column(name = "dyeing_color")
	private String dyeingColor;

	@Column(name = "dyeing_decolorization")
	private String dyeingDecolorization;

	@Column(name = "dyeing_time")
	private Long dyeingTime;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "dyeing_hurt")
	private HurtRate dyeingHurt;
}
