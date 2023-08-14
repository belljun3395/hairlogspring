package jongjun.hairlog.data.entity.record;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import jongjun.hairlog.data.enums.HurtRate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder(toBuilder = true)
@Entity
@Table(name = "record_dyeing_tb")
public class DyeingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dyeing_id", nullable = false)
	private Long id;

	@Column(name = "dyeing_color", nullable = false)
	private String dyeingColor;

	@Column(name = "dyeing_decolorization", nullable = false)
	private String dyeingDecolorization;

	@Column(name = "dyeing_time", nullable = false)
	private Long dyeingTime;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "dyeing_hurt", nullable = false)
	private HurtRate dyeingHurt;

	public void changeDyeingColor(String dyeingColor) {
		this.dyeingColor = dyeingColor;
	}

	public void changeDyeingDecolorization(String dyeingDecolorization) {
		this.dyeingDecolorization = dyeingDecolorization;
	}

	public void changeDyeingTime(Long dyeingTime) {
		this.dyeingTime = dyeingTime;
	}

	public void changeDyeingHurt(HurtRate dyeingHurt) {
		this.dyeingHurt = dyeingHurt;
	}
}
