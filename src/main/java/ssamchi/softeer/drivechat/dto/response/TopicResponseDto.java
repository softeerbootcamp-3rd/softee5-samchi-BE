package ssamchi.softeer.drivechat.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TopicResponseDto {
    private String content;

    @Builder
    public TopicResponseDto(String content) {
        this.content = content;
    }

}
