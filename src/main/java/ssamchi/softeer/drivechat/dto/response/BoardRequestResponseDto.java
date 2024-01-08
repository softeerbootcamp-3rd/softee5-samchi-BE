package ssamchi.softeer.drivechat.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestResponseDto {
    private Boolean success;
    private Long matchId;

    @Builder
    public BoardRequestResponseDto(Boolean success, Long matchId) {
        this.success = success;
        this.matchId = matchId;
    }
}
