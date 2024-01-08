package ssamchi.softeer.drivechat.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewRequestDto {
    private String content;

    @Builder
    public ReviewRequestDto(String content) {
        this.content = content;
    }
}
