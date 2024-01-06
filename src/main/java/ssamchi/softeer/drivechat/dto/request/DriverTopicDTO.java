package ssamchi.softeer.drivechat.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DriverTopicDTO {
    //  >> 확인 userID로만은 중복 결과 반환됨 ;;ㅁ;;
    private Long driverId;
    private List<Long> topicIds;

    @Builder
    public DriverTopicDTO(Long driverId, List<Long> topicIds) {
        this.driverId = driverId;
        this.topicIds = topicIds;
    }
}