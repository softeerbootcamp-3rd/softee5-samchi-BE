package ssamchi.softeer.drivechat.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssamchi.softeer.drivechat.domain.Match;
import ssamchi.softeer.drivechat.domain.Review;
import ssamchi.softeer.drivechat.domain.User;
import ssamchi.softeer.drivechat.dto.request.ReviewRequestDto;
import ssamchi.softeer.drivechat.exception.BusinessException;
import ssamchi.softeer.drivechat.exception.Error;
import ssamchi.softeer.drivechat.repository.MatchRepository;
import ssamchi.softeer.drivechat.repository.ReviewRepository;
import ssamchi.softeer.drivechat.repository.UserRepository;
import ssamchi.softeer.drivechat.utils.HeaderUtils;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public void makeReview(ReviewRequestDto reviewRequestDto) {
        User user = userRepository.findById(reviewRequestDto.getUserId())
                .orElseThrow(() -> BusinessException.of(Error.USER_NOT_FOUND));

        Match match = matchRepository.findById(reviewRequestDto.getMatchId())
                .orElseThrow(() -> BusinessException.of(Error.MATCH_NOT_FOUND));

        Review review = Review.builder()
                .user(user)
                .match(match)
                .content(reviewRequestDto.getContent())
                .build();
        reviewRepository.save(review);
    }
}
