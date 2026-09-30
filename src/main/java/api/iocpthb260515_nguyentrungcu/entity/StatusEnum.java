package api.iocpthb260515_nguyentrungcu.entity;

import java.util.Arrays;

public enum StatusEnum {
    CANCELED(0),
    PROCESSING(1),
    COMPLETED(2);

    private final int code;

    StatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    public static StatusEnum fromValue(int value) {
            return Arrays.stream(values()).filter(e -> e.code == value).findFirst().orElseThrow();
    }
}
