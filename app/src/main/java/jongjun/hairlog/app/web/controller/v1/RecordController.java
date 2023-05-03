package jongjun.hairlog.app.web.controller.v1;

import jongjun.hairlog.app.domain.model.record.Record;
import jongjun.hairlog.app.domain.model.record.RecordIndex;
import jongjun.hairlog.app.domain.usecase.record.GetRecordUseCase;
import jongjun.hairlog.app.domain.usecase.record.SaveRecordUseCase;
import jongjun.hairlog.app.support.ApiResponse;
import jongjun.hairlog.app.support.ApiResponseGenerator;
import jongjun.hairlog.app.support.Page;
import jongjun.hairlog.app.web.controller.request.record.CutRecordRequest;
import jongjun.hairlog.app.web.controller.request.record.DyeingRecordRequest;
import jongjun.hairlog.app.web.controller.request.record.PermRecordRequest;
import jongjun.hairlog.app.web.controller.request.record.RecordRequest;
import jongjun.hairlog.data.enums.RecordCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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

	@PostMapping()
	public ApiResponse<ApiResponse.SuccessBody<Long>> addRecord(
			@RequestBody RecordRequest request, @RequestParam("c") String category) {
		RecordCategory recordCategory = RecordCategory.valueOf(category.toUpperCase());
		if ((RecordCategory.CUT.equals(recordCategory))) {
			return ApiResponseGenerator.success(
					saveRecordUseCase.execute((CutRecordRequest) request), HttpStatus.OK);
		} else if (RecordCategory.PERM.equals(recordCategory)) {
			return ApiResponseGenerator.success(
					saveRecordUseCase.execute((PermRecordRequest) request), HttpStatus.OK);
		} else {
			return ApiResponseGenerator.success(
					saveRecordUseCase.execute((DyeingRecordRequest) request), HttpStatus.OK);
		}
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
