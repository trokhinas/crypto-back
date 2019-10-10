package vsu.labs.crypto.exceptions.algs.encryption.transposition;

import vsu.labs.crypto.exceptions.LogicException;

public class TooLongKeyException extends LogicException {

    private static final String DEFAULT_MESSAGE = "Длина ключа не должна превышать 10 символов";

    public TooLongKeyException() {
        super(DEFAULT_MESSAGE);
    }
}
