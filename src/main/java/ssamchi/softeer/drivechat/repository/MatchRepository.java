package ssamchi.softeer.drivechat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssamchi.softeer.drivechat.domain.Match;

import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findByDriver_DriverIdAndIsMatchedFalse(Long driverId);
}