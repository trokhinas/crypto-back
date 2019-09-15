package vsu.labs.crypto.entity.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.labs.crypto.entity.test.TestEntity;

public interface TestRepository extends JpaRepository<TestEntity, Long> {

}
