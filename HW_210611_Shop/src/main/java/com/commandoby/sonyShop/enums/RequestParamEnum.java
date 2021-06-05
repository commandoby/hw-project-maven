package com.commandoby.sonyShop.enums;

public enum RequestParamEnum {
    LOGIN("username"),
    PASSWORD("password"),
    COMMAND("command");

    private final String value;

    RequestParamEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
