package jongjun.hairlog.data.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "designer_tb")
@AttributeOverrides(
		value = {
			@AttributeOverride(name = "id", column = @Column(name = "designer_id")),
		})
@SQLDelete(sql = "UPDATE designer_tb SET deleted = true WHERE designer_id=?")
public class DesignerEntity extends BaseEntity {

	@Column(name = "designer_name", nullable = false)
	private String designerName;

	@Column(name = "designer_salon")
	private String designerSalon;

	@Builder.Default
	@Column(name = "designer_fav", nullable = false)
	private Boolean designerFav = true;

	@Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_fk", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private MemberEntity member;

	public void changeDesignerFav(Boolean designerFav) {
		this.designerFav = designerFav;
	}
}
