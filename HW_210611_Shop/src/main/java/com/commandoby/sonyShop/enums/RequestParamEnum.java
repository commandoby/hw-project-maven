package com.commandoby.sonyShop.enums;

public enum RequestParamEnum {
    LOGIN("username"),
    PASSWORD("password"),
    COMMAND("command"),
    CATEGORY("category_name");

    private final String value;

    RequestParamEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
