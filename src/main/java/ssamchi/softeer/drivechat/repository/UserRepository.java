package ssamchi.softeer.drivechat.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ssamchi.softeer.drivechat.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String nickname);

}
