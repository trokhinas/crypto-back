package vsu.labs.crypto.utils.math;

import org.junit.Test;
import vsu.labs.crypto.config.UnitTest;

import static org.junit.Assert.*;

public class MathUtilsTest extends UnitTest {

    private static final Integer TEN = 10;

    @Test
    public void calculatePercent() {
        for (int i = 0; i <= TEN; i++) {
            Integer expectedPercent = i * 10;
            Integer calculatedPercent = MathUtils.calculatePercent(TEN, i);
            assertEquals(expectedPercent, calculatedPercent);
        }
    }
}