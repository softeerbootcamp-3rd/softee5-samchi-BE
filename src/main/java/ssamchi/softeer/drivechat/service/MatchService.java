package ssamchi.softeer.drivechat.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssamchi.softeer.drivechat.domain.Driver;
import ssamchi.softeer.drivechat.domain.Guest;
import ssamchi.softeer.drivechat.domain.Match;
import ssamchi.softeer.drivechat.domain.User;
import ssamchi.softeer.drivechat.dto.request.BoardRequestRequestDto;
import ssamchi.softeer.drivechat.dto.request.ConversationRequestDto;
import ssamchi.softeer.drivechat.dto.request.RequestConfirmMatchingDto;
import ssamchi.softeer.drivechat.dto.response.*;
import ssamchi.softeer.drivechat.exception.BusinessException;
import ssamchi.softeer.drivechat.exception.Error;
import ssamchi.softeer.drivechat.repository.DriverRepository;
import ssamchi.softeer.drivechat.repository.GuestRepository;
import ssamchi.softeer.drivechat.repository.MatchRepository;
import ssamchi.softeer.drivechat.repository.UserRepository;
import ssamchi.softeer.drivechat.utils.HeaderUtils;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    private final UserRepository userRepository;
    private final GuestRepository guestRepository;
    private final DriverRepository driverRepository;

    @Transactional
    public SummaryResponseDto conversationSummary(Long matchId, ConversationRequestDto conversation) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid matchId: " + matchId));
        // conversation -> summary 생성 생략
        String summaryResult = "요약요약요.";
        match.makeContentSummary(summaryResult);
        matchRepository.save(match);

        return SummaryResponseDto.builder()
                .contents(summaryResult)
                .build();
    }

    @Transactional
    public ResponseConfirmMatchingDto confirmMatchCheck(RequestConfirmMatchingDto requestConfirmMatchingDto) {
        Long userId = Long.parseLong(HeaderUtils.getHeader("userid"));
        Match match = matchRepository.findById(requestConfirmMatchingDto.getMatchingId())
            .orElseThrow(() -> BusinessException.of(Error.MATCH_NOT_FOUND));

        // modify isFound
        match.getDriver().found();
        match.confirmMatched();
        matchRepository.save(match);
        driverRepository.save(match.getDriver());

        Long driverCount = driverRepository.countByUser_UserIdAndFoundIsTrue(userId);

        return ResponseConfirmMatchingDto.builder()
                .matchId(requestConfirmMatchingDto.getMatchingId())
                .guest(match.getGuest())
                .driver(match.getDriver())
                .content(null)
                .driverCount(driverCount)
                .build();
    }

    public ResponseMatchCheckDto checkMatching(Long driverId) {
        Driver driver = driverRepository.findByDriverId(driverId)
                .orElseThrow(() -> BusinessException.of(Error.DRIVER_NOT_FOUND));

        return ResponseMatchCheckDto.builder()
                .isFound(driver.isFound())
                .build();
    }

    public ResponseBoardCheckDto boardCheck(Long matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> BusinessException.of(Error.MATCH_NOT_FOUND));

        return ResponseBoardCheckDto.builder()
                .isBoarding(match.getIsMatched())
                .build();
    }

    @Transactional
    public BoardRequestResponseDto boardRequest(BoardRequestRequestDto boardRequestRequestDto) {
        Long userId = Long.parseLong(HeaderUtils.getHeader("userid"));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> BusinessException.of(Error.USER_NOT_FOUND));

        Guest guest = guestRepository.findDistinctTopByUser_UserIdOrderByCreatedAtDesc(user.getUserId())
            .orElseThrow(() -> BusinessException.of(Error.GUEST_NOT_FOUND));

        Driver driver = driverRepository.findByDriverId(boardRequestRequestDto.getDriverId())
            .orElseThrow(() -> BusinessException.of(Error.DRIVER_NOT_FOUND));

        Match newMatch = matchRepository.save(Match.builder()
                .driver(driver)
                .guest(guest)
                .content(null)
                .build());

        return BoardRequestResponseDto.builder()
                .success(true)
                .matchId(newMatch.getMatchId())
                .build();
    }
}
