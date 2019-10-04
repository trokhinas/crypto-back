package vsu.labs.crypto.algs.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BlocksResponse {
    private List<ControlPanelBlock> blocks;
    private Boolean withKeysGeneration = false;
    private Boolean withStart = false;
    private Boolean withEncrypt = false;
    private Boolean withEncode = false;

    public static BlocksResponse withEncrypt(List<ControlPanelBlock> blocks) {
        return new BlocksResponse(blocks, false, false, true, false);
    }

    public static BlocksResponse withStart(List<ControlPanelBlock> blocks) {
        return new BlocksResponse(blocks,
                false, true, false, false);
    }

    public static BlocksResponse withEncryptAndKeys(List<ControlPanelBlock> blocks) {
        return new BlocksResponse(blocks, true, false, true, false);
    }

    public static BlocksResponse withEncode(List<ControlPanelBlock> blocks) {
        return new BlocksResponse(blocks, false, false, false, true);
    }
}
