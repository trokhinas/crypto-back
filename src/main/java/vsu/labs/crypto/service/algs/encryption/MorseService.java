package vsu.labs.crypto.service.algs.encryption;

import org.springframework.stereotype.Service;
import vsu.labs.crypto.algs.common.BlocksResponse;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.algs.morse.Morse;
import vsu.labs.crypto.dto.crypto.PartitionAlgData;
import vsu.labs.crypto.exceptions.LogicException;
import vsu.labs.crypto.service.algs.handlers.Handlers;

import java.util.Collections;
import java.util.List;

@Service
public class MorseService {

    private static final List<String> REQUIRED_BLOCKS = Collections.singletonList("text");

    public BlocksResponse getBlocks() {
        ControlPanelBlock textBlock = new ControlPanelBlock();
        textBlock.setId("text");
        textBlock.setName("Текст");

        return BlocksResponse.withEncode(Collections.singletonList(textBlock));
    }

    public String encode(List<ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        return Morse.code(text);
    }

    public String decode(List<ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        return Morse.decode(text);
    }

    public PartitionAlgData stagingEncode(List<ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        return Morse.stagingCode(text);
    }

    public PartitionAlgData stagingDecode(List<ControlPanelBlock> blocks) {
        checkBlocks(blocks);
        String text = getValueFromBlockWithId("text", blocks);
        return Morse.stagingDecode(text);
    }

    private void checkBlocks(List<ControlPanelBlock> blocks) {
        REQUIRED_BLOCKS.forEach(
                //TODO duplicate
                // вот этот кусок наверняка будет повторяться во всех сервисах,
                // его можно либо абстрагировать в утилиту, либо выделить отдельный сервис, проверяльщик
                blockId -> blocks.stream()
                        .filter(x -> x.getId().equals(blockId))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Отсутствует блок с id " + blockId))
        );
        blocks.forEach(Handlers.NOT_NULL_HANDLER::handle);
        blocks.forEach(Handlers.NOT_EMPTY_VALUE::handle);
    }

    private String getValueFromBlockWithId(String id, List<ControlPanelBlock> blocks) {
        //TODO duplicate
        // вот этот кусок наверняка будет повторяться во всех сервисах,
        // его можно либо абстрагировать в утилиту, либо выделить отдельный сервис, проверяльщик
        return blocks.stream()
                .filter(x -> x.getId().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalStateException("Отсутствует блок с id " + id))
                .getValue();
    }
}
