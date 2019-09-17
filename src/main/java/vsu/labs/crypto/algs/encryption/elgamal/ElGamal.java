package vsu.labs.crypto.algs.encryption.elgamal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vsu.labs.crypto.utils.math.PrimesGenerator;
import vsu.labs.crypto.utils.math.RandomUtils;

public final class ElGamal {
    private static final Logger log = LoggerFactory.getLogger(ElGamal.class);

    private static final Integer P_BITS = 8;

    public String encrypt(String source) {
        log.info("process method encrypt with source = {}", source);
        Long primeP = PrimesGenerator.getPrimeLong(P_BITS);

        return null;
    }
}
