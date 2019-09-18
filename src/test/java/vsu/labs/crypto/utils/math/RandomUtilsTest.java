package vsu.labs.crypto.utils.math;

import org.assertj.core.api.BigIntegerAssert;
import org.junit.Test;
import org.springframework.data.util.Pair;
import vsu.labs.crypto.config.UnitTest;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.from;
import static org.junit.Assert.*;

public class RandomUtilsTest extends UnitTest {

    private static final List<Pair<Integer, Integer>> BOUND_PAIRS_INT = Arrays.asList(
            Pair.of(1, 1), Pair.of(1, 10),
            Pair.of(100, 1), Pair.of(-5, -1)
    );
    private static final List<Pair<BigInteger, BigInteger>> BOUND_PAIRS_BIGINT = BOUND_PAIRS_INT.stream()
            .map(pair -> {
                BigInteger first = BigInteger.valueOf(pair.getFirst());
                BigInteger second = BigInteger.valueOf(pair.getSecond());
                return Pair.of(first, second);
            })
            .collect(Collectors.toList());

    @Test
    public void getRandomInt() {
        BOUND_PAIRS_INT.forEach(
                pair -> {
                    int left = pair.getFirst();
                    int right = pair.getSecond();
                    if (left < right) {
                        int value = RandomUtils.getRandomInt(left, right);
                        assertTrue(value >= left && value <= right);
                    }
                    else {
                        assertThatThrownBy(() -> RandomUtils.getRandomInt(left, right))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessage("Left bound must not be greater than right");
                    }
                }
        );
    }

    @Test
    public void getRandomBigInteger() {
        BOUND_PAIRS_BIGINT.forEach(
                pair -> {
                    BigInteger left = pair.getFirst();
                    BigInteger right = pair.getSecond();

                    if (left.signum() < 0 || right.signum() < 0) {
                        assertThatThrownBy(() -> RandomUtils.getRandomBigInteger(left, right))
                                .isInstanceOf(IllegalStateException.class)
                                .hasMessage("Implementation for negative BigInteger not ready yet");
                    }
                    else if (left.compareTo(right) >= 0) {
                        assertThatThrownBy(() -> RandomUtils.getRandomBigInteger(left, right))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessage("Left bound must not be greater than right");
                    }
                    else {
                        BigInteger value = RandomUtils.getRandomBigInteger(left, right);
                        assertTrue(value.compareTo(left) >= 0 && value.compareTo(right) <= 0);
                    }
                }
        );
    }
}
