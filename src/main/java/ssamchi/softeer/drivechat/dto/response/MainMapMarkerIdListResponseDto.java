package ssamchi.softeer.drivechat.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainMapMarkerIdListResponseDto {
    private List<Long> markerIdList;

    @Builder
    public MainMapMarkerIdListResponseDto(List<Long> markerIdList) {
        this.markerIdList = markerIdList;
    }
}
