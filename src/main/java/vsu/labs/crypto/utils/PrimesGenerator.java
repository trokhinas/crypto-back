package vsu.labs.crypto.utils;

import java.math.BigInteger;
import java.util.Random;

public class PrimesGenerator {
    private static Random rand = new Random();
    private static final BigInteger int3 = new BigInteger("3");
    private static final BigInteger int2 = new BigInteger("2");
    private static final BigInteger int1 = new BigInteger("1");
    private static final int k = 64;


    private static boolean millerRabinTest(BigInteger value, int bits) {
        if (value.equals(int2) || value.equals(int3))
            return true;
        int s = 0;
        BigInteger r = value.subtract(int1);
        BigInteger d = value.subtract(int1);
        while (!r.mod(int2).equals(int1)) {
            r = r.divide(int2);
            s++;
        }

        for (int i = 0; i < k; ++i) {
            boolean isPrime = false;
            BigInteger a = new BigInteger(bits - 1, rand);
            while (a.compareTo(int2) < 0)
                a = new BigInteger(bits - 1, rand);
            BigInteger x = a.modPow(r, value);
            if (!x.equals(int1)) {
                if (x.equals(d))
                    isPrime = true;
                int j = 1;
                while (j < s) {
                    x = x.modPow(int2, value);
                    j++;
                    if (x.equals(d))
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
        BigInteger bigBit = new BigInteger("1").shiftLeft(bits - 1);
        BigInteger x = new BigInteger(bits - 1, rand);
        if (!x.mod(int2).equals(int1)) {
            x = x.add(int1);
        }
        return x.add(bigBit);
    }


    public static BigInteger getPrime(int bits) {
        BigInteger value = getRandValue(bits);
        while (!millerRabinTest(value, bits)) {
            value = getRandValue(bits);
        }
        return value;
    }


    public static int getPrimeInt(int bits) {
        if (bits <= 1 || bits > 16)
            throw new IllegalArgumentException("Wrong bits parameter");
        return getPrime(bits).intValue();
    }
}
