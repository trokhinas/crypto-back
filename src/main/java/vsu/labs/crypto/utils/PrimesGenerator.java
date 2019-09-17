package vsu.labs.crypto.utils;

import java.math.BigInteger;
import java.util.Random;

public final class PrimesGenerator {
    private static final Random rand = new Random();
    private static final BigInteger INT_3 = new BigInteger("3");
    private static final BigInteger INT_2 = new BigInteger("2");
    private static final BigInteger INT_1 = new BigInteger("1");
    private static final int NUMBER_OF_TESTS = 64;


    private PrimesGenerator() {}


    public static BigInteger getPrime(int bits) {
        BigInteger probablyPrime = getRandValue(bits);
        while (!isPrime(probablyPrime, bits)) {
            probablyPrime = getRandValue(bits);
        }
        return probablyPrime;
    }


    public static int getPrimeInt(int bits) {
        if (bits <= 1 || bits > 16)
            throw new IllegalArgumentException("Wrong bits parameter");
        return getPrime(bits).intValue();
    }


    private static boolean isPrime(BigInteger value, int bits) {
        if (value.equals(INT_2) || value.equals(INT_3))
            return true;
        int s = 0;
        BigInteger r = value.subtract(INT_1);
        BigInteger d = value.subtract(INT_1);
        while (!r.mod(INT_2).equals(INT_1)) {
            r = r.divide(INT_2);
            s++;
        }

        for (int i = 0; i < NUMBER_OF_TESTS; ++i) {
            boolean isPrime = false;
            BigInteger testValue = new BigInteger(bits - 1, rand);
            while (testValue.compareTo(INT_2) < 0)
                testValue = new BigInteger(bits - 1, rand);
            BigInteger powerOfTestValue = testValue.modPow(r, value);
            if (!powerOfTestValue.equals(INT_1)) {
                if (powerOfTestValue.equals(d))
                    isPrime = true;
                int j = 1;
                while (j < s) {
                    powerOfTestValue = powerOfTestValue.modPow(INT_2, value);
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


    private static BigInteger getRandValue(int bits) {
        BigInteger highBit = new BigInteger("1").shiftLeft(bits - 1);
        BigInteger randomValue = new BigInteger(bits - 1, rand);
        if (!randomValue.mod(INT_2).equals(INT_1)) {
            randomValue = randomValue.add(INT_1);
        }
        return randomValue.add(highBit);
    }
}
