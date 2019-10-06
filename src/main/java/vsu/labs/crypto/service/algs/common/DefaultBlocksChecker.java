package vsu.labs.crypto.service.algs.common;

import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.service.algs.handlers.Handlers;

import java.util.List;
import java.util.Map;

public final class DefaultBlocksChecker {

    private DefaultBlocksChecker() { }

    public static void checkBlocks(Map<String, ControlPanelBlock> blockMap, List<String> requiredIds) {
        requiredIds.forEach(blockId -> {
            if (!blockMap.containsKey(blockId)) {
                throw new IllegalStateException("Отсутствует блок с id " + blockId);
            }
        });
        blockMap.values().forEach(Handlers.NOT_NULL_HANDLER::handle);
        blockMap.values().forEach(Handlers.NOT_EMPTY_VALUE::handle);
    }
}
