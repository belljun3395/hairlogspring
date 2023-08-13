package jongjun.hairlog.data.log.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import jongjun.hairlog.data.converter.EditedFieldsConverter;
import jongjun.hairlog.data.enums.MemberSex;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder.Default;
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
@Table(name = "member_log_tb")
@AttributeOverrides(
		value = {
			@AttributeOverride(name = "id", column = @Column(name = "member_id")),
		})
@SQLDelete(sql = "UPDATE member_log_tb SET deleted = true WHERE member_id=?")
public class MemberInfoLog extends LogBaseEntity {

	@Column(name = "edited_id", nullable = false)
	private Long editedId;

	@Column(name = "member_email", nullable = false)
	private String email;

	@Column(name = "member_password", nullable = false)
	private String password;

	@Column(name = "member_name", nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "member_sex", nullable = false)
	private MemberSex sex;

	@Column(name = "member_cycle", nullable = false)
	private Long cycle;

	@Default
	@Column(name = "edited_fields", nullable = false)
	@Convert(converter = EditedFieldsConverter.class)
	private List<String> editedFields = new ArrayList<>();

	public void addEditedField(String editedField) {
		this.editedFields.add(editedField);
	}
}
