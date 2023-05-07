package jongjun.hairlog.app.web.controller.v1;

import jongjun.hairlog.app.domain.model.record.Record;
import jongjun.hairlog.app.domain.model.record.RecordIndex;
import jongjun.hairlog.app.domain.usecase.record.DeleteRecordUseCase;
import jongjun.hairlog.app.domain.usecase.record.GetRecordUseCase;
import jongjun.hairlog.app.domain.usecase.record.SaveRecordUseCase;
import jongjun.hairlog.app.support.ApiResponse;
import jongjun.hairlog.app.support.ApiResponseGenerator;
import jongjun.hairlog.app.support.Page;
import jongjun.hairlog.app.web.controller.request.record.CutRecordEditRequest;
import jongjun.hairlog.app.web.controller.request.record.CutRecordRequest;
import jongjun.hairlog.app.web.controller.request.record.DyeingRecordEditRequest;
import jongjun.hairlog.app.web.controller.request.record.DyeingRecordRequest;
import jongjun.hairlog.app.web.controller.request.record.PermRecordEditRequest;
import jongjun.hairlog.app.web.controller.request.record.PermRecordRequest;
import jongjun.hairlog.data.enums.RecordCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/records")
@RequiredArgsConstructor
@RestController
public class RecordController {

	private final SaveRecordUseCase saveRecordUseCase;
	private final GetRecordUseCase getRecordUseCase;
	private final DeleteRecordUseCase deleteRecordUseCase;

	@PostMapping("/cut")
	public ApiResponse<ApiResponse.SuccessBody<Long>> addRecord(
			@RequestBody CutRecordRequest request) {
		return ApiResponseGenerator.success(saveRecordUseCase.execute(request), HttpStatus.OK);
	}

	@PostMapping("/perm")
	public ApiResponse<ApiResponse.SuccessBody<Long>> addRecord(
			@RequestBody PermRecordRequest request) {
		return ApiResponseGenerator.success(saveRecordUseCase.execute(request), HttpStatus.OK);
	}

	@PostMapping("/dyeing")
	public ApiResponse<ApiResponse.SuccessBody<Long>> addRecord(
			@RequestBody DyeingRecordRequest request) {
		return ApiResponseGenerator.success(saveRecordUseCase.execute(request), HttpStatus.OK);
	}

	@PatchMapping("/cut")
	public ApiResponse<ApiResponse.SuccessBody<Long>> editRecord(
			@RequestBody CutRecordEditRequest request) {
		return ApiResponseGenerator.success(saveRecordUseCase.execute(request), HttpStatus.OK);
	}

	@PatchMapping("/perm")
	public ApiResponse<ApiResponse.SuccessBody<Long>> editRecord(
			@RequestBody PermRecordEditRequest request) {
		return ApiResponseGenerator.success(saveRecordUseCase.execute(request), HttpStatus.OK);
	}

	@PatchMapping("/dyeing")
	public ApiResponse<ApiResponse.SuccessBody<Long>> editRecord(
			@RequestBody DyeingRecordEditRequest request) {
		return ApiResponseGenerator.success(saveRecordUseCase.execute(request), HttpStatus.OK);
	}

	@DeleteMapping()
	public ApiResponse<ApiResponse.SuccessBody<Long>> deleteRecord(
			@RequestParam("rid") Long recordId) {
		return ApiResponseGenerator.success(deleteRecordUseCase.execute(recordId), HttpStatus.OK);
	}

	@GetMapping("/pages")
	public ApiResponse<ApiResponse.SuccessBody<Page<RecordIndex>>> readRecordPage(
			@RequestParam("id") Long memberId,
			Pageable pageable,
			@RequestParam(value = "c", required = false) String category) {
		if (category == null) {
			return ApiResponseGenerator.success(
					getRecordUseCase.execute(memberId, pageable), HttpStatus.OK);
		}
		return ApiResponseGenerator.success(
				getRecordUseCase.execute(
						memberId, RecordCategory.valueOf(category.toUpperCase()), pageable),
				HttpStatus.OK);
	}

	@GetMapping()
	public ApiResponse<ApiResponse.SuccessBody<Record>> readRecord(
			@RequestParam("id") Long memberId,
			@RequestParam("rid") Long recordId,
			@RequestParam("c") String category) {
		return ApiResponseGenerator.success(
				getRecordUseCase.execute(
						memberId, recordId, RecordCategory.valueOf(category.toUpperCase())),
				HttpStatus.OK);
	}
}
