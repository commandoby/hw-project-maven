package com.commandoby.sonyShop.commands;

import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.exceptions.CommandException;

import javax.servlet.http.HttpServletRequest;

public class HomePageCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        return PagesPathEnum.HOME_PAGE.getPath();
    }
}
