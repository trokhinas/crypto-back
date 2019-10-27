package vsu.labs.crypto.service.algs.aes128;

import org.springframework.stereotype.Service;
import vsu.labs.crypto.algs.common.BlocksResponse;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.algs.digSignature.aes128.AES128;
import vsu.labs.crypto.algs.morse.Morse;
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
public class Aes128Service {
    private static final List<String> REQUIRED_BLOCKS = Collections.singletonList("text");

    public BlocksResponse getBlocks() {
        Map<String, ControlPanelBlock> blockMap = BlockBuilder.buildMap()
                .withBlock("text", "Текст")
                .withBlock("secretKey","секретный ключ")
                .withBlock("sign","подпись")
                .build();

        return BlocksResponse.withCheckSign(blockMap);
    }

    public String encode(Map<String, ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        String secretKey = getValueFromBlockWithId("secretKey",blocks);
        return AES128.encode(text,secretKey);
    }

    public String decode(Map<String, ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        String secretKey = getValueFromBlockWithId("secretKey",blocks);
        String sign = getValueFromBlockWithId("sign",blocks);
        return AES128.decode(text,secretKey,sign);
    }


    public PartitionAlgData stagingEncode(Map<String, ControlPanelBlock> blocks) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        String secretKey = getValueFromBlockWithId("secretKey",blocks);
        return AES128.stagingEncrypt(text,secretKey);
    }

    public PartitionAlgData stagingDecode(Map<String, ControlPanelBlock> blocks) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        String secretKey = getValueFromBlockWithId("secretKey",blocks);
        String sign = getValueFromBlockWithId("sign",blocks);
        return AES128.stagingDecrypt(text,secretKey,sign);
    }
    private void checkBlocks(Map<String, ControlPanelBlock> blocks) {
        DefaultBlocksChecker.checkBlocksAllRequired(blocks, REQUIRED_BLOCKS);
    }

    private String getValueFromBlockWithId(String id, Map<String, ControlPanelBlock> blocks) {
        return blocks.get(id).getValue();
    }
}
