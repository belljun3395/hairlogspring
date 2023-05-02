package jongjun.hairlog.app.domain.model.member;

import java.time.LocalDateTime;
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
public class Member {

	private Long id;

	private String email;

	private String password;

	private String name;

	private MemberSex sex;

	private Long cycle;

	private LocalDateTime createAt;
}
