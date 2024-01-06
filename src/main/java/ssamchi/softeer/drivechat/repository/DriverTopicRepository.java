package ssamchi.softeer.drivechat.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ssamchi.softeer.drivechat.domain.DriverTopic;

public interface DriverTopicRepository extends JpaRepository<DriverTopic, Long> {
    List<DriverTopic> findAllByDriver_DriverId(Long driverId);

    @Query("SELECT DISTINCT dt.driver.driverId "
        + "FROM DriverTopic dt INNER JOIN GuestTopic gt ON dt.topic.topicId = gt.topic.topicId "
        + "WHERE gt.guest.guestId = :guestId and dt.driver.found = false "
        + "ORDER BY dt.driver.createdAt DESC ")
    List<Long> findSameTopicDriverIdsByGuestId(@Param("guestId") Long guestId);

}
