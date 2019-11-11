package vsu.labs.crypto.service.users;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.dto.users.GridColumn;
import vsu.labs.crypto.dto.users.GridResponse;
import vsu.labs.crypto.entity.JpaRepository.UserRepository;
import vsu.labs.crypto.entity.security.UserEntity;
import vsu.labs.crypto.enums.GridColumnType;
import vsu.labs.crypto.enums.RoleType;
import vsu.labs.crypto.service.users.util.user.UserDataExtractors;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class UsersEditGridService {

    private final UserRepository userRepository;

    public GridResponse getGridData() {
        List<GridColumn> columns = buildColumns();
        List<UserEntity> students = userRepository
                .findByRoleIdNotOrderByRoleId(RoleType.ADMIN.getId());

        List<Map<String, Object>> data = new ArrayList<>();
        students.stream().map(this::buildUserRow).forEach(data::add);

        return new GridResponse(columns, data);
    }

    private Map<String, Object> buildUserRow(UserEntity userEntity) {
        Map<String, Object> row = new HashMap<>();
        UserDataExtractors.ALL_EXTRACTORS.forEach(extractor -> {
            String key = extractor.getKey();
            Object value = extractor.getExtractor().apply(userEntity);

            row.put(key, value);
        });
        row.put("roleId", userEntity.getRoleId());

        return row;
    }

    private List<GridColumn> buildColumns() {
        List<GridColumn> columns = new ArrayList<>();

        GridColumn deleteEdit = new GridColumn(
                GridColumnType.DELETE_EDIT.getType(),
                "",
                GridColumnType.DELETE_EDIT
        );
        columns.add(deleteEdit);

        UserDataExtractors.ALL_EXTRACTORS.forEach(extractor -> {
            GridColumn column = new GridColumn();
            String key = extractor.getKey();

            column.setKey(key);
            column.setHeader(UserDataExtractors.getColumnName(key));
            columns.add(column);
        });
        return columns;
    }

}
