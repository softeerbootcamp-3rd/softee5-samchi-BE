package ssamchi.softeer.drivechat.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DriverMarkerDetailInfoResponseDto {
    private Long driverId;
    private String driverNickname;
    private Long driveChatCount;
    private String destination;
    private LocalDateTime startTime;
    private List<String> driverTopics;

    @Builder
    public DriverMarkerDetailInfoResponseDto(Long driverId, String driverNickname, Long driveChatCount, String destination, LocalDateTime startTime, List<String> driverTopics) {
        this.driverId = driverId;
        this.driverNickname = driverNickname;
        this.driveChatCount = driveChatCount;
        this.destination = destination;
        this.startTime = startTime;
        this.driverTopics = driverTopics;
    }
}
