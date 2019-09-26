package vsu.labs.crypto.algs.encryption.elgamal;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import vsu.labs.crypto.algs.encryption.elgamal.utils.GenerationUtils;
import vsu.labs.crypto.utils.common.Bounds;
import vsu.labs.crypto.utils.math.MathUtils;
import vsu.labs.crypto.utils.math.ModularUtils;
import vsu.labs.crypto.utils.math.PrimesGenerator;
import vsu.labs.crypto.utils.math.RandomUtils;

import java.math.BigInteger;

import static vsu.labs.crypto.utils.math.MathConstants.ONE;

public final class ElGamal {
    private static final Logger log = LoggerFactory.getLogger(ElGamal.class);

    private static final Integer P_BITS = 24;

    public static Pair<OpenKey, SecretKey> generateKeys() {
        log.info("process method generateKeys");
        BigInteger primeP = PrimesGenerator.getPrime(P_BITS);
        BigInteger a = getPrimitiveRoot(primeP);
        BigInteger randomX = getCoprimeIntegerWith(primeP.subtract(ONE), Bounds.of(ONE, primeP));
        BigInteger y = a.modPow(randomX, primeP);

        OpenKey openKey = new OpenKey(a, primeP, y);
        SecretKey secretKey = new SecretKey(randomX);
        return Pair.of(openKey, secretKey);
    }

    public static Pair<BigInteger, BigInteger> encrypt(BigInteger source, OpenKey openKey) {
        log.info("process method encrypt with source = {}, openKey = {}", source, openKey);
        BigInteger secretK = getCoprimeIntegerWith(openKey.p.subtract(ONE), Bounds.of(ONE, openKey.p));
        BigInteger gamma = openKey.a.modPow(secretK, openKey.p);
        BigInteger yModuleExponent = openKey.y.modPow(secretK, openKey.p);
        BigInteger beta = ModularUtils.modularMul(source, yModuleExponent, openKey.p);

        return Pair.of(gamma, beta);
    }

    public static BigInteger decrypt(Pair<BigInteger, BigInteger> text, OpenKey openKey, SecretKey secretKey) {
        log.info("process method decrypt with text = {}, openKey = {}, secretKey = {}", text, openKey, secretKey);
        BigInteger gamma = text.getFirst();
        BigInteger beta = text.getSecond();

        BigInteger gammaExponent = gamma.modPow(secretKey.x, openKey.p);
        gammaExponent = gammaExponent.modInverse(openKey.p);
        return ModularUtils.modularMul(beta, gammaExponent, openKey.p);
    }

    // TODO
    // реализовать получение примитивного корня по модулю value
    // на данный момент генерируется случайно число и проверяется, условие
    // но не для всех модулей существуют примитивыне корни
    private static BigInteger getPrimitiveRoot(BigInteger modulo) {
        log.info("searching primitive root modulo = {}", modulo);
        return GenerationUtils.whileWithTimeout(
                value -> MathUtils.isPrimitiveRootModuloN(value, modulo),
                () -> RandomUtils.getRandBigInteger(P_BITS)
        );
    }

    private static BigInteger getCoprimeIntegerWith(BigInteger value, Bounds bounds) {
        log.info("searching coprime integer with value = {}, in bounds [{}, {}]", value , bounds.getLeft(), bounds.getRight());
        return GenerationUtils.whileWithTimeout(
                random -> MathUtils.isCoprimeIntegers(random, value.subtract(ONE)),
                () -> RandomUtils.getRandomBigInteger(bounds.getLeft(), bounds.getRight())
        );
    }

    @AllArgsConstructor
    @Data
    public static class OpenKey {
        private BigInteger a;
        private BigInteger p;
        private BigInteger y;
    }
    @AllArgsConstructor
    @Data
    public static class SecretKey {
        private BigInteger x;
    }
}
