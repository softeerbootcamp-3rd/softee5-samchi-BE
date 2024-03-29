package ssamchi.softeer.drivechat.exception;

import lombok.Getter;

@Getter
public enum Error {
    BAD_REQUEST("잘못된 요청입니다.", 400),
    INTERNAL_SERVER_ERROR("서버 내부 에러입니다.", 500),
    // 사용자
    USER_NOT_FOUND("사용자를 찾을 수 없습니다.", 1000),
    DRIVER_NOT_FOUND("운전자를 찾을 수 없습니다.", 1001),
    GUEST_NOT_FOUND("동승자 정보를 찾을 수 없습니다.", 1002),
    TOPIC_NOT_FOUND("토픽 정보를 찾을 수 없습니다.", 1003),
    MATCH_NOT_FOUND("매칭 데이터가 없습니다.", 1004);

    private final String message;
    private final int errorCode;

    Error(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
