package pl.learning.photoappusers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.learning.photoappusers.entity.UserEntity;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

}
