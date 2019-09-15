package vsu.labs.crypto.entity.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.labs.crypto.entity.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity,Integer> {
}
