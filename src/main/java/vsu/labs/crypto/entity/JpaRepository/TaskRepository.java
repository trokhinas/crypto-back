package vsu.labs.crypto.entity.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.labs.crypto.entity.test.TaskEntity;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
