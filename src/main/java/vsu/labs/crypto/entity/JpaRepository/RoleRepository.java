package vsu.labs.crypto.entity.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.labs.crypto.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findId(Long id);
}
