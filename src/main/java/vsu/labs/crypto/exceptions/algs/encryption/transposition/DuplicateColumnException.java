package vsu.labs.crypto.exceptions.algs.encryption.transposition;

import vsu.labs.crypto.exceptions.LogicException;

public class DuplicateColumnException extends LogicException {
    private static final String DUPLICATE_COLUMN = "Дублируется порядковый номер %d";

    public DuplicateColumnException(int i) {
        super(String.format(DUPLICATE_COLUMN, i));
    }
}