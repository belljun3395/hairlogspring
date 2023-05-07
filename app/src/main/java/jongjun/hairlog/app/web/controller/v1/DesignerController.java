package jongjun.hairlog.app.web.controller.v1;

import java.util.List;
import jongjun.hairlog.app.domain.model.designer.Designer;
import jongjun.hairlog.app.domain.usecase.designer.DeleteDesignerUseCase;
import jongjun.hairlog.app.domain.usecase.designer.GetDesignerUseCase;
import jongjun.hairlog.app.domain.usecase.designer.SaveDesignerUseCase;
import jongjun.hairlog.app.support.ApiResponse;
import jongjun.hairlog.app.support.ApiResponseGenerator;
import jongjun.hairlog.app.web.controller.request.designer.DesignerIdParam;
import jongjun.hairlog.app.web.controller.request.designer.DesignerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
	private final DeleteDesignerUseCase deleteDesignerUseCase;

	@PostMapping()
	public ApiResponse<ApiResponse.SuccessBody<Long>> addDesigner(
			@RequestBody DesignerRequest request) {
		return ApiResponseGenerator.success(saveDesignerUseCase.execute(request), HttpStatus.OK);
	}

	@DeleteMapping()
	public ApiResponse<ApiResponse.SuccessBody<Long>> deleteDesigner(
			@RequestParam("did") Long designerId) {
		return ApiResponseGenerator.success(deleteDesignerUseCase.execute(designerId), HttpStatus.OK);
	}

	@PatchMapping()
	public ApiResponse<ApiResponse.SuccessBody<Long>> editDesignerFav(
			@RequestParam("did") Long designerId, @RequestParam("fav") Boolean fav) {
		return ApiResponseGenerator.success(
				saveDesignerUseCase.execute(designerId, fav), HttpStatus.OK);
	}

	@GetMapping()
	public ApiResponse<ApiResponse.SuccessBody<List<Designer>>> readDesigners(
			@RequestParam(value = "dn", required = false) String designerName) {
		if (designerName == null) {
			return ApiResponseGenerator.success(getDesignerUseCase.execute(), HttpStatus.OK);
		}
		return ApiResponseGenerator.success(getDesignerUseCase.execute(designerName), HttpStatus.OK);
	}

	@GetMapping("/info")
	public ApiResponse<ApiResponse.SuccessBody<Designer>> readDesigner(
			@RequestParam("did") Long designerId) {
		return ApiResponseGenerator.success(
				getDesignerUseCase.execute(DesignerIdParam.builder().designerId(designerId).build()),
				HttpStatus.OK);
	}
}
