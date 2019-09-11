package vsu.labs.crypto.algs.encryption.rot13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vsu.labs.crypto.dto.crypto.PartitionAlgData;
import vsu.labs.crypto.dto.crypto.StageData;
import vsu.labs.crypto.utils.StringSplitter;

import java.util.List;
import java.util.stream.Collectors;


public final class Rot13 {
    private static final Logger log = LoggerFactory.getLogger(Rot13.class);

    private static final String DEFAULT_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxy";
    private static final String SHIFTED_ALPHABET = "NOPQRSTUVWXYZABCDEFGHIJKLMnopqrstuvwxyzabcdefghijklm";

    private static final int PARTITION_SIZE = 4;
    private static final StringSplitter splitter = StringSplitter.withPartitionSize(PARTITION_SIZE);

    private static final String STAGE_MESSAGE = "Зашифровано %d процентов сообщения";

    private Rot13() { }

    public static String encrypt(String source) {
        log.info("start method encrypt");
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < source.length() ; i ++) {
            char letter = source.charAt(i);
            char encryptedChar = getShiftedLetter(letter);
            sb.append(encryptedChar);
        }
        return sb.toString();
    }

    public static PartitionAlgData stagingEncrypt(String source) {
        log.info("start method stagingEncrypt with partition");
        List<String> parts = splitter.splitIntoParts(source);

        List<String> encryptedParts = parts.stream()
                .map(Rot13::encrypt)
                .collect(Collectors.toList());
        for (int i = 1; i < encryptedParts.size(); i++) {
            String concatPart = encryptedParts.get(i - 1).concat(encryptedParts.get(i));
            encryptedParts.set(i, concatPart);
        }
        List<StageData> data = encryptedParts.stream().map(part -> {
            String message = generateMessage(source, part);
            return StageData.withData(message, part);
        }).collect(Collectors.toList());
        return new PartitionAlgData(data, data.get(data.size() - 1));
    }

    private static String generateMessage(String source, String part) {
        int percent = (int) (((float)part.length() / source.length()) * 100);
        return String.format(STAGE_MESSAGE, percent);
    }

    // так как шифрованием происходит сдвигом на 13 букв,
    // то процессы шифрования и расшифровки идентичны
    // отдельный метод прописан, для удобства, чтобы не путаться в нейминге
    public static String decrypt(String source) {
        return encrypt(source);
    }

    private static char getShiftedLetter(char c) {
        int i = DEFAULT_ALPHABET.indexOf(c);
        return i != -1 ? SHIFTED_ALPHABET.charAt(i) : c;
    }
}
