package ssamchi.softeer.drivechat.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssamchi.softeer.drivechat.dto.common.UserType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DriveChatRegistrationResponseDto {
    private Long driverId;
    private Long guestId;
    private UserType userType;

    @Builder
    public DriveChatRegistrationResponseDto(Long driverId, Long guestId, UserType userType) {
        this.driverId = driverId;
        this.guestId = guestId;
        this.userType = userType;
    }
}
