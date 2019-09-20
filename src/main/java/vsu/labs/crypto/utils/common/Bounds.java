package vsu.labs.crypto.utils.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Bounds {
    private BigInteger left;
    private BigInteger right;

    public static Bounds of(BigInteger left, BigInteger right) {
        if (left.compareTo(right) > 0) {
            throw new IllegalStateException("Right bound must be greater than right!");
        }
        return new Bounds(left, right);
    }
}
