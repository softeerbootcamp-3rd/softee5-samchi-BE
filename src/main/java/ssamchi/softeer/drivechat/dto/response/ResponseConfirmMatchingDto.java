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
    private Guest guest;
    private Driver driver;
    private String content;
    private Long driverCount;

    @Builder
    public ResponseConfirmMatchingDto(Long matchId, Guest guest, Driver driver, String content, Long driverCount) {
        this.matchId = matchId;
        this.guest = guest;
        this.driver = driver;
        this.content = content;
        this.driverCount = driverCount;
    }
}
