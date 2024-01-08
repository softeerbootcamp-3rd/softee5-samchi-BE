package ssamchi.softeer.drivechat.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssamchi.softeer.drivechat.domain.*;
import ssamchi.softeer.drivechat.dto.request.RequestDriverInfoDto;
import ssamchi.softeer.drivechat.dto.request.RequestGuestInfoDto;
import ssamchi.softeer.drivechat.dto.request.RequestUserRegisterDto;
import ssamchi.softeer.drivechat.dto.response.ResponseDriverInfoDto;
import ssamchi.softeer.drivechat.dto.response.ResponseGuestInfoDto;
import ssamchi.softeer.drivechat.dto.response.ResponseUserRegisterDto;
import ssamchi.softeer.drivechat.exception.BusinessException;
import ssamchi.softeer.drivechat.exception.Error;
import ssamchi.softeer.drivechat.repository.*;
import ssamchi.softeer.drivechat.utils.HeaderUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final GuestRepository guestRepository;
    private final GuestTopicRepository guestTopicRepository;
    private final DriverRepository driverRepository;
    private final DriverTopicRepository driverTopicRepository;

    @Transactional
    public ResponseUserRegisterDto registerUser(RequestUserRegisterDto requestUserRegisterDto) {
        String username = requestUserRegisterDto.getUsername();

        if (username == null)
            throw BusinessException.of(Error.INTERNAL_SERVER_ERROR);

        boolean isExistingUser = userRepository.findByUserName(username).isPresent();

//        이미 존재하는 닉네임일 떄의 처리?
        if (isExistingUser)
            throw BusinessException.of(Error.INTERNAL_SERVER_ERROR);

        User newUser = userRepository.save(User.builder()
                .userName(username)
                .build()
        );

        return ResponseUserRegisterDto.builder()
                .success(true)
                .userId(newUser.getUserId())
                .build();
    }

    @Transactional
    public ResponseDriverInfoDto registerDriverInfo(RequestDriverInfoDto requestDriverInfoDto) {
        Long userId = Long.parseLong(HeaderUtils.getHeader("userid"));
        List<Long> topicIds = requestDriverInfoDto.getTopicIds();

        if (topicIds == null)
            throw BusinessException.of(Error.BAD_REQUEST);


        User user = userRepository.findById(userId)
                .orElseThrow(() -> BusinessException.of(Error.USER_NOT_FOUND));

        // 운전자 생성시 받아야 하는 부분?
        Driver newDriver = driverRepository.save(Driver.builder()
                .user(user)
                .destinationAddress("강남역") // dummy
                .estimatedTime(LocalDateTime.now()) // dummy
                .isFound(false)
                .build()
        );

        List<Topic> driverTopics = topicRepository.findAllById(topicIds);

        for (Topic topic : driverTopics) {
            driverTopicRepository.save(DriverTopic.builder()
                    .driver(newDriver)
                    .topic(topic)
                    .build()
            );
        }

        return ResponseDriverInfoDto.builder()
                .success(true)
                .build();
    }

    @Transactional
    public ResponseGuestInfoDto registerGuestInfo(RequestGuestInfoDto requestGuestInfoDto) {
        Long userId = Long.parseLong(HeaderUtils.getHeader("userid"));
        List<Long> topicIds = requestGuestInfoDto.getTopicIds();

        if (topicIds == null)
            throw BusinessException.of(Error.BAD_REQUEST);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> BusinessException.of(Error.USER_NOT_FOUND));

        Guest newGuest = guestRepository.save(Guest.builder()
                .user(user)
                .reviewScore(0)
                .build()
        );

        List<Topic> guestTopics = topicRepository.findAllById(topicIds);

        for (Topic topic : guestTopics) {
            guestTopicRepository.save(GuestTopic.builder()
                    .guest(newGuest)
                    .topic(topic)
                    .build()
            );
        }

        return ResponseGuestInfoDto.builder()
                .success(true)
                .build();
    }
}
