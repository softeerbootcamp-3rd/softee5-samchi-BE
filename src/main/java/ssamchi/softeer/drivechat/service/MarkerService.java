package ssamchi.softeer.drivechat.service;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssamchi.softeer.drivechat.domain.Driver;
import ssamchi.softeer.drivechat.domain.DriverTopic;
import ssamchi.softeer.drivechat.domain.User;
import ssamchi.softeer.drivechat.dto.request.RequestDriverMarkerMakeDto;
import ssamchi.softeer.drivechat.dto.response.DriverMarkerDetailInfoResponseDto;
import ssamchi.softeer.drivechat.dto.response.ResponseDriverMarkerMakeDto;
import ssamchi.softeer.drivechat.exception.BusinessException;
import ssamchi.softeer.drivechat.exception.Error;
import ssamchi.softeer.drivechat.repository.DriverRepository;
import ssamchi.softeer.drivechat.repository.DriverTopicRepository;
import ssamchi.softeer.drivechat.repository.UserRepository;
import ssamchi.softeer.drivechat.utils.HeaderUtils;

@Service
@RequiredArgsConstructor
public class MarkerService {
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final DriverTopicRepository driverTopicRepository;

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
        List<DriverTopic> driverTopics = driverTopicRepository.findAllByDriverTopicId(
            driver.getDriverId());

        List<String> topics = driverTopics.stream()
            .map(driverTopic -> driverTopic.getTopic().getContentsList())
            .toList()
            .stream().flatMap(List::stream).toList();

        return DriverMarkerDetailInfoResponseDto.builder()
            .driverId(driver.getDriverId())
            .driverNickname(driver.getUser().getUserName())
            .driveChatCount(driveChatCount)
            .destination(driver.getDestinationAddress())
            .startTime(driver.getEstimatedTime())
            .driverTopics(topics)
            .build();
    }
}
