package jongjun.hairlog.data.entity.record;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@DiscriminatorValue("cut")
@SuperBuilder
@Table(name = "record_cut_entity")
public class CutEntity extends RecordEntity {

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
