package jongjun.hairlog.app.domain.model.member;

import jongjun.hairlog.data.enums.MemberSex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class MemberInfo {

	private String email;
	private String name;
	private MemberSex sex;
	private Long cycle;
}
