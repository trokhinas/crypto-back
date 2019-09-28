package vsu.labs.crypto.entity.JpaRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import vsu.labs.crypto.config.IntegrationTest;
import vsu.labs.crypto.entity.test.MarkEntity;

import static org.junit.Assert.*;

public class MarkRepositoryTest extends IntegrationTest {

    @Autowired
    private MarkRepository markRepository;

    @Test
    public void testWork() {
    }
}