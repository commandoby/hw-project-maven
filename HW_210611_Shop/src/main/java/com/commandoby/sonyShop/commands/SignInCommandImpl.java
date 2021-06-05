package com.commandoby.sonyShop.commands;

import com.commandoby.sonyShop.classies.Category;
import com.commandoby.sonyShop.classies.ShopContent;
import com.commandoby.sonyShop.classies.User;
import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.enums.RequestParamEnum;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.RequestParamNullException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SignInCommandImpl implements BaseCommand {

    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException, RequestParamNullException {
        String login = servletRequest.getParameter(RequestParamEnum.LOGIN.getValue());
        String password = servletRequest.getParameter(RequestParamEnum.PASSWORD.getValue());

        HttpRequestParamValidator.validateParamNotNull(login);
        HttpRequestParamValidator.validateParamNotNull(password);

        User user = getUser();
        return checkReceivedUser(user, servletRequest);
    }

    private String checkReceivedUser(User user, HttpServletRequest servletRequest) {
        if (user != null
                && servletRequest.getParameter(RequestParamEnum.LOGIN.getValue()).equals(ADMIN_LOGIN)
                && servletRequest.getParameter(RequestParamEnum.PASSWORD.getValue()).equals(ADMIN_PASSWORD)) {

            List<Category> categoryList = ShopContent.getCategoriesList();
            servletRequest.setAttribute("categories", categoryList);

            return PagesPathEnum.HOME_PAGE.getPath();
        }
        return PagesPathEnum.SIGN_IN_PAGE.getPath();
    }

    private User getUser() {
        return new User(ADMIN_LOGIN, ADMIN_PASSWORD);
    }
}
