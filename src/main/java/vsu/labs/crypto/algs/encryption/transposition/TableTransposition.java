package vsu.labs.crypto.algs.encryption.transposition;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

@Slf4j
public final class TableTransposition {

    public static String encrypt(String source, BigInteger key) {
        log.info("process method encrypt with source = {}, key = {}", source, key);
        Table table = Table.withSize(BigInteger.valueOf(source.length()), key);

        return table.encrypt(source);
    }

    public static String decrypt(String source, BigInteger key) {
        log.info("process method decrypt with source = {}, key = {}", source, key);
        Table table = Table.withSize(BigInteger.valueOf(source.length()), key);

        return table.decrypt(source);
    }

    private static String buildMessageForUser(String message) {
        return message.replaceAll(Table.SPACING_SYMBOL, "");
    }
}
