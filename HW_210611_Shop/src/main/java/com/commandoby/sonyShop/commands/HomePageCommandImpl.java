package com.commandoby.sonyShop.commands;

import com.commandoby.sonyShop.classies.Category;
import com.commandoby.sonyShop.classies.ShopContent;
import com.commandoby.sonyShop.classies.User;
import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.enums.RequestParamEnum;
import com.commandoby.sonyShop.exceptions.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class HomePageCommandImpl implements BaseCommand {

    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        HttpSession session = servletRequest.getSession();

        String login = (String) session.getAttribute(RequestParamEnum.LOGIN.getValue());
        String password = (String) session.getAttribute(RequestParamEnum.PASSWORD.getValue());

        User user = new User(login, password);
        if (validateParamNotNull(login) && validateParamNotNull(password) && checkReceivedUser(user)) {
            return categoryQuery(servletRequest);
        }

        login = servletRequest.getParameter(RequestParamEnum.LOGIN.getValue());
        password = servletRequest.getParameter(RequestParamEnum.PASSWORD.getValue());

        user = new User(login, password);
        if (validateParamNotNull(login) && validateParamNotNull(password) && checkReceivedUser(user)) {
            session.setAttribute(RequestParamEnum.LOGIN.getValue(), login);
            session.setAttribute(RequestParamEnum.PASSWORD.getValue(), password);

            return categoryQuery(servletRequest);
        } else {
            return PagesPathEnum.SIGN_IN_PAGE.getPath();
        }
    }

    private String categoryQuery(HttpServletRequest servletRequest) {
        List<Category> categoryList = ShopContent.getCategoriesList();
        servletRequest.setAttribute(RequestParamEnum.CATEGORIES.getValue(), categoryList);

        int basketSize = BasketPageCommandImpl.getBasketSize(servletRequest);
        servletRequest.setAttribute(RequestParamEnum.BASKET_SIZE.getValue(), basketSize);

        return PagesPathEnum.HOME_PAGE.getPath();
    }

    private boolean checkReceivedUser(User user) {
        if (user != null && user.getName().equals(ADMIN_LOGIN)
                && user.getPassword().equals(ADMIN_PASSWORD)) {
            return true;
        }
        return false;
    }

    private boolean validateParamNotNull(String parameter) {
        if (parameter != null && parameter != "") return true;
        return false;
    }
}
