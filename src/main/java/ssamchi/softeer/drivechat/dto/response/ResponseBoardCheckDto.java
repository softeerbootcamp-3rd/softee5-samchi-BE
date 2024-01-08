package ssamchi.softeer.drivechat.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseBoardCheckDto {
    private Boolean isBoarding;

    @Builder
    public ResponseBoardCheckDto(Boolean isBoarding) {
        this.isBoarding = isBoarding;
    }
}
