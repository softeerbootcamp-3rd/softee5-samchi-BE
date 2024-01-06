package ssamchi.softeer.drivechat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssamchi.softeer.drivechat.domain.Driver;
import ssamchi.softeer.drivechat.domain.DriverTopic;
import ssamchi.softeer.drivechat.domain.Topic;
import ssamchi.softeer.drivechat.domain.User;
import ssamchi.softeer.drivechat.dto.request.DriverTopicDTO;
import ssamchi.softeer.drivechat.exception.BusinessException;
import ssamchi.softeer.drivechat.exception.Error;
import ssamchi.softeer.drivechat.repository.DriverRepository;
import ssamchi.softeer.drivechat.repository.DriverTopicRepository;
import ssamchi.softeer.drivechat.repository.TopicRepository;
import ssamchi.softeer.drivechat.repository.UserRepository;
import ssamchi.softeer.drivechat.utils.HeaderUtils;

@Service
@RequiredArgsConstructor
public class DriverTopicService {

    private final DriverTopicRepository driverTopicRepository;
    private final TopicRepository topicRepository;
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;

    public void saveDriverTopics(DriverTopicDTO driverTopicDTO) {
//        String nickname = HeaderUtils.getHeader("nickname");
//        User user = userRepository.findByUserName(nickname)
//                .orElseThrow(() -> BusinessException.of(Error.USER_NOT_FOUND));

        Driver driver = driverRepository.findByDriverId(driverTopicDTO.getDriverId())
                .orElseThrow(() -> BusinessException.of(Error.MARKER_NOT_FOUND));

        driverTopicDTO.getTopicIds().forEach(topicId -> {
            Topic topic = topicRepository.findById(topicId)
                    .orElseThrow(() -> BusinessException.of(Error.TOPIC_NOT_FOUND));
            DriverTopic driverTopic = new DriverTopic(driver, topic);
            driverTopicRepository.save(driverTopic);
        });
    }
}