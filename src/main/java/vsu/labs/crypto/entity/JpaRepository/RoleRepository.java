package vsu.labs.crypto.entity.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.labs.crypto.entity.security.RoleEntity;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    public Optional<RoleEntity> findById(Long id);
}
