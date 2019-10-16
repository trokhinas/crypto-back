package vsu.labs.crypto.service.algs.sign;

import org.springframework.stereotype.Service;
import vsu.labs.crypto.algs.common.BlocksResponse;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.utils.algs.BlockBuilder;

import java.util.Map;

@Service
public class RsaService {

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
}
