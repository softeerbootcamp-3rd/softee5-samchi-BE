package ssamchi.softeer.drivechat.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ssamchi.softeer.drivechat.domain.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Long countByUser_UserIdAndFoundIsTrue(Long userId);
    List<Driver> findDriversByFoundIsFalse();
}
