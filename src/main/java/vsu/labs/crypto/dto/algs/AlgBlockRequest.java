package vsu.labs.crypto.dto.algs;

import lombok.Data;
import vsu.labs.crypto.algs.common.ControlPanelBlock;

import java.util.Map;

@Data
public class AlgBlockRequest {
    private Map<String, ControlPanelBlock> blocks;
}
