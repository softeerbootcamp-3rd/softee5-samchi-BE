package ssamchi.softeer.drivechat.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ssamchi.softeer.drivechat.domain.DriverTopic;

public interface DriverTopicRepository extends JpaRepository<DriverTopic, Long> {
    List<DriverTopic> findAllByDriverTopicId(Long driverId);

}
