package jongjun.hairlog.app.web.controller.v1;

import java.util.List;
import jongjun.hairlog.app.domain.model.designer.Designer;
import jongjun.hairlog.app.domain.usecase.designer.GetDesignerUseCase;
import jongjun.hairlog.app.domain.usecase.designer.SaveDesignerUseCase;
import jongjun.hairlog.app.support.ApiResponse;
import jongjun.hairlog.app.support.ApiResponseGenerator;
import jongjun.hairlog.app.web.controller.request.designer.DesignerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/designers")
@RequiredArgsConstructor
@RestController
public class DesignerController {

	private final SaveDesignerUseCase saveDesignerUseCase;
	private final GetDesignerUseCase getDesignerUseCase;

	@PostMapping()
	public ApiResponse<ApiResponse.SuccessBody<Long>> addDesigner(
			@RequestBody DesignerRequest request) {
		return ApiResponseGenerator.success(saveDesignerUseCase.execute(request), HttpStatus.OK);
	}

	@GetMapping()
	public ApiResponse<ApiResponse.SuccessBody<List<Designer>>> readDesigners(
			@RequestParam("id") Long memberId,
			@RequestParam(value = "dn", required = false) String designerName) {
		if (designerName == null) {
			return ApiResponseGenerator.success(getDesignerUseCase.execute(memberId), HttpStatus.OK);
		}
		return ApiResponseGenerator.success(
				getDesignerUseCase.execute(memberId, designerName), HttpStatus.OK);
	}
}
