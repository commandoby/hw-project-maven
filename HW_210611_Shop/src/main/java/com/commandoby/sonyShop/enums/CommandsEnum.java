package com.commandoby.sonyShop.enums;

public enum CommandsEnum {
    HOME_PAGE_COMMAND("start_page"),
    SIGN_IN_COMMAND("sign-in");

    private final String command;

    CommandsEnum(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
