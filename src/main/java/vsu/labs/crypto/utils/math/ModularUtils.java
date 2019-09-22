package vsu.labs.crypto.utils.math;

import java.math.BigInteger;

public final class ModularUtils {

    private ModularUtils() { }

    public static BigInteger modularMul(BigInteger a, BigInteger b, BigInteger module) {
        BigInteger left = a.mod(module);
        BigInteger right = b.mod(module);
        return left.multiply(right).mod(module);
    }

    public static BigInteger modularAdd(BigInteger a, BigInteger b, BigInteger module) {
        BigInteger left = a.mod(module);
        BigInteger right = b.mod(module);
        return left.add(right).mod(module);
    }

    public static BigInteger modularSub(BigInteger a, BigInteger b, BigInteger module) {
        BigInteger left = a.mod(module);
        BigInteger right = b.mod(module);
        return left.subtract(right).mod(module);
    }
}
