package ssamchi.softeer.drivechat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssamchi.softeer.drivechat.dto.common.ResponseDto;
import ssamchi.softeer.drivechat.dto.request.RequestUserRegisterDto;
import ssamchi.softeer.drivechat.dto.response.ResponseUserRegisterDto;
import ssamchi.softeer.drivechat.service.UserService;

import java.util.List;

@Tag(name = "User 컨트롤러", description = "User 관련 API 목록입니다.")
@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "마커 상세정보 조회 API", description = "해당 마커에 대한 상세 정보를 조회합니다.")
    @PostMapping(value = "/register")
    public ResponseEntity<ResponseDto<ResponseUserRegisterDto>> registerUser
            (
                    @RequestBody RequestUserRegisterDto requestUserRegisterDto
            )
    {
        ResponseUserRegisterDto responseUserRegisterDto = userService.registerUser(requestUserRegisterDto);
        return ResponseEntity.ok()
                .body(ResponseDto.of(HttpStatus.OK.value(), "유저 등록 성공", responseUserRegisterDto));
    }
}
