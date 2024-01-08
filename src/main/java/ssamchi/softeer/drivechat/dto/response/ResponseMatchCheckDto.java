package ssamchi.softeer.drivechat.dto.response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseMatchCheckDto {
    private Boolean isFound;

    @Builder
    public ResponseMatchCheckDto(Boolean isFound) {
        this.isFound = isFound;
    }
}
