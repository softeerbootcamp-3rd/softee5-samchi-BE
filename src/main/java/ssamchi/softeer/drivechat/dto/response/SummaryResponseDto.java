package ssamchi.softeer.drivechat.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SummaryResponseDto {
    private String contents;

    @Builder
    public SummaryResponseDto(String contents) {
        this.contents = contents;
    }
}
