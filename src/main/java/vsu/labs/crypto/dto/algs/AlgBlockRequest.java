package vsu.labs.crypto.dto.algs;

import lombok.Data;
import vsu.labs.crypto.algs.common.ControlPanelBlock;

import java.util.List;

@Data
public class AlgBlockRequest {
    private List<ControlPanelBlock> blocks;
}
