package vsu.labs.crypto.algs.encryption.elgamal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import vsu.labs.crypto.utils.common.Bounds;
import vsu.labs.crypto.utils.math.RandomUtils;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class ElGamalTest {

    private static final BigInteger VALUE = RandomUtils.getRandomBigInteger(Bounds.of(BigInteger.ONE, BigInteger.valueOf(10)));

    @Test
    public void encrypt() {
    }

    @Test
    public void decrypt() {
    }

    @Test
    public void testAlg() {
        var keys = ElGamal.generateKeys();
        var encryptedValue = ElGamal.encrypt(VALUE, keys.getFirst());
        var decryptedValue = ElGamal.decrypt(encryptedValue, keys.getFirst(), keys.getSecond());

        assertEquals(VALUE, decryptedValue);
    }
}