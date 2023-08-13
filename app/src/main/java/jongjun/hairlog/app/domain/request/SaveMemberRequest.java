package jongjun.hairlog.app.domain.request;

import jongjun.hairlog.data.enums.MemberSex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SaveMemberRequest {

	private Long id;
	private String email;
	private String password;
	private String name;
	private MemberSex sex;
	private Long cycle;
}
