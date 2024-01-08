package ssamchi.softeer.drivechat.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssamchi.softeer.drivechat.domain.Driver;
import ssamchi.softeer.drivechat.domain.DriverTopic;
import ssamchi.softeer.drivechat.domain.Guest;
import ssamchi.softeer.drivechat.domain.GuestTopic;
import ssamchi.softeer.drivechat.domain.Topic;
import ssamchi.softeer.drivechat.domain.User;
import ssamchi.softeer.drivechat.dto.common.UserType;
import ssamchi.softeer.drivechat.dto.request.DriverChatRegistrationRequestDto;
import ssamchi.softeer.drivechat.dto.response.DriverMarkerDetailInfoResponseDto;
import ssamchi.softeer.drivechat.dto.response.MainMapMarkerIdListResponseDto;
import ssamchi.softeer.drivechat.dto.response.DriveChatRegistrationResponseDto;
import ssamchi.softeer.drivechat.exception.BusinessException;
import ssamchi.softeer.drivechat.exception.Error;
import ssamchi.softeer.drivechat.repository.DriverRepository;
import ssamchi.softeer.drivechat.repository.DriverTopicRepository;
import ssamchi.softeer.drivechat.repository.GuestRepository;
import ssamchi.softeer.drivechat.repository.GuestTopicRepository;
import ssamchi.softeer.drivechat.repository.TopicRepository;
import ssamchi.softeer.drivechat.repository.UserRepository;
import ssamchi.softeer.drivechat.utils.HeaderUtils;

@Service
@RequiredArgsConstructor
public class MarkerService {
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final DriverTopicRepository driverTopicRepository;
    private final GuestTopicRepository guestTopicRepository;
    private final GuestRepository guestRepository;
    private final TopicRepository topicRepository;

    @Transactional
    public DriveChatRegistrationResponseDto registrationDriverChat(DriverChatRegistrationRequestDto driverChatRegistrationRequestDto) {
        Long userId = Long.parseLong(HeaderUtils.getHeader("userid"));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> BusinessException.of(Error.USER_NOT_FOUND));

        UserType userType = driverChatRegistrationRequestDto.getUserType();
        List<Topic> topics = topicRepository.findAllByTopicIdIn(driverChatRegistrationRequestDto.getTopicIds());

        if (userType.equals(UserType.DRIVER)) {
            Driver driver = driverRepository.save(Driver.builder()
                .user(user)
                .destinationAddress(driverChatRegistrationRequestDto.getDestinationAddress())
                .estimatedTime(driverChatRegistrationRequestDto.getEstimateStartTime())
                .isFound(false)
                .build());
            driverTopicRepository.saveAll(topics.stream()
                .map(tp -> DriverTopic.builder()
                    .driver(driver)
                    .topic(tp)
                    .build())
                .collect(Collectors.toList()));
            return DriveChatRegistrationResponseDto.builder()
                .driverId(driver.getDriverId())
                .guestId(null)
                .userType(UserType.DRIVER)
                .build();
        } else {
            Guest guest = guestRepository.save(Guest.builder()
                .user(user)
                .reviewScore(0)
                .build());
            guestTopicRepository.saveAll(topics.stream()
                .map(tp -> GuestTopic.builder()
                    .guest(guest)
                    .topic(tp)
                    .build())
                .collect(Collectors.toList()));
            return DriveChatRegistrationResponseDto.builder()
                .driverId(null)
                .guestId(guest.getGuestId())
                .userType(UserType.GUEST)
                .build();
        }
    }


    public DriverMarkerDetailInfoResponseDto getDriverMarkerDetailInfo(Long markerId) {
        Long userId = Long.parseLong(HeaderUtils.getHeader("userid"));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> BusinessException.of(Error.USER_NOT_FOUND));

        Driver driver = driverRepository.findById(markerId)
            .orElseThrow(() -> BusinessException.of(Error.DRIVER_NOT_FOUND));

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
        Long userId = Long.parseLong(HeaderUtils.getHeader("userid"));
        User user = userRepository.findById(userId)
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
