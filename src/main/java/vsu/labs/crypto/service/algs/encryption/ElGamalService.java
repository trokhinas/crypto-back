package vsu.labs.crypto.service.algs.encryption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.algs.common.BlocksResponse;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.algs.encryption.elgamal.ElGamal;
import vsu.labs.crypto.dto.crypto.PartitionAlgData;
import vsu.labs.crypto.dto.crypto.StageData;
import vsu.labs.crypto.enums.ResponseBlockEnum;
import vsu.labs.crypto.service.algs.common.DefaultBlocksChecker;
import vsu.labs.crypto.utils.algs.BlockBuilder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ElGamalService {

    private static final List<String> REQUIRED_IDS_ENCRYPT = Arrays.asList(
            "openA", "openP", "openY", "text"
    );
    private static final List<String> REQUIRED_IDS_DECRYPT = Arrays.asList(
            "openA", "openP", "openY", "text", "secretX"
    );

    public BlocksResponse getBlocks() {
        Map<String, ControlPanelBlock> blocks = BlockBuilder.buildMap()
                .withBlock("text", "Текст")
                .withBlock("openA", "(Открытый ключ)a")
                .withBlock("openP", "(Открытый ключ)p")
                .withBlock("openY", "(Открытый ключ)y")
                .withBlock("secretX", "(Закрытый ключ)x")
                .build();

        return BlocksResponse.withEncrypt(blocks)
                .withProperty(ResponseBlockEnum.WithKeysGeneration.getValue());
    }

    public String generateKeys() {
        Pair<ElGamal.OpenKey, ElGamal.SecretKey> keys = ElGamal.generateKeys();
        var open = keys.getFirst();
        var secret = keys.getSecond();

        return "Открытый ключ a = " + open.getA() + "\n" +
                "открытый ключ p = " + open.getP() + "\n " +
                "открытый ключ y = " + open.getY() + "\n" +
                "закрытый ключ x = " + secret.getX();
    }

    public PartitionAlgData generateKeyStaging() {
        List<StageData> stagingData = Arrays.asList(
                StageData.message("Выбирается простое число p длиной 24 бит"),
                StageData.message("Выбирается число a, являющееся превообразным корнем по модулю p"),
                StageData.message("Выбирается число x взаимно простое с числом p - 1"),
                StageData.message("Формируется число y = a^x mod p")
        );

        String result = generateKeys();
        return new PartitionAlgData(stagingData, result);
    }

    public PartitionAlgData stagingEncrypt(Map<String, ControlPanelBlock> blocks) {
        return null;
    }

    public String encrypt(Map<String, ControlPanelBlock> blocks) {
        DefaultBlocksChecker.checkOnlyRequiredBlocks(blocks, REQUIRED_IDS_ENCRYPT);
        BigInteger openA = new BigInteger(blocks.get("openA").getValue());
        BigInteger openP = new BigInteger(blocks.get("openP").getValue());
        BigInteger openY = new BigInteger(blocks.get("openY").getValue());
        String text = blocks.get("text").getValue();
        ElGamal.OpenKey openKey = new ElGamal.OpenKey(openA, openP, openY);

        StringBuilder builder = new StringBuilder();
        text.chars().forEach(i -> {
            BigInteger value = BigInteger.valueOf(i);
            Pair<BigInteger, BigInteger> pair = ElGamal.encrypt(value, openKey);
            log.info("pair - {}", pair);

            Character c1 = (char) pair.getFirst().longValue();
            Character c2 = (char) pair.getSecond().longValue();
            builder.append(c1).append(c2);
        });

        return builder.toString();
    }

    public PartitionAlgData stagingDecrypt(Map<String, ControlPanelBlock> blocks) {
        return null;
    }

    public String decrypt(Map<String, ControlPanelBlock> blocks) {
        DefaultBlocksChecker.checkOnlyRequiredBlocks(blocks, REQUIRED_IDS_ENCRYPT);
        BigInteger openA = new BigInteger(blocks.get("openA").getValue());
        BigInteger openP = new BigInteger(blocks.get("openP").getValue());
        BigInteger openY = new BigInteger(blocks.get("openY").getValue());
        BigInteger secretX = new BigInteger(blocks.get("secretX").getValue());
        String text = blocks.get("text").getValue();
        ElGamal.OpenKey openKey = new ElGamal.OpenKey(openA, openP, openY);
        ElGamal.SecretKey secretKey = new ElGamal.SecretKey(secretX);

        StringBuilder builder = new StringBuilder();
        List<Pair<String, String>> pairs = new ArrayList<>();
        for (int i = 0; i < text.length() / 2; i++) {
            String c1 = String.valueOf(text.charAt(i));
            String c2 = String.valueOf(text.charAt(i + 1));

            pairs.add(Pair.of(c1, c2));
        }
        pairs.stream()
                .map(pair ->
                        Pair.of(
                                BigInteger.valueOf((long) pair.getFirst().charAt(0)),
                                BigInteger.valueOf((long) pair.getSecond().charAt(0))
                        )
                )
                .forEach(pair -> {
                    Character result = (char) ElGamal.decrypt(pair, openKey, secretKey).longValue();
                    builder.append(result);
                });

        return builder.toString();
    }

}
