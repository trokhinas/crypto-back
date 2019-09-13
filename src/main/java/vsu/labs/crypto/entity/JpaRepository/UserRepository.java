package vsu.labs.crypto.entity.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vsu.labs.crypto.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByLogin(String login);
}
