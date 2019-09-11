package vsu.labs.crypto.algs.encryption.rot13;

import org.junit.Test;
import vsu.labs.crypto.config.UnitTest;
import vsu.labs.crypto.dto.crypto.PartitionAlgData;

import static org.junit.Assert.*;

public class Rot13Test extends UnitTest {

    private static final String FIRST_STRING = "Hello world";
    private static final String ENCRYPTED_FIRST_STRING = "Uryyb jbeyq";

    @Test
    public void encrypt() {
        String result = Rot13.encrypt(ENCRYPTED_FIRST_STRING);
        assertEquals(FIRST_STRING, result);
    }

    @Test
    public void decrypt() {
        String result = Rot13.decrypt(Rot13.encrypt(FIRST_STRING));
        assertEquals(FIRST_STRING, result);
    }

    @Test
    public void name() {
        PartitionAlgData data = Rot13.stagingEncrypt(FIRST_STRING);
    }
}