package vsu.labs.crypto.algs.encryption.transposition;

import org.springframework.data.util.Pair;
import vsu.labs.crypto.utils.data.StringSplitter;

import java.math.BigInteger;
import java.util.List;

public final class TableTransposition {

    // Pair - пара из двух строчек:
    // first - вывод алгоритма, с дополняющими символами, только его можно корректно расшифровать
    // second - строка first без дополняющих символов, для демонтрсации пользователю
    // Такой вакханалии можно избежать, запрещая шифровать строки в таблицы неподходящего размера.
    public static Pair<String, String> encrypt(String source, BigInteger key) {
        int tableSize = getTableSize(key);
        List<String> parts = StringSplitter.withPartitionSize(tableSize).splitIntoParts(source);

        String encryptedMessageWithSpacing = encryptByParts(parts, key);
        String encryptedMessageForUser = buildMessageForUser(encryptedMessageWithSpacing);
        return Pair.of(encryptedMessageForUser, encryptedMessageWithSpacing);
    }

    public static String decrypt(String encryptedSourceWithSpacing, BigInteger key) {
        int tableSize = getTableSize(key);
        List<String> parts = StringSplitter.withPartitionSize(tableSize).splitIntoParts(encryptedSourceWithSpacing);

        String messageWithSpacing =  decryptByParts(parts, key);
        return buildMessageForUser(messageWithSpacing);
    }

    private static String encryptByParts(List<String> parts, BigInteger key) {
        StringBuilder sb = new StringBuilder();

        for (String part: parts) {
            var encryptedPart = encryptPart(part, key);
            sb.append(encryptedPart);
        }
        return sb.toString();
    }

    private static String decryptByParts(List<String> parts, BigInteger key) {
        StringBuilder sb = new StringBuilder();

        for (String part: parts) {
            var encryptedPart = decryptPart(part, key);
            sb.append(encryptedPart);
        }
        return sb.toString();
    }

    private static String decryptPart(String part, BigInteger key) {
        Table table = Table.withSize(key);
        return table.decrypt(part);
    }

    private static String encryptPart(String part, BigInteger key) {
        Table table = Table.withSize(key);
        return table.encrypt(part);
    }

    private static String buildMessageForUser(String message) {
        return message.replaceAll(Table.SPACING_SYMBOL, "");
    }

    private static int getTableSize(BigInteger key) {
        return key.pow(2).intValue();
    }
}
