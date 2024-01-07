package ssamchi.softeer.drivechat.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseMakeMatchingDto {
    private Long matchId;

    @Builder
    public ResponseMakeMatchingDto(Long matchId) {
        this.matchId = matchId;
    }
}
