package vsu.labs.crypto.utils.math;

import java.math.BigInteger;

import static java.math.BigInteger.ZERO;
import static vsu.labs.crypto.utils.math.MathConstants.ONE;
import static vsu.labs.crypto.utils.math.MathConstants.THREE;
import static vsu.labs.crypto.utils.math.MathConstants.TWO;

public final class MathUtils {

    private MathUtils() { }

    public static int calculatePercent(Integer source, Integer part) {
        float partFloat = (float)part / (float)source;
        return (int) (partFloat * 100);
    }

    public static BigInteger euler(BigInteger n) {
        if (n.signum() < 0) {
            throw new IllegalStateException("Euler function works only with natural numbers");
        }
        BigInteger result = n;
        for (BigInteger i = TWO; i.multiply(i).compareTo(n) <= 0 ; i = i.add(ONE)) {
            if (n.mod(i).compareTo(ZERO) == 0) {
                while (n.mod(i).compareTo(ZERO) == 0) {
                    n = n.divide(i);
                }
                result = result.subtract(result.divide(i));
            }
        }
        if (n.compareTo(ONE) > 0) {
            result = result.subtract(result.divide(n));
        }
        return result;
    }

    public static boolean isPrime(BigInteger value, Integer numberOfTests) {
        if (value.equals(TWO) || value.equals(THREE))
            return true;

        int s = 0;
        BigInteger r = value.subtract(ONE);
        BigInteger d = value.subtract(ONE);
        while (!r.mod(TWO).equals(ONE)) {
            r = r.divide(TWO);
            s++;
        }

        for (int i = 0; i < numberOfTests; ++i) {
            boolean isPrime = false;
            BigInteger testValue = RandomUtils.getRandomBigInteger(TWO, value.subtract(ONE));
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

    public static boolean isPrime(BigInteger value) {
        return isPrime(value, MathConstants.COUNT_PRIME_TESTS);
    }
}