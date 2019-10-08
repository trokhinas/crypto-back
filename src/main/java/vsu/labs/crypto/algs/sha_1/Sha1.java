package vsu.labs.crypto.algs.sha_1;

import vsu.labs.crypto.dto.crypto.PartitionAlgData;
import vsu.labs.crypto.dto.crypto.StageData;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.DatatypeConverter;

public class Sha1 {
    static final public String[] stages = {"Преобразование входных данных в битовое представление",
            "Разделение битового представления на блоки по 512 бит",
    "Выделение отрезка в 64 бита в для записи длины сообщения",
    "Разделение каждого блока на слова в 32 бита",
    "Преобразование каждых 16 слов в 80 с помощью 4 этапов по 20 операций в каждом по формуле:" +
            "Wt = Mt Wt = (Wt-3 XOR Wt-8 XOR Wt-14 XOR Wt-16) << 1 ",
    "Инициализация пяти 32 битных перменных \n" +
            " A = a = 0x67452301\n" +
            "B = b = 0xEFCDAB89\n" +
            "C = c = 0x98BADCFE\n" +
            "D = d = 0x10325476\n" +
            "E = e = 0xC3D2E1F0\n",
    "В цикле для t 0 до 79:\n" +
            "temp = (a << 5) + Ft(b,c,d)+e+Wt+Kt)\n "+
            "e = d\n"+
            "d = c \n"+
            "c = b << 30\n"+
            "b = a\n" +
            "a = temp\n",
    "Текущие a,b,c,d,e прибавляются к исходным A,B,C,D,E соответственно.Начинается аналогичная обработка следующего набора слов",
    "Получение итогового сообщения объединением пяти 32-битных слов в одно 160-битное сообщение"
    };
    public static PartitionAlgData stagingHash(String input) {
        String sha1;
        try {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(input.getBytes(StandardCharsets.UTF_8), 0, input.length());
            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException();
        }
        List<StageData> allStages = new ArrayList<>();
        for (int i = 0; i< stages.length;i++){
            allStages.add(new StageData(stages[i],null));
        }

        return new PartitionAlgData(allStages,sha1);
    }

    public static String hash(String input) {
        String sha1;
        try {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(input.getBytes(StandardCharsets.UTF_8), 0, input.length());
            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
            return sha1;
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException();
        }
    }

}
