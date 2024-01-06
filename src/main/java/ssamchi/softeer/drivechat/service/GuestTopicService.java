package ssamchi.softeer.drivechat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssamchi.softeer.drivechat.domain.Guest;
import ssamchi.softeer.drivechat.domain.GuestTopic;
import ssamchi.softeer.drivechat.domain.Topic;
import ssamchi.softeer.drivechat.dto.request.GuestTopicRequestDTO;
import ssamchi.softeer.drivechat.exception.BusinessException;
import ssamchi.softeer.drivechat.exception.Error;
import ssamchi.softeer.drivechat.repository.GuestRepository;
import ssamchi.softeer.drivechat.repository.GuestTopicRepository;
import ssamchi.softeer.drivechat.repository.TopicRepository;
import ssamchi.softeer.drivechat.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class GuestTopicService {

    private final GuestTopicRepository guestTopicRepository;
    private final TopicRepository topicRepository;
    private final GuestRepository guestRepository;
    private final UserRepository userRepository;

    public void saveGuestTopics(GuestTopicRequestDTO guestTopicRequestDTO) {
//        String nickname = HeaderUtils.getHeader("nickname");
//        User user = userRepository.findByUserName(nickname)
//                .orElseThrow(() -> BusinessException.of(Error.USER_NOT_FOUND));

        Guest guest = guestRepository.findByGuestId(guestTopicRequestDTO.getGuestId())
                .orElseThrow(() -> BusinessException.of(Error.GUEST_NOT_FOUND));

        guestTopicRequestDTO.getTopicIds().forEach(topicId -> {
            Topic topic = topicRepository.findById(topicId)
                    .orElseThrow(() -> BusinessException.of(Error.TOPIC_NOT_FOUND));
            GuestTopic guestTopic = new GuestTopic(guest, topic);
            guestTopicRepository.save(guestTopic);
        });
    }
}
