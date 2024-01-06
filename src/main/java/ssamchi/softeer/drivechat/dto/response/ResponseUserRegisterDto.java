package ssamchi.softeer.drivechat.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseUserRegisterDto {
    private Boolean success;

    @Builder
    public ResponseUserRegisterDto(Boolean success) {
        this.success = success;
    }
}
