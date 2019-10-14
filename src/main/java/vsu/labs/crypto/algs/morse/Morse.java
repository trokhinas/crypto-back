package vsu.labs.crypto.algs.morse;

import org.hibernate.result.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vsu.labs.crypto.algs.encryption.rot13.Rot13;
import vsu.labs.crypto.dto.crypto.PartitionAlgData;
import vsu.labs.crypto.dto.crypto.StageData;
import vsu.labs.crypto.utils.data.ListIncrementDataBuilder;
import vsu.labs.crypto.utils.data.MessageUtils;
import vsu.labs.crypto.utils.data.StringSplitter;
import vsu.labs.crypto.utils.math.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class Morse {

    private static final Logger log = LoggerFactory.getLogger(Morse.class);
    private static final int PARTITION_SIZE = 4;
    private static final StringSplitter splitter = StringSplitter.withPartitionSize(PARTITION_SIZE);

    private static final String STAGE_MESSAGE = "Зашифровано %d процентов сообщения";
    private static final String delimiter = " ";
    private static final String delimiterOfWord = "  ";
    final static char[] english = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            ',', '.', '?'};
    final static String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
            ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
            "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
            "-----", "--..--", ".-.-.-", "..--.."};

    private Morse() {
    }

    public static String code(String message) {
        String output = new String();
        String[] handleMessage = message.split(delimiter);

        for (int j = 0; j < handleMessage.length; j++) {
            handleMessage[j] = handleMessage[j].toLowerCase();
            for (char symb : handleMessage[j].toCharArray()) {
                for (int i = 0; i < english.length; i++) {
                    if (english[i] == symb)
                        output += morse[i] + " ";
                }
            }
            output += delimiter;
        }

        return output.trim();
    }

    public static String decode(String message) {
        String output = new String();
        String[] handleMessage = message.split(delimiterOfWord);
        for (String x : handleMessage) {
            String[] word = x.split(" ");
            for (int i = 0; i < word.length; i++) {
                for (int k = 0; k < morse.length; k++) {
                    if (word[i].toLowerCase().equals("" + morse[k]))
                        output += english[k];
                }
            }
            output += delimiter;
        }
        return output.trim();
    }

    public static PartitionAlgData stagingCode(String message) {
        log.info("start method stagingCode with partition");
        String output = new String();
        String[] handleMessage = message.split(delimiter);
        List<StageData> stageData = new ArrayList<>(10);
        int counter = 0;
        for (int j = 0; j < handleMessage.length; j++) {
            handleMessage[j] = handleMessage[j].toLowerCase();
            for (char symb : handleMessage[j].toCharArray()) {
                for (int i = 0; i < english.length; i++) {
                    if (english[i] == symb) {
                        output += morse[i] + delimiter;
                        stageData.add(counter, new StageData("Зашифровано: " + (100.0 / message.toCharArray().length *counter)+"%", output));
                        counter++;
                    }
                }
            }
            output += delimiter;
            stageData.get(counter - 1).setData(stageData.get(counter - 1).getData() + "  ");
        }

        stageData.get(counter-1).setData((""+stageData.get(counter-1).getData()).trim());
        stageData.get(counter - 1).setMessage("Зашифровано: "+100+ "%");
        return new PartitionAlgData(stageData, output.trim());
    }

    public static PartitionAlgData stagingDecode(String message) {
        log.info("start method stagingDecode with partition");
        List<StageData> stageData = new ArrayList<>();
        String output = new String();
        String[] handleMessage = message.split(delimiterOfWord);
        int counter = 0,percent=0;
        for (String x : handleMessage) {
            percent++;
            String[] word = x.split(delimiter);
            for (int i = 0; i < word.length; i++) {
                for (int k = 0; k < morse.length; k++) {
                    if (word[i].toLowerCase().equals("" + morse[k])) {
                        output += english[k];
                    }
                }
            }
            stageData.add(counter, new StageData("Расшифровано: " + (100.0 / handleMessage.length * percent+"%"), output));
            counter++;
            output += delimiter;
            stageData.get(counter-1).setData(stageData.get(counter-1).getData() + "  ");
        }
        stageData.get(counter-1).setData((""+stageData.get(counter-1).getData()).trim());
        stageData.get(counter - 1).setMessage("Расшифровано: "+100+"%");
        return new PartitionAlgData(stageData, output.trim());
    }

    public static void main(String[] args) {
        System.out.println(Morse.code("hey i am tired").equals(".... . -.--  ..  .- --  - .. .-. . -.."));
        System.out.println(code("hey i am not a gay"));
        System.out.println(Morse.decode(".... . -.--  ..  .- --  - .. .-. . -..").equals("hey i am tired"));
        System.out.println(stagingDecode(".... . -.--  ..  .- --  -. --- -  .-  --. .- -.--").getStageData().get(stagingDecode(".... . -.--  ..  .- --  -. --- -  .-  --. .- -.--").getStageData().size()-1).getData());
        System.out.println(stagingCode("hey i am not a gay").getStageData().get(stagingCode("hey i am not a gay").getStageData().size()-1));
        System.out.println(stagingDecode(".... . -.--  ..  .- --  - .. .-. . -.."));
    }

}
