package jongjun.hairlog.data.entity.record;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import jongjun.hairlog.data.enums.SatisfactionRate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@SuperBuilder(toBuilder = true)
@Embeddable
public class CommonRecordInfo {
	@Temporal(TemporalType.DATE)
	@Column(name = "record_date", nullable = false)
	private Date recordDate;

	@Column(name = "record_cost", nullable = false)
	private Long recordCost;

	@Lob
	@Column(name = "record_etc")
	private String recordEtc;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "record_grade", nullable = false)
	private SatisfactionRate recordGrade;

	public void changeRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public void changeRecordCost(Long recordCost) {
		this.recordCost = recordCost;
	}

	public void changeRecordEtc(String recordEtc) {
		this.recordEtc = recordEtc;
	}

	public void changeRecordGrade(SatisfactionRate recordGrade) {
		this.recordGrade = recordGrade;
	}
}
