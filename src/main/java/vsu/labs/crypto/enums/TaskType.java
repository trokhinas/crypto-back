package vsu.labs.crypto.enums;

import java.util.Arrays;

public enum TaskType implements IEnum{
    SELECT(500L),
    MULTISELECT(600L),
    MANUAL(700L);

    private final Long id;

    TaskType(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public static TaskType byId(Long id) {
        return Arrays.stream(TaskType.values())
                .filter(taskType -> taskType.id.equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не существует типа задачи с id = " + id));
    }
}
