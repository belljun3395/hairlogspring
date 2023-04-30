package jongjun.hairlog.data.entity.record;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
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
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "record_category")
@ToString
@SuperBuilder(toBuilder = true)
@Table(name = "record_entity")
public abstract class RecordEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "record_id")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "record_date")
	private Date recordDate;

	@Column(name = "record_cost")
	private Long recordCost;

	@Column(name = "record_etc")
	private String recordEtc;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "record_grade")
	private SatisfactionRate recordGrade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_fk")
	private MemberEntity member;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "designer_fk")
	private DesignerEntity designer;
}
