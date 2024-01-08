package ssamchi.softeer.drivechat.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssamchi.softeer.drivechat.domain.Driver;
import ssamchi.softeer.drivechat.domain.Guest;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseConfirmMatchingDto {
    private Long matchId;
    private Long guestId;
    private Long driverId;
    private String content;
    private Long driverCount;

    @Builder
    public ResponseConfirmMatchingDto(Long matchId, Long guestId, Long driverId, String content, Long driverCount) {
        this.matchId = matchId;
        this.guestId = guestId;
        this.driverId = driverId;
        this.content = content;
        this.driverCount = driverCount;
    }
}
