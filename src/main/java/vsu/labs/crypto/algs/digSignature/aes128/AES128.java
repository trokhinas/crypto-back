package vsu.labs.crypto.algs.digSignature.aes128;

import vsu.labs.crypto.dto.crypto.PartitionAlgData;
import vsu.labs.crypto.dto.crypto.StageData;
import vsu.labs.crypto.exceptions.LogicException;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


public class AES128 {

    private static SecretKeySpec secretKey;
    private static byte[] key;

    private static final List<String> stagesEncrypt = Arrays.asList(
            "1: Получаем хэш от пароля",
            "2: Преобрзовываем хэш в ключ по правилам описанным в стандарте AES128",
            "3: Разбиваем текст на блоки по 128 бит",
            "4: Зашифровываем каждый блок функцией chiper"
    );
    private static final List<String> stagesDecrypt = Arrays.asList(
            "1: Разбиваем текст на блоки по 128 бит",
            "2: Применяем chiperDecrypt с секретным ключом к каждому блоку",
            "3: Объединяем расшифрованные блоки",
            "4: Сверяем получившийся ответ с исходным сообщением"
    );

    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String strToEncrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static String encode(String message, String secret) {
        String sign = encrypt(message, secret);
        if (sign == null)
            //здесь illegal так как такая ошибка невозможна
            throw new IllegalStateException();
        return sign;
    }

    public static String decode(String message, String secret, String sign) {
        String checkedSign = decrypt(sign, secret);
        if (checkedSign == null)
            //здесь наш exception потому что при ошибочной подписи decrypt возвращает null
            throw new LogicException("Проверка подписи провалена");
        return checkedSign.equals(message) ? "Проверка подписи пройдена" : "Проверка подписи провалена";
    }

    public static PartitionAlgData stagingEncrypt(String message, String secret) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        String sign = encrypt(message, secret);
        List<StageData> allStages = stagesEncrypt.stream()
                .map(StageData::message)
                .collect(Collectors.toList());
        return new PartitionAlgData(allStages, sign);

    }

    public static PartitionAlgData stagingDecrypt(String message, String secret, String sign) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String checkedSign = decrypt(sign, secret);
        List<StageData> allStages = stagesDecrypt.stream()
                .map(StageData::message)
                .collect(Collectors.toList());
        return new PartitionAlgData(allStages, checkedSign);
    }
}