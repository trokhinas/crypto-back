package vsu.labs.crypto.algs.encryption.elgamal;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import vsu.labs.crypto.utils.math.MathUtils;
import vsu.labs.crypto.utils.math.PrimesGenerator;
import vsu.labs.crypto.utils.math.RandomUtils;

import java.math.BigInteger;

import static vsu.labs.crypto.utils.math.MathConstants.ONE;

public final class ElGamal {
    private static final Logger log = LoggerFactory.getLogger(ElGamal.class);

    private static final Integer P_BITS = 8;

    public Pair<OpenKey, SecretKey> generateKeys() {
        log.info("process method generateKeys");
        BigInteger primeP = PrimesGenerator.getPrime(P_BITS);
        BigInteger a = getPrimitiveRoot(primeP);
        BigInteger randomX = RandomUtils.getRandomBigInteger(ONE, primeP);
        while (!MathUtils.isCoprimeIntegers(randomX, primeP.subtract(ONE))) {
            randomX = RandomUtils.getRandomBigInteger(ONE, primeP);
        }
        BigInteger y = a.modPow(randomX, primeP);
        OpenKey openKey = new OpenKey(a, primeP, y);
        SecretKey secretKey = new SecretKey(randomX);

        return Pair.of(openKey, secretKey);
    }

    public String encrypt(String source) {
        var keys = generateKeys();

        return null;
    }

    // TODO реализовать получение примитивного корня по модулю value
    private BigInteger getPrimitiveRoot(BigInteger value) {
        return BigInteger.ONE;
    }

    @AllArgsConstructor
    private static class OpenKey {
        private BigInteger a;
        private BigInteger p;
        private BigInteger y;
    }
    @AllArgsConstructor
    private static class SecretKey {
        private BigInteger x;
    }
}
