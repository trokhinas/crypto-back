package vsu.labs.crypto.utils.math;

import java.math.BigInteger;

import static vsu.labs.crypto.utils.math.RandomUtils.getRandBigInteger;

public final class PrimesGenerator {

    private PrimesGenerator() {}

    public static BigInteger getPrime(int bits) {
        BigInteger probablyPrime = getRandBigInteger(bits);

        while (!MathUtils.isPrime(probablyPrime)) {
            probablyPrime = getRandBigInteger(bits);
        }
        return probablyPrime;
    }

    public static int getPrimeInt(int bits) {
        if (bits <= 1 || bits > 16)
            throw new IllegalArgumentException("Wrong bits parameter");
        return getPrime(bits).intValue();
    }
}
