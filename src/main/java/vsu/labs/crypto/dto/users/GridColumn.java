package vsu.labs.crypto.dto.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vsu.labs.crypto.enums.GridColumnType;

@Data
@AllArgsConstructor @NoArgsConstructor
public class GridColumn {
    private String key;
    private String header;
    @JsonProperty("type")
    private GridColumnType columnType = GridColumnType.DEFAULT;
}
