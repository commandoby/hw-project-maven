package com.commandoby.sonyShop.commands;

import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.enums.RequestParamEnum;
import com.commandoby.sonyShop.exceptions.CommandException;

import javax.servlet.http.HttpServletRequest;

public class ProductListPageCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        String categoryName = servletRequest.getParameter(RequestParamEnum.CATEGORY.getValue());
        servletRequest.setAttribute(RequestParamEnum.CATEGORY.getValue(), categoryName);
        return PagesPathEnum.PRODUCTS_LIST_PAGE.getPath();
    }
}
