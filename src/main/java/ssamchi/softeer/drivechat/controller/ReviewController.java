package ssamchi.softeer.drivechat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssamchi.softeer.drivechat.dto.request.ReviewRequestDto;
import ssamchi.softeer.drivechat.service.ReviewService;

@RequestMapping("/api/review")
@RequiredArgsConstructor
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody ReviewRequestDto reviewRequestDto) {
        reviewService.makeReview(reviewRequestDto);
        return ResponseEntity.ok().build();
    }
}
