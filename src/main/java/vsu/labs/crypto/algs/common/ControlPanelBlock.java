package vsu.labs.crypto.algs.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ControlPanelBlock {
    private String id;
    private String value;
    private String name;

    public ControlPanelBlock() { }
}
