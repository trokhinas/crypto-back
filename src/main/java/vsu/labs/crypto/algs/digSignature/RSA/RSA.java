package vsu.labs.crypto.algs.digSignature.RSA;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.util.Pair;
import vsu.labs.crypto.utils.math.PrimesGenerator;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;

import static vsu.labs.crypto.utils.math.MathConstants.ONE;

public final class RSA {

    private RSA() {}

    /**
     * Метод генерирует ключи c помощью простых чисел, заданной длины
     * @param bits Длина простых чисел, с помощью которых генерируются ключи
     * @return Пара ключей, где пара (e,n) - открытый ключ, d - закрытый ключ
     */
    public static Pair<OpenKey, SecretKey> genKeys(int bits) {
        checkBitLength(bits);
        Random rand = new Random();

        BigInteger p = PrimesGenerator.getPrime(bits);
        BigInteger q = PrimesGenerator.getPrime(bits);
        BigInteger openN = p.multiply(q);
        BigInteger phi = p.subtract(ONE).multiply(q.subtract(ONE));

        BigInteger openE = new BigInteger(bits - 1, rand);
        while (!phi.gcd(openE).equals(ONE))
            openE = new BigInteger(bits - 1, rand);
        BigInteger secretD = genPrivateKey(openE, phi).add(phi).mod(phi);

        return Pair.of(
                new OpenKey(openE, openN),
                new SecretKey(secretD)
        );
    }

    /**
     * Генерация сообщения с ЭЦП
     * @param secretKey закрытый ключ d
     * @param n часть открытого ключа
     * @param text текст для формирование подписи
     * @return сообщение с ЭЦП(пара - (text, sign))
     */
    public static Message genMessageWithSign(SecretKey secretKey, BigInteger n, String text) {
        BigInteger hash = getHash(text);
        BigInteger sign = hash.modPow(secretKey.d, n);

        return new Message(text, sign.toString(16));
    }

    /**
     * Метод для проверки корректности ЭЦП
     * @param openKey открытый ключ(пара - (e, n))
     * @param message сообщение c ЭЦП для проверки корректности(пара - (text, sign))
     * @return является ли подпись корректной
     */
    public static boolean checkSign(OpenKey openKey, Message message) {
        BigInteger signToProve = new BigInteger(message.sign, 16);
        BigInteger hash = signToProve.modPow(openKey.e, openKey.n);
        BigInteger messageHash = getHash(message.text);
        return hash.equals(messageHash);
    }

    /**
     * Генерирует закрытый ключ d с заданными e и phi, соответствующий формуле ed = 1 mod phi
     * @return Значение закрытого ключа d
     */
    private static BigInteger genPrivateKey(BigInteger e, BigInteger phi) {
        return extendGcd(e, phi)[1];
    }

    /**
     * Расширенный алгоритм Евклида для нахождения коэффициентов, при которых достигается a*k1 + b*k2 = gcd(a,b)
     * @return пара коеффициентов k1 и k2
     */
    private static BigInteger[] extendGcd(BigInteger a, BigInteger b) {
        BigInteger x = a, y = b;
        BigInteger[] qrem;
        BigInteger[] result = new BigInteger[3];
        BigInteger x0 = BigInteger.ONE, x1 = BigInteger.ZERO;
        BigInteger y0 = BigInteger.ZERO, y1 = BigInteger.ONE;
        while (true){
            qrem = x.divideAndRemainder(y);
            x = qrem[1];
            x0 = x0.subtract(y0.multiply(qrem[0]));
            x1 = x1.subtract(y1.multiply(qrem[0]));
            if (x.equals(BigInteger.ZERO)) {
                result[0] = y;
                result[1] = y0;
                result[2] = y1;
                return result;
            }
            qrem = y.divideAndRemainder(x);
            y = qrem[1];
            y0 = y0.subtract(x0.multiply(qrem[0]));
            y1 = y1.subtract(x1.multiply(qrem[0]));
            if (y.equals(BigInteger.ZERO)) {
                result[0] = x;
                result[1] = x0;
                result[2] = x1;
                return result;
            }
        }
    }

    /**
     * Генерация хеша строки с использованием sha-1
     */
    private static BigInteger getHash(String text) {
        BigInteger hash = BigInteger.ZERO;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(text.getBytes(StandardCharsets.UTF_8));
            hash = new BigInteger(1, digest.digest());
        } catch (Exception e){
            e.printStackTrace();
        }
        return hash;
    }

    private static void checkBitLength(int bits) {
        if (bits < 81)
            throw new IllegalArgumentException("Keys length must be greater than 80");
    }

    @Data
    @AllArgsConstructor
    public static class OpenKey {
        private BigInteger e;
        private BigInteger n;
    }

    @Data
    @AllArgsConstructor
    public static class SecretKey {
        private BigInteger d;
    }

    @Data
    @AllArgsConstructor
    public static class Message {
        private String text;
        private String sign;
    }
}

