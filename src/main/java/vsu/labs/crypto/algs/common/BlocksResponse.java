package vsu.labs.crypto.algs.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BlocksResponse {
    private Map<String, ControlPanelBlock> blocks;
    private final Set<String> ids;
    private Boolean withKeysGeneration = false;
    private Boolean withStart = false;
    private Boolean withEncrypt = false;
    private Boolean withEncode = false;
    private Boolean withCheckSign = false;

    public static BlocksResponse withEncrypt(Map<String, ControlPanelBlock> blocks) {
        return BlocksResponse.builder().blocks(blocks).ids(blocks.keySet()).withEncrypt(true).build();
    }

    public static BlocksResponse withStart(Map<String, ControlPanelBlock> blocks) {
        return BlocksResponse.builder().blocks(blocks).ids(blocks.keySet()).withStart(true).build();
    }

    public static BlocksResponse withEncode(Map<String, ControlPanelBlock> blocks) {
        return BlocksResponse.builder().blocks(blocks).ids(blocks.keySet()).withEncode(true).build();
    }

    public static BlocksResponse withCheckSign(Map<String, ControlPanelBlock> blocks) {
        return BlocksResponse.builder().blocks(blocks).ids(blocks.keySet()).withKeysGeneration(true).withCheckSign(true).build();
    }
}
