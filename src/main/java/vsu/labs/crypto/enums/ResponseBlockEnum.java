package vsu.labs.crypto.enums;

public enum ResponseBlockEnum {
    WithKeysGeneration("withKeysGeneration"),
    WithStart("withStart"),
    WithEncrypt("withEncrypt"),
    WithEncode("withEncode"),
    WithCheckSign("withCheckSign"),
    WITH_FILE_COMPRESSION("withFileCompression"),
    WITH_STRING_COMPRESSION("withStringCompression")
    ;

    private final String value;

    ResponseBlockEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
