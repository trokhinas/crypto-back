package vsu.labs.crypto.utils.math;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vsu.labs.crypto.exceptions.LogicException;

import java.math.BigInteger;

import static vsu.labs.crypto.utils.math.MathConstants.*;
import static vsu.labs.crypto.utils.math.MathConstants.TWO;

public final class MathUtils {
    private static final Logger log = LoggerFactory.getLogger(MathUtils.class);

    private MathUtils() { }

    public boolean isCoprimeIntegers(Long a, Long b) {
        return getGCD(a, b) != 1;
    }

    public boolean isCoprimeIntegers(Integer a, Integer b) {
        return getGCD((long) a, (long) b) != 1;
    }

    public static Long eilerFunction(Long a) {
        // TODO решить нужна ли нам вся функция эйлера или только ее реализация для простых чисел
        return null;
    }

    public static Long eilerPrimeFunction(Long prime) {
        return prime - 1;
    }

    public static Long getGCD(Long a, Long b) {
        log.info("process method getGCD for a = {}, b = {}", a, b);
        // TODO завести отдельные исключения
        if (a == 0 || b == 0)
            throw new LogicException("a and b must not be 0");

        while (a != 0 && b != 0) {
            if (a > b) {
                a %= b;
            }
            else {
                b %= a;
            }
        }
        return a + b;
    }

    public static boolean isPrime(BigInteger value, int numberOfTests) {
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
            BigInteger testValue = RandomUtils.getBoundedBigInteger(TWO, value.subtract(ONE));
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
