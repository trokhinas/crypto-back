package vsu.labs.crypto.service.algs.encryption;

import org.springframework.stereotype.Service;
import vsu.labs.crypto.algs.common.BlocksResponse;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.algs.encryption.transposition.TableTransposition;
import vsu.labs.crypto.dto.crypto.PartitionAlgData;
import vsu.labs.crypto.service.algs.common.DefaultBlocksChecker;
import vsu.labs.crypto.utils.algs.BlockBuilder;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class TableTranspositionService {

    private static final List<String> REQUIRED_BLOCKS = Arrays.asList("text", "key");

    public BlocksResponse getAlgorithmBlocks() {
        Map<String, ControlPanelBlock> blockMap = BlockBuilder.buildMap()
                .withBlock("text", "Текст")
                .withBlock("key", "Ключ")
                .build();

        return BlocksResponse.withEncrypt(blockMap);
    }

    public String encrypt(Map<String, ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        BigInteger key = BigInteger.valueOf(Long.parseLong(getValueFromBlockWithId("key", blocks)));
        return TableTransposition.encrypt(text, key);
    }

    public String decrypt(Map<String, ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        BigInteger key = BigInteger.valueOf(Long.parseLong(getValueFromBlockWithId("key", blocks)));
        return TableTransposition.decrypt(text, key);
    }

    public PartitionAlgData stagingEncrypt(Map<String, ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        BigInteger key = BigInteger.valueOf(Long.parseLong(getValueFromBlockWithId("key", blocks)));
        return TableTransposition.stagingEncrypt(text, key);
    }

    public PartitionAlgData stagingDecrypt(Map<String, ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        BigInteger key = BigInteger.valueOf(Long.parseLong(getValueFromBlockWithId("key", blocks)));
        return TableTransposition.stagingDecrypt(text, key);
    }

    private void checkBlocks(Map<String, ControlPanelBlock> blocks) {
        DefaultBlocksChecker.checkBlocks(blocks, REQUIRED_BLOCKS);
    }

    private String getValueFromBlockWithId(String id, Map<String, ControlPanelBlock> blocks) {
        return blocks.get(id).getValue();
    }
}
