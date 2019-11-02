package vsu.labs.crypto.entity.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vsu.labs.crypto.entity.security.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByLogin(String login);

    List<UserEntity> findByRoleId(Long roleId);

    List<UserEntity> findByRoleIdNotOrderByRoleId(Long roleId);
}
