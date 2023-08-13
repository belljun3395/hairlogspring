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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.apache.logging.log4j.util.Strings;
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

	@Column(name = "edited_id")
	private Long editedId;

	@Builder.Default
	@Column(name = "member_email")
	private String email = Strings.EMPTY;

	@Builder.Default
	@Column(name = "member_password")
	private String password = Strings.EMPTY;

	@Builder.Default
	@Column(name = "member_name")
	private String name = Strings.EMPTY;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Column(name = "member_sex")
	private MemberSex sex = MemberSex.N;

	@Builder.Default
	@Column(name = "member_cycle")
	private Long cycle = 0L;

	@Column(name = "edit_success")
	private Boolean success;

	@Builder.Default
	@Column(name = "edited_fields")
	@Convert(converter = EditedFieldsConverter.class)
	private List<String> editedFields = new ArrayList<>();

	public void addEditedField(String editedField) {
		this.editedFields.add(editedField);
	}
}
