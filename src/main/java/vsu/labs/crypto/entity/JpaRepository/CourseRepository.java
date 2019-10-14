package vsu.labs.crypto.entity.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.labs.crypto.entity.test.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
