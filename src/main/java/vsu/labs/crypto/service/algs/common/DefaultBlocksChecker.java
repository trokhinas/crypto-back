package vsu.labs.crypto.service.algs.common;

import vsu.labs.crypto.algs.common.ControlPanelBlock;

import java.util.List;
import java.util.Map;

import static vsu.labs.crypto.service.algs.handlers.Handlers.DEFAULT_HANDLERS;

public final class DefaultBlocksChecker {

    private DefaultBlocksChecker() { }

    public static void checkBlocks(Map<String, ControlPanelBlock> blockMap, List<String> requiredIds) {
        requiredIds.forEach(blockId -> {
            if (!blockMap.containsKey(blockId)) {
                throw new IllegalStateException("Отсутствует блок с id " + blockId);
            }
        });
        for (var handler: DEFAULT_HANDLERS) {
            blockMap.values().forEach(handler::handle);
        }
    }
}
