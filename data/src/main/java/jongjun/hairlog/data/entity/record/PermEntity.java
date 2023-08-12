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
@Table(name = "record_perm_tb")
public class PermEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "perm_id", nullable = false)
	private Long id;

	@Column(name = "perm_name", nullable = false)
	private String permName;

	@Column(name = "perm_time", nullable = false)
	private Long permTime;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "perm_hurt", nullable = false)
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
