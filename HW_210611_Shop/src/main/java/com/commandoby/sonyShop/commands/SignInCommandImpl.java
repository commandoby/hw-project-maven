package com.commandoby.sonyShop.commands;

import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.enums.RequestParamEnum;
import com.commandoby.sonyShop.exceptions.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignInCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        HttpSession session = servletRequest.getSession();
        session.setAttribute(RequestParamEnum.LOGIN.getValue(), "");
        session.setAttribute(RequestParamEnum.PASSWORD.getValue(), "");
        return PagesPathEnum.SIGN_IN_PAGE.getPath();
    }
}
