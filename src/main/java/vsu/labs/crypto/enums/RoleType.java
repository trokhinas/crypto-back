package vsu.labs.crypto.enums;

import java.util.Arrays;

public enum RoleType implements IEnum {
    STUDENT(1000L, "Студент"),
    TEACHER(2000L, "Преподаватель"),
    ADMIN(3000L, "Администратор");

    private final Long id;
    private final String name;

    RoleType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static RoleType byId(Long id) {
        return Arrays.stream(RoleType.values())
                .filter(role -> role.id.equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не существует роли с id = " + id));
    }
}
