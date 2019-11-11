package vsu.labs.crypto.exceptions.algs.encryption.transposition;

import vsu.labs.crypto.exceptions.LogicException;

public class StorageFileNotFoundException extends LogicException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }
}
