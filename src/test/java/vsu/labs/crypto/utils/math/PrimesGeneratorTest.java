package vsu.labs.crypto.utils.math;

import org.junit.Test;
import vsu.labs.crypto.config.UnitTest;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

public class PrimesGeneratorTest extends UnitTest {

    private static final Integer MAX_BITS = 16;
    private static final List<Integer> ERROR_COUNT_OF_BITS = Arrays.asList(
            -1, 0, 1, 17
    );

    @Test
    public void getPrime() {
        for (int i = 1; i < MAX_BITS; i++) {
            BigInteger prime = PrimesGenerator.getPrime(i);
            assertPrimeInBounds(i, prime);
        }
    }

    @Test
    public void getPrimeIntNegative() {
        ERROR_COUNT_OF_BITS.forEach(
                bits -> assertThatThrownBy(() -> PrimesGenerator.getPrimeInt(bits))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("Wrong bits parameter")
        );
    }

    @Test
    public void getPrimeInt() {
        for (int i = 2; i < MAX_BITS; i++) {
            int prime = PrimesGenerator.getPrimeInt(i);
            assertPrimeInBounds(i, BigInteger.valueOf(prime));
        }
    }

    private void assertPrimeInBounds(int bits, BigInteger value) {
        BigInteger bound = BigInteger.valueOf((long) Math.pow(2, bits));
        assertTrue(value.compareTo(BigInteger.ZERO) > 0 && value.compareTo(bound) <= 0);
    }
}
