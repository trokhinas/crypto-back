package vsu.labs.crypto.entity.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.labs.crypto.entity.security.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    public RoleEntity findId(Long id);
}
