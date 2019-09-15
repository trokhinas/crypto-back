package vsu.labs.crypto.entity.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.labs.crypto.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity,Integer> {
}
