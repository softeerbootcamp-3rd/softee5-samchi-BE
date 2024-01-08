package ssamchi.softeer.drivechat.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssamchi.softeer.drivechat.domain.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseUserRegisterDto {
    private Boolean success;
    private Long userId;

    @Builder
    public ResponseUserRegisterDto(Boolean success, Long userId) {
        this.success = success;
        this.userId = userId;
    }
}
