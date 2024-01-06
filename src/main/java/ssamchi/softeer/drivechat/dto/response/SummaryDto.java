package ssamchi.softeer.drivechat.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SummaryDto {
    private String contents;

    @Builder
    public SummaryDto(String contents) {
        this.contents = contents;
    }
}
