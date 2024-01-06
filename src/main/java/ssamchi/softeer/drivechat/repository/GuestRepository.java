package ssamchi.softeer.drivechat.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ssamchi.softeer.drivechat.domain.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    Optional<Guest> findByUser_UserId(Long userId);

    Optional<Guest> findByGuestId(Long guestId);
}
