package vsu.labs.crypto.algs.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static vsu.labs.crypto.enums.ResponseBlockEnum.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BlocksResponse {
    private Map<String, ControlPanelBlock> blocks;
    private final Set<String> ids;
    @JsonProperty("buttonsMap")
    private Map<String, Boolean> map;

    public BlocksResponse withProperty(String name) {
        map.put(name, true);
        return this;
    }

    public static BlocksResponse withEncrypt(Map<String, ControlPanelBlock> blocks) {
        return BlocksResponse.builder().blocks(blocks).ids(blocks.keySet()).map(new HashMap<>() {{
            put(WithEncrypt.getValue(), true);
        }}).build();
    }

    public static BlocksResponse withStart(Map<String, ControlPanelBlock> blocks) {
        return BlocksResponse.builder().blocks(blocks).ids(blocks.keySet()).map(new HashMap<>() {{
            put(WithStart.getValue(), true);
        }}).build();
    }

    public static BlocksResponse withEncode(Map<String, ControlPanelBlock> blocks) {
        return BlocksResponse.builder().blocks(blocks).ids(blocks.keySet()).map(new HashMap<>() {{
            put(WithEncode.getValue(), true);
        }}).build();
    }

    public static BlocksResponse withCheckSign(Map<String, ControlPanelBlock> blocks) {
        return BlocksResponse.builder().blocks(blocks).ids(blocks.keySet()).map(new HashMap<>() {{
            put(WithKeysGeneration.getValue(), true);
            put(WithCheckSign.getValue(), true);
        }}).build();
    }

    public static BlocksResponse withFileCompression(Map<String, ControlPanelBlock> blocks) {
        return BlocksResponse.builder()
                .blocks(blocks)
                .ids(blocks.keySet())
                .map(new HashMap<>() {{
                    put(WITH_FILE_COMPRESSION.getValue(), true);
                }}).build();
    }
}
