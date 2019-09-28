package vsu.labs.crypto.algs.encryption.elgamal.utils;

import lombok.extern.slf4j.Slf4j;
import vsu.labs.crypto.exceptions.LogicException;

import java.math.BigInteger;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Slf4j
public final class GenerationUtils {
    private static final long DEFAULT_TIMEOUT_SECOND = 10;

    public static BigInteger whileWithTimeout(Predicate<BigInteger> condition, Supplier<BigInteger> supplier) {
        log.info("process method whileWithTimeout");
        long start = System.currentTimeMillis();
        BigInteger result = supplier.get();

        while (!condition.test(result)) {
            result = supplier.get();
            checkTimeout(start);
        }
        return result;
    }

    private static void checkTimeout(long start) {
        long currentTime = System.currentTimeMillis();
        long secondsAfterStart = (currentTime - start) / 1000;

        if (secondsAfterStart >= DEFAULT_TIMEOUT_SECOND) {
            throw new LogicException("Превышено время на генерацию");
        }
    }
}
