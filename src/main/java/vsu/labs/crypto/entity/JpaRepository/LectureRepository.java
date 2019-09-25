package vsu.labs.crypto.entity.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.labs.crypto.entity.test.LectureEntity;

public interface LectureRepository extends JpaRepository<LectureEntity, Long> {
}
