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
import ssamchi.softeer.drivechat.dto.request.RequestMakeMatchingDto;
import ssamchi.softeer.drivechat.dto.response.BoardRequestResponseDto;
import ssamchi.softeer.drivechat.dto.response.ResponseMakeMatchingDto;
import ssamchi.softeer.drivechat.dto.response.ResponseMatchCheckDto;
import ssamchi.softeer.drivechat.dto.response.SummaryResponseDto;
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
    public ResponseMakeMatchingDto makeMatching(RequestMakeMatchingDto requestMakeMatchingDto) {
        String nickname = HeaderUtils.getHeader("nickname");
        Long driverId = requestMakeMatchingDto.getDriverId();

        if (nickname == null || driverId == null)
            throw BusinessException.of(Error.BAD_REQUEST);

        User user = userRepository.findByUserName(nickname)
                .orElseThrow(() -> BusinessException.of(Error.USER_NOT_FOUND));

        Guest guest = guestRepository.findByUser_UserId(user.getUserId())
                .orElseThrow(() -> BusinessException.of(Error.GUEST_NOT_FOUND));

        Driver driver = driverRepository.findByDriverId(driverId)
                .orElseThrow(() -> BusinessException.of(Error.DRIVER_NOT_FOUND));

        Match newMatch = matchRepository.save(Match.builder()
                .guest(guest)
                .driver(driver)
                .content("") // 초기값
                .build()
        );

        // modify isFound
        driver.found();
        driverRepository.save(driver);

        return ResponseMakeMatchingDto.builder()
                .matchId(newMatch.getMatchId())
                .build();
    }

    public ResponseMatchCheckDto checkMatching(Long driverId) {
        Driver driver = driverRepository.findByDriverId(driverId)
                .orElseThrow(() -> BusinessException.of(Error.DRIVER_NOT_FOUND));

        return ResponseMatchCheckDto.builder()
                .isFound(driver.isFound())
                .build();
    }

    @Transactional
    public BoardRequestResponseDto boardRequest(BoardRequestRequestDto boardRequestRequestDto) {
        Long userId = Long.parseLong(HeaderUtils.getHeader("userid"));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> BusinessException.of(Error.USER_NOT_FOUND));

        Guest guest = guestRepository.findByUser_UserId(user.getUserId())
            .orElseThrow(() -> BusinessException.of(Error.GUEST_NOT_FOUND));

        Driver driver = driverRepository.findByDriverId(boardRequestRequestDto.getDriverId())
            .orElseThrow(() -> BusinessException.of(Error.DRIVER_NOT_FOUND));

        matchRepository.save(Match.builder()
                .driver(driver)
                .guest(guest)
                .content(null)
                .build());

        return BoardRequestResponseDto.builder()
                .success(true)
                .build();
    }
}
