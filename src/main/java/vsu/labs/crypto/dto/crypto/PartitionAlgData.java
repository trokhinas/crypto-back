package vsu.labs.crypto.dto.crypto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data @AllArgsConstructor
public class PartitionAlgData {
    private List<StageData> stageData;
    private Object result;
}
