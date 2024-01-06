package ssamchi.softeer.drivechat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ssamchi.softeer.drivechat.dto.common.ResponseDto;
import ssamchi.softeer.drivechat.dto.response.TopicDTO;
import ssamchi.softeer.drivechat.service.TopicService;

import java.util.List;

@RequestMapping("/api/topics")
@RequiredArgsConstructor
@Controller
public class TopicController {
    private final TopicService topicService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<TopicDTO>>> getAllTopics() {
        List<TopicDTO> topics = topicService.findAllTopics();
        ResponseDto<List<TopicDTO>> response = ResponseDto.of(HttpStatus.OK.value(), "Success", topics);
        return ResponseEntity.ok(response);
    }


}
