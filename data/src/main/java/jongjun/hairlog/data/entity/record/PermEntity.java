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
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@DynamicUpdate
@DiscriminatorValue("perm")
@SuperBuilder
@Table(name = "record_perm_entity")
public class PermEntity extends RecordEntity {

	@Column(name = "perm_name")
	private String permName;

	@Column(name = "perm_time")
	private Long permTime;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "perm_hurt")
	private HurtRate permHurt;

	public void changePermName(String permName) {
		this.permName = permName;
	}

	public void changePermTime(Long permTime) {
		this.permTime = permTime;
	}

	public void changePermHurt(HurtRate permHurt) {
		this.permHurt = permHurt;
	}
}
