package vsu.labs.crypto.service.algs.encryption;

import org.springframework.stereotype.Service;
import vsu.labs.crypto.algs.common.BlocksResponse;
import vsu.labs.crypto.algs.common.ControlPanelBlock;

import java.util.Collections;

@Service
public class MorseService {

    public BlocksResponse getBlocks() {
        ControlPanelBlock textBlock = new ControlPanelBlock();
        textBlock.setId("text");
        textBlock.setName("Текст");

        return BlocksResponse.withEncrypt(Collections.singletonList(textBlock));
    }
}
