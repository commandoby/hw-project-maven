package com.commandoby.sonyShop.commands;

import com.commandoby.sonyShop.classies.Product;
import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.enums.RequestParamEnum;
import com.commandoby.sonyShop.exceptions.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BasketPageCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        int basketPrice = 0;
        int basketSize = 0;
        int removeProduct;
        List<Product> basketList;

        if (servletRequest.getParameter(RequestParamEnum.REMOVE_PRODUCT_ID.getValue()) != null) {
            removeProduct = Integer.parseInt(servletRequest
                    .getParameter(RequestParamEnum.REMOVE_PRODUCT_ID.getValue()));
            basketList = getBasketList(servletRequest, removeProduct);
        } else {
            basketList = getBasketList(servletRequest);
        }

        if (basketList != null) {
            basketSize = basketList.size();
            basketPrice = sumPrice(basketList);
        }

        servletRequest.setAttribute(RequestParamEnum.BASKET_PRICE.getValue(), basketPrice);
        servletRequest.setAttribute(RequestParamEnum.BASKET_SIZE.getValue(), basketSize);
        servletRequest.setAttribute(RequestParamEnum.BASKET_LIST.getValue(), basketList);

        return PagesPathEnum.BASKET_PAGE.getPath();
    }

    private static List<Product> getBasketList(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        List<Product> productList = (List<Product>) session.getAttribute(RequestParamEnum.BASKET_LIST.getValue());
        return productList;
    }

    private List<Product> getBasketList(HttpServletRequest servletRequest, int id) {
        HttpSession session = servletRequest.getSession();
        List<Product> productList = (List<Product>) session.getAttribute(RequestParamEnum.BASKET_LIST.getValue());
        productList.remove(id);
        session.setAttribute(RequestParamEnum.BASKET_LIST.getValue(), productList);
        return productList;
    }

    public static int getBasketSize(HttpServletRequest servletRequest) {
        List<Product> basketList = getBasketList(servletRequest);
        if (basketList != null) return basketList.size();
        return 0;
    }

    private int sumPrice(List<Product> basketList) {
        int sumPrice = 0;
        for (Product product : basketList) {
            sumPrice += product.getPrice();
        }
        return sumPrice;
    }
}
