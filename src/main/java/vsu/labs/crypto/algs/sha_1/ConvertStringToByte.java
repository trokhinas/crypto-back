package vsu.labs.crypto.algs.sha_1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ConvertStringToByte {
    public static List<Integer[]> convert(String message) {
        List<Integer[]> listOfArrayByte = new ArrayList<>();
        int counter = 0;
        for (char symb : message.toCharArray()) { //записываем каждый символ в байт
            int asciiSymb = (int) symb; //получаем код ascii
            listOfArrayByte.set(counter, new Integer[8]);
            int numberOfStepDelim = 8 - checkLengthCodeOfSymb(asciiSymb);
            while (asciiSymb % 2 != 0) { //превращаем код символа в двоичную систему и записываем с нужной позиции и до конца
                listOfArrayByte.get(counter)[numberOfStepDelim] = asciiSymb % 2;
                asciiSymb /= 2;
                numberOfStepDelim++;
            }
            numberOfStepDelim = 8 - checkLengthCodeOfSymb(asciiSymb) - 1;
            while (numberOfStepDelim >= 0) { //забиваем нулями всё начало байта
                listOfArrayByte.get(counter)[numberOfStepDelim] = 0;
                numberOfStepDelim--;
            }
            counter++;
        }
        BigInteger lengthOfMessageInByte = BigInteger.valueOf(listOfArrayByte.size() / 64); //количество отрезков по 512 бит
        if (listOfArrayByte.size() % 64 == 0) { //если длина кратна 512

        }

        return null;
    }

    public static int checkLengthCodeOfSymb(int symbCode) {
        if (symbCode <= 1) {
            return 1;
        } else if (symbCode <= 3) {
            return 2;
        } else if (symbCode <= 7) {
            return 3;
        } else if (symbCode <= 15) {
            return 4;
        } else if (symbCode <= 31) {
            return 5;
        } else if (symbCode <= 63) {
            return 6;
        } else if (symbCode <= 127) {
            return 7;
        } else return 0;
    }

    public static void main(String[] args) {
        ConvertStringToByte.convert("hey1");
    }
}
