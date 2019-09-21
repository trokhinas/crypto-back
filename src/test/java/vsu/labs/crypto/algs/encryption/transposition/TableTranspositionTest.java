package vsu.labs.crypto.algs.encryption.transposition;

import org.junit.Test;

import static org.junit.Assert.*;
import static vsu.labs.crypto.utils.math.MathConstants.THREE;
import static vsu.labs.crypto.utils.math.MathConstants.TWO;

public class TableTranspositionTest {

    private static final String TEST_VALUE = "Мама мыла";

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