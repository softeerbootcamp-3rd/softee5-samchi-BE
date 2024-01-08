package ssamchi.softeer.drivechat.dto.request;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestMatchCheckDto {
    private Long userId;
}
