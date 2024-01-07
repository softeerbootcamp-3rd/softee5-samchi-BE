package ssamchi.softeer.drivechat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssamchi.softeer.drivechat.domain.Topic;
import ssamchi.softeer.drivechat.dto.request.TopicRequestDto;
import ssamchi.softeer.drivechat.dto.response.AllTopicResponseDTO;
import ssamchi.softeer.drivechat.dto.response.TopicResponseDto;
import ssamchi.softeer.drivechat.repository.TopicRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final Map<Long, String> lastContentMap = new HashMap<>();

    public List<AllTopicResponseDTO> findAllTopics() {
        return topicRepository.findAll().stream()
                .map(topic -> new AllTopicResponseDTO(topic.getTopicId(), topic.getName()))
                .collect(Collectors.toList());
    }

    public TopicResponseDto getRandomContent(TopicRequestDto requestDto) {
        List<Topic> topics = topicRepository.findAllById(requestDto.getIds());

        List<String> possibleContents = new ArrayList<>();
        for (Topic topic : topics) {
            List<String> contentsList = topic.getContentsList();
            contentsList.remove(lastContentMap.get(topic.getTopicId())); // Remove last content if exists
            possibleContents.addAll(contentsList);
        }

        if (possibleContents.isEmpty()) {
            return new TopicResponseDto(""); // Or handle this case as you see fit
        }

        String selectedContent = possibleContents.get(new Random().nextInt(possibleContents.size()));
        updateLastContentMap(topics, selectedContent);

        return new TopicResponseDto(selectedContent);
    }

    private void updateLastContentMap(List<Topic> topics, String selectedContent) {
        for (Topic topic : topics) {
            if (topic.getContentsList().contains(selectedContent)) {
                lastContentMap.put(topic.getTopicId(), selectedContent);
                break;
            }
        }
    }
}