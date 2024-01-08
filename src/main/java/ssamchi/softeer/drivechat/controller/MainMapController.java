package ssamchi.softeer.drivechat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
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
import ssamchi.softeer.drivechat.dto.response.FilteredMarkerIdListResponseDto;
import ssamchi.softeer.drivechat.dto.response.MainMapMarkerIdListResponseDto;
import ssamchi.softeer.drivechat.dto.response.DriveChatRegistrationResponseDto;
import ssamchi.softeer.drivechat.service.MarkerService;

@Tag(name = "Main Map 컨트롤러", description = "메인 지도 관련 API 목록입니다.")
@RestController
@RequestMapping(value = "/api/main-map")
@RequiredArgsConstructor
public class MainMapController {
    private final MarkerService markerService;

    @Operation(summary = "드라이브 챗 시작하기 API", description = "서비스를 시작할 때 운전자/동승자 정보를 지정하는 API 입니다.")
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

    @Operation(summary = "메인 지도 전체 마커 리스트 API", description = "서비스를 시작할 때 전체 마커 리스트를 조회합니다.")
    @GetMapping(value = "/all")
    public ResponseEntity<ResponseDto<MainMapMarkerIdListResponseDto>> getAllDriverMarker()
    {
        MainMapMarkerIdListResponseDto mainMapMarkerIdListResponseDto = markerService.getAllDriverIds();
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK.value(), "마커 전체 조회가 완료되었습니다.", mainMapMarkerIdListResponseDto));
    }

    @Operation(summary = "필터링 마커 리스트 API", description = "관심사 선택 이후 마커 리스트를 조회합니다.")
    @GetMapping(value = "/filter")
    public ResponseEntity<ResponseDto<List<FilteredMarkerIdListResponseDto>>> getFilteredDriverMarker()
    {
        List<FilteredMarkerIdListResponseDto> filteredMarkerIdListResponseDtos = markerService.getFilteredMarkerInfos();
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK.value(), "필터링된 마커 조회가 완료되었습니다.", filteredMarkerIdListResponseDtos));
    }

    @Operation(summary = "마커 상세정보 조회 API", description = "해당 마커에 대한 상세 정보를 조회합니다.")
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
