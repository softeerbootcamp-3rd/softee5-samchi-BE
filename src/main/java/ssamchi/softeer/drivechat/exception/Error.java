package ssamchi.softeer.drivechat.exception;

import lombok.Getter;

@Getter
public enum Error {
    INTERNAL_SERVER_ERROR("서버 내부 에러입니다.", 500),
    // 사용자
    USER_NOT_FOUND("사용자를 찾을 수 없습니다.", 1000),
    MARKER_NOT_FOUND("운전자를 찾을 수 없습니다.", 1001);

    private final String message;
    private final int errorCode;

    Error(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
