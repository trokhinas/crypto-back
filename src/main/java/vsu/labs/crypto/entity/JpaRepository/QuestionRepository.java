package vsu.labs.crypto.entity.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.labs.crypto.entity.test.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

}
