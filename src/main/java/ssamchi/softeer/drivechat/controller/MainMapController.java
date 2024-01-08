package ssamchi.softeer.drivechat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ssamchi.softeer.drivechat.dto.common.ResponseDto;
import ssamchi.softeer.drivechat.dto.request.DriverChatRegistrationRequestDto;
import ssamchi.softeer.drivechat.dto.response.DriverMarkerDetailInfoResponseDto;
import ssamchi.softeer.drivechat.dto.response.MainMapMarkerIdListResponseDto;
import ssamchi.softeer.drivechat.dto.response.DriveChatRegistrationResponseDto;
import ssamchi.softeer.drivechat.service.MarkerService;

@RestController
@RequestMapping(value = "/api/main-map")
@RequiredArgsConstructor
public class MainMapController {
    private final MarkerService markerService;

    @PostMapping(value = "/registration")
    public ResponseEntity<ResponseDto<DriveChatRegistrationResponseDto>> registrationDriverChat
        (
            @RequestBody DriverChatRegistrationRequestDto driverChatRegistrationRequestDto
        )
    {
        DriveChatRegistrationResponseDto driveChatRegistrationResponseDto = markerService.registrationDriverChat(driverChatRegistrationRequestDto);
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK.value(), "드라이브 챗 등록이 완료되었습니다.",
                driveChatRegistrationResponseDto));
    }

    @GetMapping(value = "/search")
    public ResponseEntity<ResponseDto<MainMapMarkerIdListResponseDto>> searchDriverMarker
        (
            @RequestParam Boolean sort
        )
    {
        MainMapMarkerIdListResponseDto MainMapMarkerIdListResponseDto = markerService.searchDriverMarker(sort);
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK.value(), "마커 조회가 완료되었습니다.", MainMapMarkerIdListResponseDto));
    }

    @GetMapping(value = "/detail/{markerId}")
    public ResponseEntity<ResponseDto<DriverMarkerDetailInfoResponseDto>> getDriverMarkerDetailInfo
        (
            @PathVariable Long markerId
        )
    {
        DriverMarkerDetailInfoResponseDto driverMarkerDetailInfo = markerService.getDriverMarkerDetailInfo(markerId);
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK.value(), "마커 상세 조회가 완료되었습니다.", driverMarkerDetailInfo));
    }
}
