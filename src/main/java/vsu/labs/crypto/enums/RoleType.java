package vsu.labs.crypto.enums;

import java.util.Arrays;

public enum RoleType implements IEnum {
    STUDENT(1000L),
    TEACHER(2000L),
    ADMIN(3000L);

    private final Long id;

    RoleType(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public static RoleType byId(Long id) {
        return Arrays.stream(RoleType.values())
                .filter(role -> role.id.equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не существует роли с id = " + id));
    }
}
