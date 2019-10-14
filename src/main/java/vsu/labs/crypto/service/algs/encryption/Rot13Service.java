package vsu.labs.crypto.service.algs.encryption;

import org.springframework.stereotype.Service;
import vsu.labs.crypto.algs.common.BlocksResponse;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.algs.encryption.rot13.Rot13;
import vsu.labs.crypto.dto.crypto.PartitionAlgData;
import vsu.labs.crypto.service.algs.common.DefaultBlocksChecker;
import vsu.labs.crypto.utils.algs.BlockBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class Rot13Service {
    private static final List<String> REQUIRED_BLOCKS = Collections.singletonList("text");

    public BlocksResponse getBlocks() {
        Map<String, ControlPanelBlock> blockMap = BlockBuilder.buildMap()
                .withBlock("text", "Текст")
                .build();

        return BlocksResponse.withEncrypt(blockMap);
    }

    public String encrypt(Map<String, ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        return Rot13.encrypt(text);
    }

    public String decrypt(Map<String, ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        return Rot13.decrypt(text);
    }

    public PartitionAlgData stagingEncrypt(Map<String, ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        return Rot13.stagingEncrypt(text);
    }

    public PartitionAlgData stagingDecrypt(Map<String, ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        return Rot13.stagingDerypt(text);
    }

    private void checkBlocks(Map<String, ControlPanelBlock> blocks) {
        DefaultBlocksChecker.checkBlocks(blocks, REQUIRED_BLOCKS);
    }

    private String getValueFromBlockWithId(String id, Map<String, ControlPanelBlock> blocks) {
        return blocks.get(id).getValue();
    }
}
