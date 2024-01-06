package ssamchi.softeer.drivechat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssamchi.softeer.drivechat.dto.common.ResponseDto;
import ssamchi.softeer.drivechat.dto.request.RequestDriverInfoDto;
import ssamchi.softeer.drivechat.dto.request.RequestGuestInfoDto;
import ssamchi.softeer.drivechat.dto.request.RequestUserRegisterDto;
import ssamchi.softeer.drivechat.dto.response.ResponseDriverInfoDto;
import ssamchi.softeer.drivechat.dto.response.ResponseGuestInfoDto;
import ssamchi.softeer.drivechat.dto.response.ResponseUserRegisterDto;
import ssamchi.softeer.drivechat.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

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

    @PostMapping(value = "/driver")
    public ResponseEntity<ResponseDto<ResponseDriverInfoDto>> registerDriverInfo
            (
                    @RequestBody RequestDriverInfoDto requestDriverInfoDto
            )
    {
        ResponseDriverInfoDto responseDriverInfoDto = userService.registerDriverInfo(requestDriverInfoDto);
        return ResponseEntity.ok()
                .body(ResponseDto.of(HttpStatus.OK.value(), "Driver 사전정보 등록 성공", responseDriverInfoDto));
    }

    @PostMapping(value = "/guest")
    public ResponseEntity<ResponseDto<ResponseGuestInfoDto>> registerGuestInfo
            (
                    @RequestBody RequestGuestInfoDto requestGuestInfoDto
            )
    {
        ResponseGuestInfoDto responseGuestInfoDto = userService.registerGuestInfo(requestGuestInfoDto);
        return ResponseEntity.ok()
                .body(ResponseDto.of(HttpStatus.OK.value(), "Guest 사전정보 등록 성공", responseGuestInfoDto));
    }
}
