package vsu.labs.crypto.service.algs.encode;

import org.springframework.stereotype.Service;
import vsu.labs.crypto.algs.common.BlocksResponse;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.algs.morse.Morse;
import vsu.labs.crypto.dto.crypto.PartitionAlgData;
import vsu.labs.crypto.service.algs.common.DefaultBlocksChecker;
import vsu.labs.crypto.utils.algs.BlockBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class MorseService {

    private static final List<String> REQUIRED_BLOCKS = Collections.singletonList("text");

    public BlocksResponse getBlocks() {
        Map<String, ControlPanelBlock> blockMap = BlockBuilder.buildMap()
                .withBlock("text", "Текст")
                .build();

        return BlocksResponse.withEncode(blockMap);
    }

    public String encode(Map<String, ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        return Morse.code(text);
    }

    public String decode(Map<String, ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        return Morse.decode(text);
    }

    public PartitionAlgData stagingEncode(Map<String, ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        return Morse.stagingCode(text);
    }

    public PartitionAlgData stagingDecode(Map<String, ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        return Morse.stagingDecode(text);
    }

    private void checkBlocks(Map<String, ControlPanelBlock> blocks) {
        DefaultBlocksChecker.checkBlocks(blocks, REQUIRED_BLOCKS);
    }

    private String getValueFromBlockWithId(String id, Map<String, ControlPanelBlock> blocks) {
        return blocks.get(id).getValue();
    }
}
