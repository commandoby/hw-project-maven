package com.commandoby.sonyShop.enums;

public enum RequestParamEnum {
    LOGIN("username"),
    PASSWORD("password"),
    COMMAND("command"),
    CATEGORIES("categories"),
    CATEGORY_NAME("category_name"),
    CATEGORY_TAG("category_tag"),
    PRODUCT_LIST("product_list"),
    PRODUCT_SIZE("product_size"),
    PRODUCT_ADD_NAME("product_name"),
    BASKET("basket"),
    BASKET_SIZE("basket_size"),
    BASKET_PRICE("basket_price"),
    REMOVE_PRODUCT_ID("remove_id"),
    SEARCH_VALUE("search_value");

    private final String value;

    RequestParamEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
