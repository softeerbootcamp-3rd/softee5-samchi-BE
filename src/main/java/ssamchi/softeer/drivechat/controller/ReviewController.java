package ssamchi.softeer.drivechat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssamchi.softeer.drivechat.dto.request.ReviewRequestDto;
import ssamchi.softeer.drivechat.service.ReviewService;

@Tag(name = "Review 컨트롤러", description = "Review 관련 API 목록입니다.")
@RequestMapping("/api/review")
@RequiredArgsConstructor
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(summary = "드라이브 챗 종료 후 리뷰 API", description = "상대방에게 리뷰를 남기는 API 입니다.")
    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody ReviewRequestDto reviewRequestDto) {
        reviewService.makeReview(reviewRequestDto);
        return ResponseEntity.ok().build();
    }
}
