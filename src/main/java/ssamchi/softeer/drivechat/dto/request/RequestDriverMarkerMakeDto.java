package ssamchi.softeer.drivechat.dto.request;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestDriverMarkerMakeDto {
    private String destinationAddress;
    private LocalDateTime estimateStartTime;
}
