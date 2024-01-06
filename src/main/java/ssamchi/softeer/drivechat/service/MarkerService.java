package ssamchi.softeer.drivechat.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssamchi.softeer.drivechat.domain.Driver;
import ssamchi.softeer.drivechat.domain.DriverTopic;
import ssamchi.softeer.drivechat.domain.Guest;
import ssamchi.softeer.drivechat.domain.User;
import ssamchi.softeer.drivechat.dto.request.RequestDriverMarkerMakeDto;
import ssamchi.softeer.drivechat.dto.response.DriverMarkerDetailInfoResponseDto;
import ssamchi.softeer.drivechat.dto.response.MainMapMarkerIdListResponseDto;
import ssamchi.softeer.drivechat.dto.response.ResponseDriverMarkerMakeDto;
import ssamchi.softeer.drivechat.exception.BusinessException;
import ssamchi.softeer.drivechat.exception.Error;
import ssamchi.softeer.drivechat.repository.DriverRepository;
import ssamchi.softeer.drivechat.repository.DriverTopicRepository;
import ssamchi.softeer.drivechat.repository.GuestRepository;
import ssamchi.softeer.drivechat.repository.UserRepository;
import ssamchi.softeer.drivechat.utils.HeaderUtils;

@Service
@RequiredArgsConstructor
public class MarkerService {
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final DriverTopicRepository driverTopicRepository;
    private final GuestRepository guestRepository;

    @Transactional
    public ResponseDriverMarkerMakeDto registrationDriverMarker(RequestDriverMarkerMakeDto requestDriverMarkerMakeDto) {
        String nickname = HeaderUtils.getHeader("nickname");
        User user = userRepository.findByUserName(nickname)
            .orElseThrow(() -> BusinessException.of(Error.USER_NOT_FOUND));

        Driver newDriver = driverRepository.save(Driver.builder()
            .user(user)
            .isFound(false)
            .destinationAddress(requestDriverMarkerMakeDto.getDestinationAddress())
            .estimatedTime(requestDriverMarkerMakeDto.getEstimateStartTime())
            .build()
        );

        return ResponseDriverMarkerMakeDto.builder()
            .success(true)
            .build();
    }





    public DriverMarkerDetailInfoResponseDto getDriverMarkerDetailInfo(Long markerId) {
        String nickname = HeaderUtils.getHeader("nickname");
        userRepository.findByUserName(nickname)
            .orElseThrow(() -> BusinessException.of(Error.USER_NOT_FOUND));

        Driver driver = driverRepository.findById(markerId)
            .orElseThrow(() -> BusinessException.of(Error.MARKER_NOT_FOUND));

        Long driveChatCount = driverRepository.countByUser_UserIdAndFoundIsTrue(driver.getUser().getUserId());
        List<DriverTopic> driverTopics = driverTopicRepository.findAllByDriver_DriverId(
            driver.getDriverId());

        List<String> topics = driverTopics.stream()
            .flatMap(driverTopic -> driverTopic.getTopic().getContentsList().stream())
            .collect(Collectors.toList());


        return DriverMarkerDetailInfoResponseDto.builder()
            .driverId(driver.getDriverId())
            .driverNickname(driver.getUser().getUserName())
            .driveChatCount(driveChatCount)
            .destination(driver.getDestinationAddress())
            .startTime(driver.getEstimatedTime())
            .driverTopics(topics)
            .build();
    }

    public MainMapMarkerIdListResponseDto searchDriverMarker(Boolean sort) {
        String nickname = HeaderUtils.getHeader("nickname");
        User user = userRepository.findByUserName(nickname)
            .orElseThrow(() -> BusinessException.of(Error.USER_NOT_FOUND));

        Guest guest = guestRepository.findByUser_UserId(user.getUserId())
            .orElseThrow(() -> BusinessException.of(Error.GUEST_NOT_FOUND));
        if (sort) {
            List<Long> sameTopicDriverIds = driverTopicRepository.findSameTopicDriverIdsByGuestId(guest.getGuestId());
            return MainMapMarkerIdListResponseDto.builder()
                .markerIdList(sameTopicDriverIds)
                .build();
        } else {
            List<Long> allDriverIds = driverRepository.findDriversByFoundIsFalse().stream().map(Driver::getDriverId).toList();
            return MainMapMarkerIdListResponseDto.builder()
                .markerIdList(allDriverIds)
                .build();
        }
    }
}
