package jongjun.hairlog.app.web.controller.v1;

import jongjun.hairlog.app.domain.model.member.Member;
import jongjun.hairlog.app.domain.model.member.MemberInfo;
import jongjun.hairlog.app.domain.usecase.member.GetMemberUseCase;
import jongjun.hairlog.app.domain.usecase.member.SaveMemberUseCase;
import jongjun.hairlog.app.support.ApiResponse;
import jongjun.hairlog.app.support.ApiResponseGenerator;
import jongjun.hairlog.app.web.controller.request.member.MemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@RestController
public class MemberController {

	private final SaveMemberUseCase saveMemberUseCase;
	private final GetMemberUseCase getMemberUseCase;

	@PostMapping()
	public ApiResponse<ApiResponse.SuccessBody<Long>> addMember(@RequestBody MemberRequest request) {
		return ApiResponseGenerator.success(saveMemberUseCase.execute(request), HttpStatus.OK);
	}

	@GetMapping()
	public ApiResponse<ApiResponse.SuccessBody<Member>> readMember(@RequestParam("id") Long id) {
		return ApiResponseGenerator.success(getMemberUseCase.execute(id), HttpStatus.OK);
	}

	@GetMapping("/info")
	public ApiResponse<ApiResponse.SuccessBody<MemberInfo>> readMemberInfo(
			@RequestParam("email") String email) {
		return ApiResponseGenerator.success(getMemberUseCase.execute(email), HttpStatus.OK);
	}
}
