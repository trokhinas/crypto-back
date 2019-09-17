package vsu.labs.crypto.utils.math;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vsu.labs.crypto.exceptions.LogicException;

import java.math.BigInteger;

import static vsu.labs.crypto.utils.math.MathConstants.*;
import static vsu.labs.crypto.utils.math.MathConstants.TWO;

public final class MathUtils {
    private static final Logger log = LoggerFactory.getLogger(MathUtils.class);

    private static final String NO_SUCH_PRIMITIVE_ROOT = "Не существует первообразного корня по модулю %d";

    private MathUtils() { }

    public static boolean isCoprimeIntegers(Long a, Long b) {
        return getGCD(a, b) != 1;
    }

    public static boolean isCoprimeIntegers(Integer a, Integer b) {
        return getGCD((long) a, (long) b) != 1;
    }

    public static Long eulerPrimeFunction(Long prime) {
        return prime - 1;
    }

    /*
    * функция не тестировалась
    * */
    public static Long eulerFunction(Long n) {
        Long result = n;
        for (int i = 2; i * i <= n; ++i)
            if (n % i == 0) {
                while (n % i == 0)
                    n /= i;
                result -= result / i;
            }
        if (n > 1)
            result -= result / n;
        return result;
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

    public static boolean isPrimeDefault(BigInteger value) {
        return isPrime(value, DEFAULT_NUMBER_PTIME_TESTS);
    }

    // TODO нужны тесты, функция не готова
    public static Long getPrimitiveRootModuloN(Long n) {
        if (n == null)
            throw new IllegalStateException("n must not be null");
        BigInteger value = BigInteger.valueOf(n);
        Long power = isPrimeDefault(value) ? eulerPrimeFunction(n) : eulerFunction(n);
        for (int i = 1; i < n; i++) {
            Long result = (long) Math.pow(i, power);
            if (result % n == 1)
                return (long) i;
        }
        // TODO отдельные исключения
        throw new LogicException(String.format(NO_SUCH_PRIMITIVE_ROOT, n));
    }
}
