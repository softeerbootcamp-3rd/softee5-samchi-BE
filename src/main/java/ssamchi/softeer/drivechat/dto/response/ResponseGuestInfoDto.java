package ssamchi.softeer.drivechat.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseGuestInfoDto {
    private Boolean success;

    @Builder
    public ResponseGuestInfoDto(Boolean success) {
        this.success = success;
    }
}
