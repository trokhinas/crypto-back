package vsu.labs.crypto.enums;

public enum GridColumnType {
    DEFAULT("default"),
    DELETE_EDIT("delete-edit");

    private final String type;

    GridColumnType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
