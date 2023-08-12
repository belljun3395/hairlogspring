package jongjun.hairlog.data.entity.record;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import jongjun.hairlog.data.entity.BaseEntity;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.enums.RecordCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@SuperBuilder(toBuilder = true)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "record_tb")
@AttributeOverrides(
		value = {
			@AttributeOverride(name = "id", column = @Column(name = "record_id")),
		})
@SQLDelete(sql = "UPDATE record_tb SET deleted = true WHERE record_id=?")
public class RecordEntity extends BaseEntity {

	@Embedded private CommonRecordInfo recordInfo;

	@Column(name = "record_category", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private RecordCategory recordCategory;

	@Column(name = "sub_record_fk", nullable = false)
	private Long subId;

	@Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_fk", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private MemberEntity member;

	@Exclude
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "designer_fk", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private DesignerEntity designer;

	public void changeDesigner(Long designerId) {
		this.designer = DesignerEntity.builder().id(designerId).build();
	}

	public void associateWithSubRecord(Long subId, RecordCategory recordCategory) {
		this.subId = subId;
		this.recordCategory = recordCategory;
	}
}
