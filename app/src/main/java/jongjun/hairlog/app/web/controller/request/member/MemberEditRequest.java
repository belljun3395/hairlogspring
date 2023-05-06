package jongjun.hairlog.app.web.controller.request.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberEditRequest {

	private Long id;
	private String name;
	private Long cycle;
}
