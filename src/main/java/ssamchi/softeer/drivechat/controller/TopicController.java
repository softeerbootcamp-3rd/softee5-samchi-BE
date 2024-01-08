package ssamchi.softeer.drivechat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Topic 컨트롤러", description = "Topic 관련 API 목록입니다.")
@RequestMapping("/api/topics")
@RequiredArgsConstructor
@RestController
public class TopicController {
    private final TopicService topicService;

    @Operation(summary = "전체 Topic Id 리스트 API", description = "서비스를 시작할 때 운전자/동승자 정보를 지정하는 과정에서 필요한 Topic Id 리스트 API 입니다.")
    @GetMapping
    public ResponseEntity<ResponseDto<List<AllTopicResponseDTO>>> getAllTopics() {
        List<AllTopicResponseDTO> topics = topicService.findAllTopics();
        ResponseDto<List<AllTopicResponseDTO>> response = ResponseDto.of(HttpStatus.OK.value(), "Success", topics);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Topic 랜덤 제시 API", description = "대화 주제를 랜덤으로 제시해주는 API 입니다.")
    @GetMapping("/content")
    public ResponseEntity<ResponseDto<TopicResponseDto>> getRandomContent(@RequestParam List<Long> ids) {
        TopicResponseDto topicResponseDto = topicService.getRandomContent(ids);
        ResponseDto<TopicResponseDto> response = ResponseDto.of(HttpStatus.OK.value(), "Success", topicResponseDto);
        return ResponseEntity.ok(response);
    }
}
