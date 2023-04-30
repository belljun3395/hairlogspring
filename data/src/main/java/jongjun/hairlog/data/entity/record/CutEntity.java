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

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@DiscriminatorValue("cut")
@SuperBuilder
@Table(name = "record_cut_entity")
public class CutEntity extends RecordEntity {

	@Column(name = "cut_name")
	private String cutName;

	@Column(name = "cut_length")
	private Long cutLength;
}
