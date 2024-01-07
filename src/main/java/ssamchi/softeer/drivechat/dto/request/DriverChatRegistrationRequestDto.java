package ssamchi.softeer.drivechat.dto.request;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssamchi.softeer.drivechat.dto.common.UserType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DriverChatRegistrationRequestDto {
    private UserType userType;
    private List<Long> topicIds;
    private String destinationAddress;
    private LocalDateTime estimateStartTime;
}
