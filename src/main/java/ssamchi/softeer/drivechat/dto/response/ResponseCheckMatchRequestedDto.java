package ssamchi.softeer.drivechat.dto.response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseCheckMatchRequestedDto {
    private Boolean isMatchRequestedByGuest;

    @Builder
    public ResponseCheckMatchRequestedDto(Boolean isMatchRequestedByGuest) {
        this.isMatchRequestedByGuest = isMatchRequestedByGuest;
    }
}
