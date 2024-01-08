package ssamchi.softeer.drivechat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssamchi.softeer.drivechat.dto.common.ResponseDto;
import ssamchi.softeer.drivechat.dto.request.DriverTopicRequestDTO;
import ssamchi.softeer.drivechat.dto.request.GuestTopicRequestDTO;
import ssamchi.softeer.drivechat.dto.response.AllTopicResponseDTO;
import ssamchi.softeer.drivechat.dto.response.TopicResponseDto;
import ssamchi.softeer.drivechat.service.TopicService;

import java.util.List;

@RequestMapping("/api/topics")
@RequiredArgsConstructor
@RestController
public class TopicController {
    private final TopicService topicService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<AllTopicResponseDTO>>> getAllTopics() {
        List<AllTopicResponseDTO> topics = topicService.findAllTopics();
        ResponseDto<List<AllTopicResponseDTO>> response = ResponseDto.of(HttpStatus.OK.value(), "Success", topics);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/content")
    public ResponseEntity<ResponseDto<TopicResponseDto>> getRandomContent(@RequestParam List<Long> ids) {
        TopicResponseDto topicResponseDto = topicService.getRandomContent(ids);
        ResponseDto<TopicResponseDto> response = ResponseDto.of(HttpStatus.OK.value(), "Success", topicResponseDto);
        return ResponseEntity.ok(response);
    }
}
