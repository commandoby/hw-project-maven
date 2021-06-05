package com.commandoby.sonyShop.commands;

import com.commandoby.sonyShop.enums.CommandsEnum;
import com.commandoby.sonyShop.enums.RequestParamEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, BaseCommand> COMMAND_LIST = new HashMap<>();

    static {
        COMMAND_LIST.put(CommandsEnum.SIGN_IN_COMMAND.getCommand(), new SignInCommandImpl());
        COMMAND_LIST.put(CommandsEnum.HOME_PAGE_COMMAND.getCommand(), new HomePageCommandImpl());
    }

    public static BaseCommand defineCommand(HttpServletRequest servletRequest) {
        String commandKey = servletRequest.getParameter(RequestParamEnum.COMMAND.toString());
        if (commandKey == null || commandKey.isEmpty()) {
            commandKey = CommandsEnum.SIGN_IN_COMMAND.getCommand();
        }

        return COMMAND_LIST.get(commandKey);
    }
}
