package vsu.labs.crypto.algs.encryption.elgamal;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import vsu.labs.crypto.utils.common.Bounds;
import vsu.labs.crypto.utils.math.MathUtils;
import vsu.labs.crypto.utils.math.PrimesGenerator;
import vsu.labs.crypto.utils.math.RandomUtils;

import java.math.BigInteger;

import static vsu.labs.crypto.utils.math.MathConstants.ONE;

public final class ElGamal {
    private static final Logger log = LoggerFactory.getLogger(ElGamal.class);

    private static final Integer P_BITS = 8;

    public static Pair<OpenKey, SecretKey> generateKeys() {
        log.info("process method generateKeys");
        BigInteger primeP = PrimesGenerator.getPrime(P_BITS);
        BigInteger a = getPrimitiveRoot(primeP);
        BigInteger randomX = getCoprimeIntegerWith(primeP, Bounds.of(ONE, primeP.subtract(ONE)));
        BigInteger y = a.modPow(randomX, primeP);

        OpenKey openKey = new OpenKey(a, primeP, y);
        SecretKey secretKey = new SecretKey(randomX);
        return Pair.of(openKey, secretKey);
    }

    public static Pair<BigInteger, BigInteger> encrypt(BigInteger source, OpenKey openKey) {
        BigInteger secretK = getCoprimeIntegerWith(openKey.p.subtract(ONE), Bounds.of(ONE, openKey.p));
        BigInteger gamma = openKey.a.modPow(secretK, openKey.p);
        BigInteger beta = source.multiply(openKey.y.modPow(secretK, openKey.p));

        return Pair.of(gamma, beta);
    }

    // TODO не работает функция расшифровки, возможно проблема в формуле: надо изучить операции по модулю
    public static BigInteger decrypt(Pair<BigInteger, BigInteger> text, OpenKey openKey, SecretKey secretKey) {
        BigInteger gammaExponent = text.getFirst().pow(secretKey.x.negate().intValue());
        BigInteger betaModP = text.getSecond().mod(openKey.p);

        return gammaExponent.multiply(betaModP);
    }

    // TODO
    // реализовать получение примитивного корня по модулю value
    // на данный момент генерируется случайно число и проверяется, условие
    // но не для всех числе существуют примитивыне корни по модулю
    private static BigInteger getPrimitiveRoot(BigInteger modulo) {
        BigInteger probablyRoot = RandomUtils.getRandBigInteger(P_BITS);
        while (!MathUtils.isPrimitiveRootModuloN(probablyRoot, modulo)) {
            probablyRoot = RandomUtils.getRandBigInteger(P_BITS);
        }

        return probablyRoot;
    }

    private static BigInteger getCoprimeIntegerWith(BigInteger value, Bounds bounds) {
        BigInteger randomX = RandomUtils.getRandomBigInteger(ONE, value);
        while (!MathUtils.isCoprimeIntegers(randomX, value.subtract(ONE))) {
            randomX = RandomUtils.getRandomBigInteger(bounds.getLeft(), bounds.getRight());
        }

        return randomX;
    }

    @AllArgsConstructor
    public static class OpenKey {
        private BigInteger a;
        private BigInteger p;
        private BigInteger y;
    }
    @AllArgsConstructor
    public static class SecretKey {
        private BigInteger x;
    }
}
