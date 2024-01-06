package ssamchi.softeer.drivechat.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TopicResponseDTO {
    private Long topicId;
    private String name;

    @Builder
    public TopicResponseDTO(Long topicId, String name) {
        this.topicId = topicId;
        this.name = name;
    }
}
