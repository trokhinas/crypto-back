package vsu.labs.crypto.algs.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BlocksResponse {
    private Map<String, ControlPanelBlock> blocks;
    private final Set<String> ids;
    private Boolean withKeysGeneration = false;
    private Boolean withStart = false;
    private Boolean withEncrypt = false;
    private Boolean withEncode = false;

    private BlocksResponse(Map<String, ControlPanelBlock> blocks,
                           Boolean withKeysGeneration,
                           Boolean withStart,
                           Boolean withEncrypt,
                           Boolean withEncode) {

        this(blocks, blocks.keySet(), withKeysGeneration, withStart, withEncrypt, withEncode);
    }

    public static BlocksResponse withEncrypt(Map<String, ControlPanelBlock> blocks) {
        return new BlocksResponse(blocks, false, false, true, false);
    }

    public static BlocksResponse withStart(Map<String, ControlPanelBlock> blocks) {
        return new BlocksResponse(blocks,
                false, true, false, false);
    }

    public static BlocksResponse withEncryptAndKeys(Map<String, ControlPanelBlock> blocks) {
        return new BlocksResponse(blocks, true, false, true, false);
    }

    public static BlocksResponse withEncode(Map<String, ControlPanelBlock> blocks) {
        return new BlocksResponse(blocks, false, false, false, true);
    }
}
