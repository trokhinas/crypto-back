package vsu.labs.crypto.entity.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.labs.crypto.entity.Question;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
}
