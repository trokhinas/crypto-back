package vsu.labs.crypto.utils.math;

import java.math.BigInteger;
import java.util.Random;

import static vsu.labs.crypto.utils.math.MathConstants.*;

public final class RandomUtils {
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private RandomUtils() { }

    /**
     *
     * @param from - min граница
     * @param to - max граница
     * @return псевдослучайное число в диапазоне [from; to]
     */
    public static Integer getRandomInt(Integer from, Integer to) {
        if (from >= to) {
            throw new IllegalArgumentException("Left bound must not be greater than right");
        }
        return from + RANDOM.nextInt(to - from + 1);
    }

    public static BigInteger getRandBigInteger(int bits) {
        BigInteger highBit = new BigInteger("1").shiftLeft(bits - 1);
        BigInteger randomValue = new BigInteger(bits - 1, RANDOM);
        if (!randomValue.mod(TWO).equals(ONE)) {
            randomValue = randomValue.add(ONE);
        }
        return randomValue.add(highBit);
    }
}
