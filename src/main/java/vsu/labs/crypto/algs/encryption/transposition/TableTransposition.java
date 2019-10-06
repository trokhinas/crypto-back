package vsu.labs.crypto.algs.encryption.transposition;

import lombok.extern.slf4j.Slf4j;
import vsu.labs.crypto.dto.crypto.PartitionAlgData;
import vsu.labs.crypto.dto.crypto.StageData;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@Slf4j
public final class TableTransposition {

    public static String encrypt(String source, BigInteger key) {
        log.info("process method encrypt with source = {}, key = {}", source, key);
        source = source.replace("\n", "");
        Table table = Table.withSize(BigInteger.valueOf(source.length()), key);

        return table.encrypt(source);
    }

    public static String decrypt(String source, BigInteger key) {
        log.info("process method decrypt with source = {}, key = {}", source, key);
        source = source.replace("\n", "");
        Table table = Table.withSize(BigInteger.valueOf(source.length()), key);

        return table.decrypt(source);
    }

    public static PartitionAlgData stagingEncrypt(String source, BigInteger key) {
        source = source.replace("\n", "");
        Table table = Table.withSize(BigInteger.valueOf(source.length()), key);
        String result = table.encrypt(source);

        List<StageData> stageData = Arrays.asList(
                StageData.message("Создана таблица размера " + table.getSize()),
                StageData.withData("В неё слева направо, сверху вниз вписыывается сообщение", source),
                StageData.withData("Из нее считывается сообщение сверху вниз, слева направо", result)
        );
        return new PartitionAlgData(stageData, result);
    }

    public static PartitionAlgData stagingDecrypt(String source, BigInteger key) {
        source = source.replace("\n", "");
        Table table = Table.withSize(BigInteger.valueOf(source.length()), key);
        String result = table.decrypt(source);

        List<StageData> stageData = Arrays.asList(
                StageData.message("Создана таблица размера " + table.getSize()),
                StageData.withData("В неё сверху вниз, слева направо вписыывается сообщение", source),
                StageData.withData("Из нее считывается сообщение слева направо, сверху вниз", result)
        );
        return new PartitionAlgData(stageData, result);
    }
}
