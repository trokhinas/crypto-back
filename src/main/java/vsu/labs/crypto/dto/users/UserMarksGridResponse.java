package vsu.labs.crypto.dto.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserMarksGridResponse {
    private List<GridColumn> columns;
    private List<Map<String, Object>> data;
}
