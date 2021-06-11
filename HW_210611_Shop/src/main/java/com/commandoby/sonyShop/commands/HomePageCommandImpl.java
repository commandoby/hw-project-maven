package com.commandoby.sonyShop.commands;

import com.commandoby.sonyShop.classies.Category;
import com.commandoby.sonyShop.classies.ShopContent;
import com.commandoby.sonyShop.classies.User;
import com.commandoby.sonyShop.exceptions.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.commandoby.sonyShop.enums.PagesPathEnum.HOME_PAGE;
import static com.commandoby.sonyShop.enums.PagesPathEnum.SIGN_IN_PAGE;
import static com.commandoby.sonyShop.enums.RequestParamEnum.*;

public class HomePageCommandImpl implements BaseCommand {

    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        HttpSession session = servletRequest.getSession();

        String login = (String) session.getAttribute(LOGIN.getValue());
        String password = (String) session.getAttribute(PASSWORD.getValue());

        User user = new User(login, password);
        if (validateParamNotNull(login) && validateParamNotNull(password) && checkReceivedUser(user)) {
            return categoryQuery(servletRequest);
        }

        login = servletRequest.getParameter(LOGIN.getValue());
        password = servletRequest.getParameter(PASSWORD.getValue());

        user = new User(login, password);
        if (validateParamNotNull(login) && validateParamNotNull(password) && checkReceivedUser(user)) {
            session.setAttribute(LOGIN.getValue(), login);
            session.setAttribute(PASSWORD.getValue(), password);

            return categoryQuery(servletRequest);
        } else {
            return SIGN_IN_PAGE.getPath();
        }
    }

    private String categoryQuery(HttpServletRequest servletRequest) {
        List<Category> categoryList = ShopContent.getCategoriesList();
        servletRequest.setAttribute(CATEGORIES.getValue(), categoryList);

        int basketSize = BasketPageCommandImpl.getBasketSize(servletRequest);
        servletRequest.setAttribute(BASKET_SIZE.getValue(), basketSize);

        return HOME_PAGE.getPath();
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
