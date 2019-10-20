package vsu.labs.crypto.service.algs.sign;

import org.springframework.stereotype.Service;
import vsu.labs.crypto.algs.common.BlocksResponse;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.algs.digSignature.RSA.RSA;
import vsu.labs.crypto.dto.crypto.PartitionAlgData;
import vsu.labs.crypto.dto.crypto.StageData;
import vsu.labs.crypto.service.algs.common.DefaultBlocksChecker;
import vsu.labs.crypto.utils.algs.BlockBuilder;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RsaService {

    private static final List<String> stagesGenerateKeys = Arrays.asList(
            "1: Выбираем простые числа p и q",
            "2: Вычисляем n = p * q",
            "3: Вычисляем значение функции Эйлера от (p-1)(q-1)",
            "4: Выбирается открытая экспонента e",
            "5: Находим d удовлетворяющее условию ed = 1 mod(p-1)(q-1)",
            "6: Создаются открытый(e,n) и закрытй(d) ключи"
    );
    private static final List<String> stagesGenerateMessage = Arrays.asList(
            "1: Создаём секретную подпись с помощью секретного ключа {d,n}: m^d mod n",
            "2: Возвращаем пару сообщение подпись {m,n}"
    );
    private static final List<String> stagesCheckMessage = Arrays.asList(
            "1: Берём полученный открытый ключ {e,n}",
            "2: Вычисляем прообраз особщения из подписи: m' = s^e mod n",
            "3: Проверяем подлинность подписи и неизменность сообщения, сравнивая m и m'"
    );

    public BlocksResponse getBlocks() {
        Map<String, ControlPanelBlock> blocks = BlockBuilder.buildMap()
                .withBlock("text", "Текст")
                .withBlock("sign", "Подпись")
                .withBlock("openE", "(Открытый ключ)e")
                .withBlock("openN", "(Открытый ключ)n")
                .withBlock("secretD", "(Закрытый ключ)d")
                .build();

        return BlocksResponse.withCheckSign(blocks);
    }

    public  String generateKeys() {
        var keys = RSA.genKeys(81);
        var open = keys.getFirst();
        var secret = keys.getSecond();
        String response = "открытый ключ e = " + open.getE() + "\n откртый ключ n = " + open.getN() + "\n закрытый ключ d = " + secret.getD();
        return response;

    }

    public String checkSign(Map<String, ControlPanelBlock> blocks) {
        List<String> listName = new ArrayList<>(Arrays.asList("text", "sign", "openE", "openN"));
        DefaultBlocksChecker.checkBlocks(blocks, listName);
        BigInteger openE = BigInteger.valueOf(Integer.valueOf(blocks.get("openE").getValue()));
        BigInteger openN = BigInteger.valueOf(Integer.valueOf(blocks.get("openN").getValue()));
        boolean check = RSA.checkSign(new RSA.OpenKey(openE, openN), new RSA.Message(blocks.get("text").getValue(), blocks.get("sign").getValue()));
        if (check) {
            return "Проверка по открытому ключу пройдена успешно";
        }
        return "Проверка по открытому ключу провалена";
    }

    public PartitionAlgData stagingGenerateKeys() {
        List<StageData> allStages = stagesGenerateKeys.stream()
                .map(StageData::message)
                .collect(Collectors.toList());

        return new PartitionAlgData(allStages,generateKeys());
    }
    public PartitionAlgData stagingGenerateMessage(BigInteger secretKey,BigInteger n,String message) {
        List<StageData> allStages = stagesGenerateMessage.stream()
                .map(StageData::message)
                .collect(Collectors.toList());

        return new PartitionAlgData(allStages,RSA.genMessageWithSign(new RSA.SecretKey(secretKey),n,message));
    }
    public PartitionAlgData stagingCheckMessage(Map<String, ControlPanelBlock> blocks) {
        List<StageData> allStages = stagesCheckMessage.stream()
                .map(StageData::message)
                .collect(Collectors.toList());

        return new PartitionAlgData(allStages,checkSign(blocks));
    }

}
