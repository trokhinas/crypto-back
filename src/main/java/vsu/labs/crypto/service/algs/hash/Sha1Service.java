package vsu.labs.crypto.service.algs.hash;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.algs.common.BlocksResponse;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.algs.sha_1.Sha1;
import vsu.labs.crypto.dto.crypto.PartitionAlgData;
import vsu.labs.crypto.service.algs.common.DefaultBlocksChecker;
import vsu.labs.crypto.utils.algs.BlockBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class Sha1Service {

    private static final List<String> REQUIRED_IDS = Collections.singletonList("text");

    public BlocksResponse getBlocks() {
        Map<String, ControlPanelBlock> blockMap = BlockBuilder.buildMap()
                .withBlock("text", "Текст")
                .build();

        return BlocksResponse.withStart(blockMap);
    }

    public String hash(Map<String, ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = blocks.get("text").getValue();

        return Sha1.hash(text);
    }

    public PartitionAlgData stagingHash(Map<String, ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = blocks.get("text").getValue();

        return Sha1.stagingHash(text);
    }

    public void checkBlocks(Map<String, ControlPanelBlock> blocks) {
        DefaultBlocksChecker.checkBlocksAllRequired(blocks, REQUIRED_IDS);
    }
}
