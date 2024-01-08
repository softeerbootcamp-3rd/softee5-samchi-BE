package ssamchi.softeer.drivechat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssamchi.softeer.drivechat.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
