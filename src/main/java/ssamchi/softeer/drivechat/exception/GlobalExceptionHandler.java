package ssamchi.softeer.drivechat.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ssamchi.softeer.drivechat.dto.common.ResponseDto;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<ResponseDto> handlerCustomException(BusinessException e) {
        log.error("Status: {}, Message: {}", e.getResponseDto().getCode(), e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseDto.of(e.getResponseDto().getCode(), e.getResponseDto().getMessage(), null));
    }
}
