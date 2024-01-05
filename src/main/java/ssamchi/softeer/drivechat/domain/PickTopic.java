package ssamchi.softeer.drivechat.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PickTopic extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pickTopicId;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private Long guestDriverId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Builder
    public PickTopic(UserType userType, Long guestDriverId, Topic topic){
        this.userType = userType;
        this.guestDriverId = guestDriverId;
        this.topic = topic;
    }
}
