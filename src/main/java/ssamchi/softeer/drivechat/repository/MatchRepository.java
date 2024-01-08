package ssamchi.softeer.drivechat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssamchi.softeer.drivechat.domain.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {
}