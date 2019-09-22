package vsu.labs.crypto.algs.encryption.transposition;

import org.junit.Test;
import vsu.labs.crypto.config.UnitTest;

import static org.junit.Assert.*;
import static vsu.labs.crypto.utils.math.MathConstants.THREE;

public class TableTranspositionTest extends UnitTest {

    private static final String TEST_VALUE = "Мама мыла раму";

    @Test
    public void encrypt() {

    }

    @Test
    public void decrypt() {

    }

    @Test
    public void testAlg() {
        var encryptedPair = TableTransposition.encrypt(TEST_VALUE, THREE);
        String messageWithSpacing = encryptedPair.getSecond();

        assertEquals(TEST_VALUE, TableTransposition.decrypt(messageWithSpacing, THREE));
    }
}