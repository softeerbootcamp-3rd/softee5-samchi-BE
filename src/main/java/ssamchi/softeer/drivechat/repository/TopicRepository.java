package ssamchi.softeer.drivechat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssamchi.softeer.drivechat.domain.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
