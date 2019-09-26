package vsu.labs.crypto.algs.encryption.elgamal;

import org.junit.Test;
import vsu.labs.crypto.utils.math.RandomUtils;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class ElGamalTest {

    private static final Integer FROM_BITS = 8;
    private static final Integer TO_BITS = 16;
    private static final Integer NUMBER_OF_TESTS = 10;

    @Test
    public void encrypt() {
    }

    @Test
    public void decrypt() {
    }

    // TODO тест может зависать из-за отвратительного подбора взаимно простых чисел: простая генерация, пока не повезет
    @Test
    public void testAlg() {
        for (int i = 0; i < NUMBER_OF_TESTS; i++) {
            int randomBits = RandomUtils.getRandomInt(FROM_BITS, TO_BITS);
            BigInteger value = RandomUtils.getRandBigInteger(randomBits);
            var keys = ElGamal.generateKeys();
            var encryptedValue = ElGamal.encrypt(value, keys.getFirst());
            var decryptedValue = ElGamal.decrypt(encryptedValue, keys.getFirst(), keys.getSecond());

            if (value.compareTo(keys.getFirst().getP()) > 0)
                System.out.println("may be error");
            assertEquals(value, decryptedValue);
        }
    }
}