package vsu.labs.crypto.utils.math;

import org.junit.Test;
import org.springframework.data.util.Pair;
import vsu.labs.crypto.config.UnitTest;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

public class MathUtilsTest extends UnitTest {

    private static final Integer TEN = 10;
    private static final List<Pair<BigInteger, BigInteger>> VALUE_EULER_VALUE_PAIRS = Arrays.asList(
            Pair.of(BigInteger.valueOf(0), BigInteger.valueOf(0)),
            Pair.of(BigInteger.valueOf(1), BigInteger.valueOf(1)),
            Pair.of(BigInteger.valueOf(2), BigInteger.valueOf(1)),
            Pair.of(BigInteger.valueOf(3), BigInteger.valueOf(2)),
            Pair.of(BigInteger.valueOf(4), BigInteger.valueOf(2)),
            Pair.of(BigInteger.valueOf(5), BigInteger.valueOf(4)),
            Pair.of(BigInteger.valueOf(6), BigInteger.valueOf(2)),
            Pair.of(BigInteger.valueOf(7), BigInteger.valueOf(6)),
            Pair.of(BigInteger.valueOf(8), BigInteger.valueOf(4)),
            Pair.of(BigInteger.valueOf(9), BigInteger.valueOf(6)),
            Pair.of(BigInteger.valueOf(-1), BigInteger.valueOf(0)),
            Pair.of(BigInteger.valueOf(-2), BigInteger.valueOf(1)),
            Pair.of(BigInteger.valueOf(99), BigInteger.valueOf(60)),
            Pair.of(BigInteger.valueOf(55), BigInteger.valueOf(40)),
            Pair.of(BigInteger.valueOf(37), BigInteger.valueOf(36))
    );
    private static final List<Pair<BigInteger, BigInteger>> ROOT_MODULE_PAIRS = Arrays.asList(
            Pair.of(BigInteger.valueOf(1), BigInteger.valueOf(2)),
            Pair.of(BigInteger.valueOf(2), BigInteger.valueOf(3)),
            Pair.of(BigInteger.valueOf(3), BigInteger.valueOf(4)),
            Pair.of(BigInteger.valueOf(2), BigInteger.valueOf(5)),
            Pair.of(BigInteger.valueOf(5), BigInteger.valueOf(6)),
            Pair.of(BigInteger.valueOf(3), BigInteger.valueOf(7)),
            Pair.of(BigInteger.valueOf(2), BigInteger.valueOf(9)),
            Pair.of(BigInteger.valueOf(3), BigInteger.valueOf(10))
    );

    @Test
    public void calculatePercent() {
        for (int i = 0; i <= TEN; i++) {
            Integer expectedPercent = i * 10;
            Integer calculatedPercent = MathUtils.calculatePercent(TEN, i);
            assertEquals(expectedPercent, calculatedPercent);
        }
    }

    @Test
    public void euler() {
        VALUE_EULER_VALUE_PAIRS.forEach(pair -> {
            BigInteger value = pair.getFirst();
            BigInteger expectedEulerValue = pair.getSecond();
            if (value.signum() < 0) {
                assertThatThrownBy(() -> MathUtils.euler(value))
                        .isInstanceOf(IllegalStateException.class)
                        .hasMessage("Euler function works only with natural numbers");
            }
            else {
                BigInteger eulerValue = MathUtils.euler(value);
                assertEquals(expectedEulerValue, eulerValue);
            }
        });
    }

    @Test
    public void isPrimitiveRootModuloN() {
        ROOT_MODULE_PAIRS.forEach(pair -> {
            BigInteger root = pair.getFirst();
            BigInteger modulo = pair.getSecond();
            assertTrue(MathUtils.isPrimitiveRootModuloN(root, modulo));
        });
        assertTrue(MathUtils.isPrimitiveRootModuloN(BigInteger.valueOf(3), BigInteger.valueOf(7)));
    }
}