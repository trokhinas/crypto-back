package vsu.labs.crypto.utils.math;

import java.math.BigInteger;

public final class PrimesGenerator {
    private static final int NUMBER_OF_TESTS = 64;

    private PrimesGenerator() {}

    public static BigInteger getPrime(int bits) {
        BigInteger probablyPrime = RandomUtils.getRandBigInteger(bits);
        while (!MathUtils.isPrime(probablyPrime, NUMBER_OF_TESTS)) {
            probablyPrime = RandomUtils.getRandBigInteger(bits);
        }
        return probablyPrime;
    }

    public static int getPrimeInt(int bits) {
        if (bits <= 1 || bits > 16)
            throw new IllegalArgumentException("Wrong bits parameter");
        return getPrime(bits).intValue();
    }

    public static long getPrimeLong(int bits) {
        return getPrime(bits).longValue();
    }
}
