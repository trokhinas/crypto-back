package vsu.labs.crypto.algs.encryption.transposition;

import lombok.extern.slf4j.Slf4j;
import vsu.labs.crypto.algs.encryption.transposition.utils.OrderedColumnsListBuilder;
import vsu.labs.crypto.dto.crypto.PartitionAlgData;
import vsu.labs.crypto.dto.crypto.StageData;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Slf4j
public final class TableTransposition {

    private static final Function<Integer, String> INPUT_MESSAGE =
            index -> "Считываем из столбца " + (index + 1);
    private static final Function<Integer, String> OUTPUT_MESSAGE =
            index -> "Записываем в столбец " + (index + 1);

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
        List<StageData> inputData = getDataList(key, table::getColumn, INPUT_MESSAGE);

        List<StageData> stageData = new ArrayList<>(Arrays.asList(
                StageData.message("Создана таблица размера " + table.getSize()),
                StageData.message("Она состоит из " + table.getColumnCount() + " столбцов"),
                StageData.message("И в ней содержится " + table.getRowCount() + " строк"),
                StageData.withData("В неё слева направо, сверху вниз вписывается сообщение", source),
                StageData.message("С помощью ключа " + key.toString() + " определяется порядок считывания столбцов")
        ));
        stageData.addAll(inputData);
        return new PartitionAlgData(stageData, result);
    }

    public static PartitionAlgData stagingDecrypt(String source, BigInteger key) {
        source = source.replace("\n", "");
        Table table = Table.withSize(BigInteger.valueOf(source.length()), key);
        String result = table.decrypt(source);
        List<StageData> output = getDataList(key, table::getColumn, OUTPUT_MESSAGE);

        List<StageData> stageData = new ArrayList<>(Arrays.asList(
                StageData.message("Создана таблица размера " + table.getSize()),
                StageData.message("Она состоит из " + table.getColumnCount() + " столбцов"),
                StageData.message("И в ней содержится " + table.getRowCount() + " строк"),
                StageData.withData("В неё сверху вниз, слева направо вписывается сообщение", source),
                StageData.message("С помощью ключа " + key.toString() + " определяется порядок записи столбцов")
        ));
        stageData.addAll(output);
        return new PartitionAlgData(stageData, result);
    }

    private static List<StageData> getDataList(BigInteger key,
                                               Function<Integer, String> dataSupplier,
                                               Function<Integer, String> messageSupplier) {
        List<Integer> columns = OrderedColumnsListBuilder.buildList(key);
        List<StageData> dataList = new ArrayList<>();
        for (var number: columns) {
            var data = dataSupplier.apply(number);
            var message = messageSupplier.apply(number);
            dataList.add(StageData.withData(message, data));
        }
        return dataList;
    }
}
