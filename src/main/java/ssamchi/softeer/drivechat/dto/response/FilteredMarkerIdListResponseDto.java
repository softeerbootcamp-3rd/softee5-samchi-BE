package ssamchi.softeer.drivechat.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FilteredMarkerIdListResponseDto {
    private Long markerId;
    private Boolean isRelated;

    @Builder
    public FilteredMarkerIdListResponseDto(Long markerId, Boolean isRelated) {
        this.markerId = markerId;
        this.isRelated = isRelated;
    }
}
