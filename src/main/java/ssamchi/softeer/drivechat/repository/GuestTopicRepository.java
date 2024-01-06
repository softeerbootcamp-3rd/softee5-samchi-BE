package ssamchi.softeer.drivechat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssamchi.softeer.drivechat.domain.GuestTopic;

public interface GuestTopicRepository extends JpaRepository<GuestTopic, Long> {
}
