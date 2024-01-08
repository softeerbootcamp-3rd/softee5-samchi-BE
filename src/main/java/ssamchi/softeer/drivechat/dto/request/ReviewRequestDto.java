package ssamchi.softeer.drivechat.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewRequestDto {
    private Long userId;
    private Long matchId;
    private String content;

    @Builder
    public ReviewRequestDto(Long userId, Long matchId, String content) {
        this.userId = userId;
        this.matchId = matchId;
        this.content = content;
    }
}
