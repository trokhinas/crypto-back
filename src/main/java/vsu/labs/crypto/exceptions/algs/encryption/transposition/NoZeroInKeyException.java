package vsu.labs.crypto.exceptions.algs.encryption.transposition;

import vsu.labs.crypto.exceptions.LogicException;

public class NoZeroInKeyException extends LogicException {

    private static final String DEFAULT_MESSAGE = "Ключ не должен содержать 0";

    public NoZeroInKeyException() {
        super(DEFAULT_MESSAGE);
    }
}
