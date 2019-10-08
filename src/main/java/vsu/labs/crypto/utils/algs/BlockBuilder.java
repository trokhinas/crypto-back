package vsu.labs.crypto.utils.algs;

import org.springframework.util.StringUtils;
import vsu.labs.crypto.algs.common.ControlPanelBlock;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class BlockBuilder {
    private static final String EMPTY_VALUE = "";

    private BlockBuilder() { }

    public static MapBuilder buildMap() {
        return new MapBuilder();
    }


    public static class MapBuilder {
        private final Map<String, ControlPanelBlock> blockMap;

        private MapBuilder() {
            blockMap = new LinkedHashMap<>();
        }

        public MapBuilder withBlock(ControlPanelBlock controlPanelBlock) {
            String id = controlPanelBlock.getId();
            if (StringUtils.isEmpty(id)) {
                throw new IllegalArgumentException("У блока должен быть id!");
            }

            blockMap.put(id, controlPanelBlock);
            return this;
        }

        public MapBuilder withBlock(String id, String name) {
            var block = buildBlock(id, name);
            return withBlock(block);
        }

        public Map<String, ControlPanelBlock> build() {
            Map<String, ControlPanelBlock> result = new HashMap<>(blockMap);
            blockMap.clear();

            return result;
        }

        private ControlPanelBlock buildBlock(String id, String name) {
            return ControlPanelBlock.builder()
                    .id(id)
                    .name(name)
                    .value(EMPTY_VALUE)
                    .build();
        }
    }
}
