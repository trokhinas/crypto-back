package vsu.labs.crypto.service.algs.rle;

import org.springframework.stereotype.Service;
import vsu.labs.crypto.algs.common.BlocksResponse;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.algs.digSignature.aes128.AES128;
import vsu.labs.crypto.algs.rle.RLE;
import vsu.labs.crypto.dto.crypto.PartitionAlgData;
import vsu.labs.crypto.service.algs.common.DefaultBlocksChecker;
import vsu.labs.crypto.utils.algs.BlockBuilder;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class RLEService {
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
        return RLE.encode(text);
    }

    public String decode(Map<String, ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        return RLE.decode(text);
    }


    public PartitionAlgData stagingEncode(Map<String, ControlPanelBlock> blocks) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        return RLE.StagingEncode(text);
    }

    public PartitionAlgData stagingDecode(Map<String, ControlPanelBlock> blocks) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        return RLE.StagingDecode(text);
    }
    private void checkBlocks(Map<String, ControlPanelBlock> blocks) {
        DefaultBlocksChecker.checkBlocksAllRequired(blocks, REQUIRED_BLOCKS);
    }

    private String getValueFromBlockWithId(String id, Map<String, ControlPanelBlock> blocks) {
        return blocks.get(id).getValue();
    }
}
