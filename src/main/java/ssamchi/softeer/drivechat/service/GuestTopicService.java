package ssamchi.softeer.drivechat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssamchi.softeer.drivechat.domain.Guest;
import ssamchi.softeer.drivechat.domain.GuestTopic;
import ssamchi.softeer.drivechat.domain.Topic;
import ssamchi.softeer.drivechat.domain.User;
import ssamchi.softeer.drivechat.dto.request.GuestTopicDTO;
import ssamchi.softeer.drivechat.exception.BusinessException;
import ssamchi.softeer.drivechat.exception.Error;
import ssamchi.softeer.drivechat.repository.GuestRepository;
import ssamchi.softeer.drivechat.repository.GuestTopicRepository;
import ssamchi.softeer.drivechat.repository.TopicRepository;
import ssamchi.softeer.drivechat.repository.UserRepository;
import ssamchi.softeer.drivechat.utils.HeaderUtils;

@Service
@RequiredArgsConstructor
public class GuestTopicService {

    private final GuestTopicRepository guestTopicRepository;
    private final TopicRepository topicRepository;
    private final GuestRepository guestRepository;
    private final UserRepository userRepository;

    public void saveGuestTopics(GuestTopicDTO guestTopicDTO) {
//        String nickname = HeaderUtils.getHeader("nickname");
//        User user = userRepository.findByUserName(nickname)
//                .orElseThrow(() -> BusinessException.of(Error.USER_NOT_FOUND));

        Guest guest = guestRepository.findByGuestId(guestTopicDTO.getGuestId())
                .orElseThrow(() -> BusinessException.of(Error.GUEST_NOT_FOUND));

        guestTopicDTO.getTopicIds().forEach(topicId -> {
            Topic topic = topicRepository.findById(topicId)
                    .orElseThrow(() -> BusinessException.of(Error.TOPIC_NOT_FOUND));
            GuestTopic guestTopic = new GuestTopic(guest, topic);
            guestTopicRepository.save(guestTopic);
        });
    }
}
