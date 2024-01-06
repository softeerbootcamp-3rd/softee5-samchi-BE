package ssamchi.softeer.drivechat.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Driver extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    private boolean found;

    private String destinationAddress;

    private LocalDateTime estimatedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private Match match;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DriverTopic> driverTopics;

    @Builder
    public Driver(boolean isFound, String destinationAddress, LocalDateTime estimatedTime, User user){
        this.found = isFound;
        this.destinationAddress = destinationAddress;
        this.estimatedTime = estimatedTime;
        this.user = user;
    }

    public void found(){
        this.found = true;
    }

}
