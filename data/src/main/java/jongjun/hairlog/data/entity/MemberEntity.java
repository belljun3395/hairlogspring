package jongjun.hairlog.data.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Version;
import jongjun.hairlog.data.enums.MemberSex;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "member_tb")
@AttributeOverrides(
		value = {
			@AttributeOverride(name = "id", column = @Column(name = "member_id")),
		})
@SQLDelete(sql = "UPDATE member_tb SET deleted = true WHERE member_id=?")
public class MemberEntity extends BaseEntity {

	@Column(name = "member_email")
	private String email;

	@Column(name = "member_password", nullable = false)
	private String password;

	@Column(name = "member_name", nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "member_sex", nullable = false)
	private MemberSex sex;

	@Column(name = "member_cycle")
	private Long cycle;

	@Version
	@Column(name = "member_version")
	private Long version;

	public void changeName(String name) {
		this.name = name;
	}

	public void changeCycle(Long cycle) {
		this.cycle = cycle;
	}
}
