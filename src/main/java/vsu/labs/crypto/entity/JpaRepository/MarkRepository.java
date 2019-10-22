package vsu.labs.crypto.entity.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import vsu.labs.crypto.entity.test.MarkEntity;

import java.util.List;

public interface MarkRepository extends JpaRepository<MarkEntity,Long> {

    List<MarkEntity> findByUserId(Long id);
    MarkEntity findByUserIdAndTestId(Long userID,Long testID);
}
