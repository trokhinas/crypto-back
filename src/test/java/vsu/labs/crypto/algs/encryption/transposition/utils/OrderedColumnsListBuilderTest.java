package vsu.labs.crypto.algs.encryption.transposition.utils;

import org.junit.Test;
import vsu.labs.crypto.exceptions.algs.encryption.transposition.DuplicateColumnException;
import vsu.labs.crypto.exceptions.algs.encryption.transposition.MissedColumnException;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderedColumnsListBuilderTest {
    private static final List<BigInteger> DUPLICATE_ERROR_VALUES = Arrays.asList(
            BigInteger.valueOf(122), BigInteger.valueOf(1233),
            BigInteger.valueOf(1332), BigInteger.valueOf(12341)
    );
    private static final List<BigInteger> MISSED_COLUMN_ERROR_VALUES = Arrays.asList(
            BigInteger.valueOf(13), BigInteger.valueOf(1235),
            BigInteger.valueOf(1334), BigInteger.valueOf(234)
    );

    @Test
    public void checkDuplicateError() {
        DUPLICATE_ERROR_VALUES.forEach(
                value -> assertThatThrownBy(() -> OrderedColumnsListBuilder.buildList(value))
                        .isInstanceOf(DuplicateColumnException.class)
        );
    }

    @Test
    public void checkMissedColumnError() {
        MISSED_COLUMN_ERROR_VALUES.forEach(
                value -> assertThatThrownBy(() -> OrderedColumnsListBuilder.buildList(value))
                        .isInstanceOf(MissedColumnException.class)
        );
    }
}