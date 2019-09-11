package vsu.labs.crypto.utils;

public final class MessageUtils {

    private MessageUtils() { }

    private static final String STAGE_MESSAGE = "Зашифровано %d процентов сообщения";

    public static String generatePercentEncryptMessage(String source, String encryptedPart) {
        float part = (float)encryptedPart.length() / (float)source.length();
        int percent = (int) (part * 100);
        return String.format(STAGE_MESSAGE, percent);
    }
}
