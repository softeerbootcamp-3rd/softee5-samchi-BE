package ssamchi.softeer.drivechat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssamchi.softeer.drivechat.dto.common.ResponseDto;
import ssamchi.softeer.drivechat.dto.request.DriverTopicRequestDTO;
import ssamchi.softeer.drivechat.dto.request.GuestTopicRequestDTO;
import ssamchi.softeer.drivechat.dto.response.AllTopicResponseDTO;
import ssamchi.softeer.drivechat.service.DriverTopicService;
import ssamchi.softeer.drivechat.service.GuestTopicService;
import ssamchi.softeer.drivechat.service.TopicService;

import java.util.List;

@RequestMapping("/api/topics")
@RequiredArgsConstructor
@RestController
public class TopicController {
    private final TopicService topicService;
    private final DriverTopicService driverTopicService;
    private final GuestTopicService guestTopicService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<AllTopicResponseDTO>>> getAllTopics() {
        List<AllTopicResponseDTO> topics = topicService.findAllTopics();
        ResponseDto<List<AllTopicResponseDTO>> response = ResponseDto.of(HttpStatus.OK.value(), "Success", topics);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/driver")
    public ResponseEntity<?> saveDriverTopics(@RequestBody DriverTopicRequestDTO driverTopicRequestDTO) {
        driverTopicService.saveDriverTopics(driverTopicRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/guest")
    public ResponseEntity<?> saveGuestTopics(@RequestBody GuestTopicRequestDTO guestTopicRequestDTO) {
        guestTopicService.saveGuestTopics(guestTopicRequestDTO);
        return ResponseEntity.ok().build();
    }


}
