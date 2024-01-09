package ssamchi.softeer.drivechat.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseCheckMatchConfirmedDto {
    private Boolean isMatchConfirmed;

    @Builder
    public ResponseCheckMatchConfirmedDto(Boolean isMatchConfirmed) {
        this.isMatchConfirmed = isMatchConfirmed;
    }
}
