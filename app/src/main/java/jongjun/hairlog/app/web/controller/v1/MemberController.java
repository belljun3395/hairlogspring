package jongjun.hairlog.app.web.controller.v1;

import jongjun.hairlog.app.config.security.AuditorHolder;
import jongjun.hairlog.app.domain.model.member.Member;
import jongjun.hairlog.app.domain.model.member.MemberInfo;
import jongjun.hairlog.app.domain.model.member.Token;
import jongjun.hairlog.app.domain.usecase.member.DeleteMemberUseCase;
import jongjun.hairlog.app.domain.usecase.member.EditMemberUseCase;
import jongjun.hairlog.app.domain.usecase.member.GetMemberUseCase;
import jongjun.hairlog.app.domain.usecase.member.GetTokenUseCase;
import jongjun.hairlog.app.domain.usecase.member.SaveMemberUseCase;
import jongjun.hairlog.app.domain.usecase.member.SignMemberUseCase;
import jongjun.hairlog.app.support.ApiResponse;
import jongjun.hairlog.app.support.ApiResponseGenerator;
import jongjun.hairlog.app.web.controller.converter.MemberControllerConverter;
import jongjun.hairlog.app.web.controller.request.member.MemberEditRequest;
import jongjun.hairlog.app.web.controller.request.member.MemberRequest;
import jongjun.hairlog.app.web.controller.request.member.MemberSignRequest;
import jongjun.hairlog.app.web.controller.response.SaveMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@RestController
public class MemberController {

	private final SaveMemberUseCase saveMemberUseCase;
	private final EditMemberUseCase editMemberUseCase;
	private final GetMemberUseCase getMemberUseCase;
	private final GetTokenUseCase getTokenUseCase;
	private final SignMemberUseCase signMemberUseCase;
	private final DeleteMemberUseCase deleteMemberUseCase;
	private final MemberControllerConverter memberControllerConverter;

	@PostMapping()
	public ApiResponse<ApiResponse.SuccessBody<Long>> addMember(@RequestBody MemberRequest request) {
		return ApiResponseGenerator.success(
				saveMemberUseCase.execute(memberControllerConverter.to(request)), HttpStatus.OK);
	}

	@PatchMapping()
	public ApiResponse<ApiResponse.SuccessBody<SaveMemberResponse>> editMember(
			@RequestBody MemberEditRequest request) {
		return ApiResponseGenerator.success(
				editMemberUseCase.execute(memberControllerConverter.to(request)), HttpStatus.OK);
	}

	@DeleteMapping()
	public ApiResponse<ApiResponse.SuccessBody<Long>> deleteMember() {
		return ApiResponseGenerator.success(
				deleteMemberUseCase.execute(AuditorHolder.get()), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ApiResponse<ApiResponse.SuccessBody<SaveMemberResponse>> login(
			@RequestBody MemberSignRequest request) {
		return ApiResponseGenerator.success(
				signMemberUseCase.execute(memberControllerConverter.to(request)), HttpStatus.CREATED);
	}

	@PostMapping("/tokens")
	public ApiResponse<ApiResponse.SuccessBody<Token>> refreshToken(
			@RequestHeader("X-REFRESH-TOKEN") String refreshToken) {
		return ApiResponseGenerator.success(getTokenUseCase.execute(refreshToken), HttpStatus.CREATED);
	}

	@GetMapping()
	public ApiResponse<ApiResponse.SuccessBody<Member>> readMember() {
		return ApiResponseGenerator.success(
				getMemberUseCase.execute(AuditorHolder.get()), HttpStatus.OK);
	}

	@GetMapping("/info")
	public ApiResponse<ApiResponse.SuccessBody<MemberInfo>> readMemberInfo(
			@RequestParam("email") String email) {
		return ApiResponseGenerator.success(getMemberUseCase.execute(email), HttpStatus.OK);
	}
}
