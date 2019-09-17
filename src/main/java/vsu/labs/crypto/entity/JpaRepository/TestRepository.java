package vsu.labs.crypto.entity.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.labs.crypto.entity.Test;

public interface TestRepository extends JpaRepository<Test,Long> {
}
