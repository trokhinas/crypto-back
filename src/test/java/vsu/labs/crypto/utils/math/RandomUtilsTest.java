package vsu.labs.crypto.utils.math;

import org.junit.Test;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

public class RandomUtilsTest {

    private static final List<Pair<Integer, Integer>> BOUND_PAIRS = Arrays.asList(
            Pair.of(1, 1), Pair.of(1, 10),
            Pair.of(100, 1), Pair.of(-5, -1)
    );

    @Test
    public void getRandomInt() {
        BOUND_PAIRS.forEach(
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
}
