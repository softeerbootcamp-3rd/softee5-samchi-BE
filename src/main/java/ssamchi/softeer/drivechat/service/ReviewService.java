package ssamchi.softeer.drivechat.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssamchi.softeer.drivechat.domain.Review;
import ssamchi.softeer.drivechat.domain.User;
import ssamchi.softeer.drivechat.dto.request.ReviewRequestDto;
import ssamchi.softeer.drivechat.exception.BusinessException;
import ssamchi.softeer.drivechat.exception.Error;
import ssamchi.softeer.drivechat.repository.ReviewRepository;
import ssamchi.softeer.drivechat.repository.UserRepository;
import ssamchi.softeer.drivechat.utils.HeaderUtils;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public void makeReview(ReviewRequestDto reviewRequestDto) {
        Long userId = Long.parseLong(HeaderUtils.getHeader("userid"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> BusinessException.of(Error.USER_NOT_FOUND));

        Review review = Review.builder()
                .user(user)
                .content(reviewRequestDto.getContent())
                .build();
        reviewRepository.save(review);
    }
}
