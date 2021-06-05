package com.commandoby.sonyShop.enums;

public enum PagesPathEnum {
    HOME_PAGE("category.jsp"),
    SIGN_IN_PAGE("login.jsp");

    private final String path;

    PagesPathEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
