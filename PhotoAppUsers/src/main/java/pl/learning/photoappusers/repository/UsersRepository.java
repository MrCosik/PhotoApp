package pl.learning.photoappusers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.learning.photoappusers.entity.User;

public interface UsersRepository extends JpaRepository<User, Long> {
}
