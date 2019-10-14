package vsu.labs.crypto.utils.math;

import vsu.labs.crypto.utils.common.Bounds;

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

    public static BigInteger getRandomBigInteger(BigInteger from, BigInteger to) {
        if (from.signum() == -1 || to.signum() == -1) {
            // TODO реализовать алгоритм генерации для отрицательынх значений BigInteger, если это необходимо
            throw new IllegalStateException("Implementation for negative BigInteger not ready yet");
        }
        if (from.compareTo(to) >= 0) {
            throw new IllegalArgumentException("Left bound must not be greater than right");
        }

        int bits = getRandomBits(from, to);
        BigInteger randomNumber = new BigInteger(bits, RANDOM);
        while (randomNumber.compareTo(to) > 0 || randomNumber.compareTo(from) < 0) {
            randomNumber = new BigInteger(bits, RANDOM);
        }
        return randomNumber;
    }

    public static BigInteger getRandomBigInteger(Bounds bounds) {
        return getRandomBigInteger(bounds.getLeft(), bounds.getRight());
    }

    public static BigInteger getRandBigInteger(int bits) {
        BigInteger highBit = new BigInteger("1").shiftLeft(bits - 1);
        BigInteger randomValue = new BigInteger(bits - 1, RANDOM);
        if (!randomValue.mod(TWO).equals(ONE)) {
            randomValue = randomValue.add(ONE);
        }
        return randomValue.add(highBit);
    }

    private static int getRandomBits(BigInteger left, BigInteger right) {
        int leftBits = left.bitLength();
        int rightBits = right.bitLength();

        if (leftBits == rightBits) {
            return leftBits;
        }
        return getRandomInt(leftBits, rightBits);
    }
}
