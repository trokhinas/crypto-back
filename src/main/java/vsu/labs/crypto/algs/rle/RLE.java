package vsu.labs.crypto.algs.rle;

import vsu.labs.crypto.dto.crypto.PartitionAlgData;
import vsu.labs.crypto.dto.crypto.StageData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RLE {


    private static final List<String> stagesEncrypt = Arrays.asList(
            "Идём по строке следующим алгоритмом:",
            "1: Берём текущую букву и берём слудющею за ней",
            "2: Если следующая буква равна текущей, добавить к счётчику +1",
            "3: Если следующая буква не равна текущей, записать в выходную строку текущую букву и значеничея счётчика за ней"
    );
    private static final List<String> stagesDecrypt = Arrays.asList(
            "1:Если следующий символ не цифра,то записываем текущий в выходную строку",
            "2:Если следующий символ это фицра,то идём далее по строке,пока не наткнёмся на букву",
            "3:Все полученные цифры складываем в число и записываем текущую букву, это количество раз",
            "Переходим к следующему символу(в случае 2-3,к следующим символовом становится буква после последнего числа)"
    );

    public static String encode(String str) {
        if (str == null || str.equals("")) {
            return str;
        }
        char currentChar = str.charAt(0);
        int currentCharCount = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= str.length(); i++) {
            char c = i < str.length() ? str.charAt(i) : 0;
            if (i == str.length() || currentCharCount == 9 || c != currentChar) {
                sb.append(currentChar);
                if (currentCharCount > 1) {
                    sb.append((char) (currentCharCount + '0'));
                }
                currentCharCount = 1;
                currentChar = c;
            } else {
                currentCharCount++;
            }
        }
        return sb.toString();
    }

    public static String decode(String str) {
        if (str == null || str.equals("")) {
            return str;
        }
        char currentChar = str.charAt(0);
        int currentCharCount = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= str.length(); i++) {
            char c = i < str.length() ? str.charAt(i) : 0;
            if (c >= '1' && c <= '9') {
                currentCharCount = c - '0';
                for (int j = 0; j < currentCharCount - 1; j++)
                    sb.append(currentChar);
            } else {
                currentChar = c;
                sb.append(currentChar);
            }

        }
        return sb.toString();
    }

    public static PartitionAlgData StagingEncode(String str){
        String result = encode(str);
        List<StageData> allStages = stagesEncrypt.stream()
                .map(StageData::message)
                .collect(Collectors.toList());
        return new PartitionAlgData(allStages,result);
    }

    public static PartitionAlgData StagingDecode(String str) {
        String result = decode(str);
        List<StageData> allStages = stagesDecrypt.stream()
                .map(StageData::message)
                .collect(Collectors.toList());
        return new PartitionAlgData(allStages, result);
    }
}
