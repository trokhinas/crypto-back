package vsu.labs.crypto.algs.encryption.transposition;

import org.junit.Test;
import vsu.labs.crypto.config.UnitTest;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class TableTranspositionTest extends UnitTest {

    private static final String TEST_VALUE = "Мама мыла раму";
    private static final BigInteger KEY = BigInteger.valueOf(312);

    @Test
    public void encrypt() {

    }

    @Test
    public void decrypt() {

    }

    @Test
    public void testAlg() {
        var encryptedMessage = TableTransposition.encrypt(TEST_VALUE, KEY);

        assertEquals(TEST_VALUE, TableTransposition.decrypt(encryptedMessage, KEY));
    }
}