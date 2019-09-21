package vsu.labs.crypto.algs.encryption.transposition;

import org.springframework.data.util.Pair;

import java.math.BigInteger;

public final class TableTransposition {

    public static Pair<String, String> encrypt(String source, BigInteger key) {
        Table table = Table.withSize(key);
        String encryptedMessageWithSpacing =  table.encrypt(source);
        String encryptedMessageForUser = buildMessageForUser(encryptedMessageWithSpacing);

        return Pair.of(encryptedMessageForUser, encryptedMessageWithSpacing);
    }

    public static String decrypt(String encryptedSourceWithSpacing, BigInteger key) {
        Table table = Table.withSize(key);
        String messageWithSpacing =  table.encrypt(encryptedSourceWithSpacing);
        String messageForUser = buildMessageForUser(messageWithSpacing);

        return messageForUser;
    }

    private static String buildMessageForUser(String message) {
        return message.replaceAll(Table.SPACING_SYMBOL, "");
    }
}
