package vsu.labs.crypto.entity.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import vsu.labs.crypto.config.IntegrationTest;
import vsu.labs.crypto.entity.JpaRepository.TestRepository;

public class TestEntityTest extends IntegrationTest {
    @Autowired
    private TestRepository testRepository;

    @Test
    public void name() {
         TestEntity testEntity = new TestEntity();
         testEntity.setTitle("ВГУ Агу");
         testRepository.save(testEntity);

    }
}