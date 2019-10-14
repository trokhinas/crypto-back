package vsu.labs.crypto.algs.digSignature.RSA;

import vsu.labs.crypto.utils.math.PrimesGenerator;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;


public final class RSA {

    private RSA() {}

    /**
     * Метод генерирует ключи c помощью простых чисел, заданной длины
     * @param bits Длина простых чисел, с помощью которых генерируются ключи
     * @return Тройка чисел, где первое - e, второе - n, третье - d(пара (e,n) - открытый ключ, d - закрытый ключ)
     */
    public static BigInteger[] genKeys(int bits){
        if (bits < 81)
            throw new IllegalArgumentException("Keys length must be greater than 80");
        BigInteger[] result = new BigInteger[3];
        Random rand = new Random();
        BigInteger int1 = BigInteger.valueOf(1);
        BigInteger p = PrimesGenerator.getPrime(bits);
        BigInteger q = PrimesGenerator.getPrime(bits);
        result[1] = p.multiply(q);
        BigInteger phi = p.subtract(int1).multiply(q.subtract(BigInteger.valueOf(1)));
        result[0] = new BigInteger(bits - 1, rand);
        while (!phi.gcd(result[0]).equals(int1))
            result[0] = new BigInteger(bits - 1, rand);
        result[2] = genPrivateKey(result[0], phi);
        result[2] = result[2].add(phi).mod(phi);
        return result;
    }

    /**
     * Генерация сообщения с ЭЦП
     * @param privateKey закрытый ключ d
     * @param n произведение простых чисел p и q
     * @param text текст для формирование подписи
     * @return сообщение с ЭЦП(пара - (text, sign))
     */
    public static String[] genMessageWithSign(BigInteger privateKey, BigInteger n, String text) {
        BigInteger hash = getHash(text);
        BigInteger sign = hash.modPow(privateKey, n);
        String[] message = new String[2];
        message[0] = text;
        message[1] = sign.toString(16);
        return message;
    }

    /**
     * Метод для проверки корректности ЭЦП
     * @param publicKey открытый ключ(пара - (e, n))
     * @param message сообщение c ЭЦП для проверки корректности(пара - (text, sign))
     * @return является ли подпись корректной
     */
    public static boolean checkSign(BigInteger[] publicKey, String[] message) {
        BigInteger signToProve = new BigInteger(message[1], 16);
        BigInteger hash = signToProve.modPow(publicKey[0], publicKey[1]);
        BigInteger messageHash = getHash(message[0]);
        return hash.equals(messageHash);
    }

    /**
     * Генерирует закрытый ключ d с заданными e и phi, соответствующий формуле ed = 1 mod phi
     * @return Значение закрытого ключа d
     */
    private static BigInteger genPrivateKey(BigInteger e, BigInteger phi) {
        return xgcd(e, phi)[1];
    }

    /**
     * Расширенный алгоритм Евклида для нахождения коэффициентов, при которых достигается a*k1 + b*k2 = gcd(a,b)
     * @return пара коеффициентов k1 и k2
     */
    private static BigInteger[] xgcd(BigInteger a, BigInteger b) {
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
            digest.update(text.getBytes("utf8"));
            hash = new BigInteger(1, digest.digest());
        } catch (Exception e){
            e.printStackTrace();
        }
        return hash;
    }
}

