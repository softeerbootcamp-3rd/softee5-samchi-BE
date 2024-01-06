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
import ssamchi.softeer.drivechat.dto.request.RequestDriverMarkerMakeDto;
import ssamchi.softeer.drivechat.dto.response.DriverMarkerDetailInfoResponseDto;
import ssamchi.softeer.drivechat.dto.response.ResponseDriverMarkerMakeDto;
import ssamchi.softeer.drivechat.service.MarkerService;

@RestController
@RequestMapping(value = "/api/main-map")
@RequiredArgsConstructor
public class MainMapController {
    private final MarkerService markerService;

    @PostMapping(value = "/registration")
    public ResponseEntity<ResponseDto<ResponseDriverMarkerMakeDto>> registrationDriverMarker
        (
            @RequestBody RequestDriverMarkerMakeDto requestDriverMarkerMakeDto
        )
    {
        ResponseDriverMarkerMakeDto responseDriverMarkerMakeDto = markerService.registrationDriverMarker(requestDriverMarkerMakeDto);
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK.value(), "마커 등록이 완료되었습니다.", responseDriverMarkerMakeDto));
    }

//    @GetMapping(value = "/search")
//    public ResponseEntity<ResponseDto<?>> searchDriverMarker
//        (
//            @RequestParam(value = "destinationAddress") String destinationAddress
//        )
//    {
//        return ResponseEntity.ok()
//            .body(ResponseDto.of(HttpStatus.OK.value(), "마커 조회가 완료되었습니다.", null));
//    }

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