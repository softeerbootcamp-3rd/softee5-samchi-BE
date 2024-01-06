package ssamchi.softeer.drivechat.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestDriverInfoDto {
    private List<Long> topicIds;
}
