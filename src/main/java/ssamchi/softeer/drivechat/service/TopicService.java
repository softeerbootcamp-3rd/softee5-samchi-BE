package ssamchi.softeer.drivechat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssamchi.softeer.drivechat.dto.response.AllTopicResponseDTO;
import ssamchi.softeer.drivechat.repository.TopicRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    public List<AllTopicResponseDTO> findAllTopics() {
        return topicRepository.findAll().stream()
                .map(topic -> new AllTopicResponseDTO(topic.getTopicId(), topic.getName()))
                .collect(Collectors.toList());
    }
}