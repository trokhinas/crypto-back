package vsu.labs.crypto.entity.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.labs.crypto.entity.test.TaskTypeEntity;

public interface TaskTypeRepository extends JpaRepository<TaskTypeEntity, Long> {

}
