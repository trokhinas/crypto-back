package vsu.labs.crypto.algs.morse;

import org.hibernate.result.Output;

import java.util.Scanner;

public class Morse {
    public static String code(String message) {
        String output = new String();
        String[] hadleMessage = message.split(" ");

        char[] english = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
                ',', '.', '?'};

        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
                "-----", "--..--", ".-.-.-", "..--.."};
        for (int j = 0; j < hadleMessage.length; j++) {
            hadleMessage[j] = hadleMessage[j].toLowerCase();
            for (char symb : hadleMessage[j].toCharArray()) {
                for (int i = 0; i < english.length; i++) {
                    if (english[i] == symb) {
                        output += morse[i]+" ";
                    }
                }
            }
            output+="  ";
        }
        System.out.println(output);
        return output;
    }

    public static String decode(String message) {
        String output = new String();
        String[] hadleMessage = message.split("  ");
        char[] english = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
                ',', '.', '?'};

        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
        };
        for (String x : hadleMessage) {
            String[] word = x.split(" ");
            for (int i = 0; i < word.length; i++) {
                for (int k = 0; k < morse.length; k++) {
                    if (word[i].toLowerCase().equals(""+morse[k])) {
                        output += english[k]+" ";
                    }
                }
            }
            output+="  ";
        }
        System.out.println(output);
        return output;
    }

    public static void main(String[] args) {
        Morse.code("i am gay");
        Morse.decode("..   .- --   --. .- -.--");
    }
}
