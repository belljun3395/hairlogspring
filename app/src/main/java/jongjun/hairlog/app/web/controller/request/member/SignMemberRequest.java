package jongjun.hairlog.app.web.controller.request.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignMemberRequest {

	private String email;
	private String password;
}
