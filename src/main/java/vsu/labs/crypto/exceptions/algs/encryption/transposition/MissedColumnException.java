package vsu.labs.crypto.exceptions.algs.encryption.transposition;

import vsu.labs.crypto.exceptions.LogicException;

public class MissedColumnException extends LogicException {
    private static final String MISSED_COLUMN = "В ключе отсутствует порядковый номер %d";

    public MissedColumnException(int columns) {
        super(String.format(MISSED_COLUMN, columns));
    }
}