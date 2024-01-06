package ssamchi.softeer.drivechat.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseDriverInfoDto {
    private Boolean success;

    @Builder
    public ResponseDriverInfoDto(Boolean success) {
        this.success = success;
    }
}
