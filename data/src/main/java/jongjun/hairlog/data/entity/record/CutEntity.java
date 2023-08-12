package jongjun.hairlog.data.entity.record;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "record_cut_tb")
public class CutEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cut_id", nullable = false)
	private Long id = 0L;

	@Column(name = "cut_name", nullable = false)
	private String cutName;

	@Column(name = "cut_length", nullable = false)
	private Long cutLength;

	public void changeCutName(String cutName) {
		this.cutName = cutName;
	}

	public void changeCutLength(Long cutLength) {
		this.cutLength = cutLength;
	}
}
