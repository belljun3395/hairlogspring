package jongjun.hairlog.data.entity.record;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.enums.SatisfactionRate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/** @implSpec {@link jongjun.hairlog.data.entity.record.RecordEntity} 관련 Entity 경우 상속하여 사용한다. */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "record_category")
@ToString
@SuperBuilder(toBuilder = true)
@Table(name = "record_entity")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE record_entity SET deleted = true WHERE member_fk=?")
@Where(clause = "deleted=false")
@NamedNativeQuery(
		name = "RecordEntity.findDeletedRecordsEntity",
		query = "select r.record_id, r.record_date from record_entity r where r.member_fk = ?1")
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

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "member_fk")
	private MemberEntity member;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "designer_fk")
	private DesignerEntity designer;

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createAt;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updateAt;

	@Column(nullable = false)
	@Builder.Default
	private Boolean deleted = false;

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

	public void changeDesigner(DesignerEntity designer) {
		this.designer = designer;
	}
}
