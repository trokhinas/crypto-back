package vsu.labs.crypto.service.users;

import vsu.labs.crypto.dto.users.GridColumn;
import vsu.labs.crypto.enums.GridColumnType;

public interface DefaultColumns {
    GridColumn userNameColumns = new GridColumn("userName", "Имя", GridColumnType.DEFAULT);
}
