package vsu.labs.crypto.utils.math;

import java.math.BigInteger;
import java.util.Random;

import static vsu.labs.crypto.utils.math.MathConstants.*;
import static vsu.labs.crypto.utils.math.RandomUtils.getRandBigInteger;

public final class PrimesGenerator {
    private static final Random RANDOM = new Random();
    private static final int NUMBER_OF_TESTS = 64;

    private PrimesGenerator() {}

    public static BigInteger getPrime(int bits) {
        BigInteger probablyPrime = getRandBigInteger(bits);
        while (!isPrime(probablyPrime, bits)) {
            probablyPrime = getRandBigInteger(bits);
        }
        return probablyPrime;
    }

    public static int getPrimeInt(int bits) {
        if (bits <= 1 || bits > 16)
            throw new IllegalArgumentException("Wrong bits parameter");
        return getPrime(bits).intValue();
    }

    private static boolean isPrime(BigInteger value, int bits) {
        if (value.equals(TWO) || value.equals(THREE))
            return true;
        int s = 0;
        BigInteger r = value.subtract(ONE);
        BigInteger d = value.subtract(ONE);
        while (!r.mod(TWO).equals(ONE)) {
            r = r.divide(TWO);
            s++;
        }

        for (int i = 0; i < NUMBER_OF_TESTS; ++i) {
            boolean isPrime = false;
            BigInteger testValue = new BigInteger(bits - 1, RANDOM);
            while (testValue.compareTo(TWO) < 0)
                testValue = new BigInteger(bits - 1, RANDOM);
            BigInteger powerOfTestValue = testValue.modPow(r, value);
            if (!powerOfTestValue.equals(ONE)) {
                if (powerOfTestValue.equals(d))
                    isPrime = true;
                int j = 1;
                while (j < s) {
                    powerOfTestValue = powerOfTestValue.modPow(TWO, value);
                    j++;
                    if (powerOfTestValue.equals(d))
                        isPrime = true;
                }
            }
            else {
                isPrime = true;
            }
            if (!isPrime)
                return false;
        }
        return true;
    }
}
