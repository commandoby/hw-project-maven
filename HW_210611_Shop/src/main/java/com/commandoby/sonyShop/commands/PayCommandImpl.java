package com.commandoby.sonyShop.commands;

import com.commandoby.sonyShop.classies.Product;
import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.enums.RequestParamEnum;
import com.commandoby.sonyShop.exceptions.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PayCommandImpl implements BaseCommand {
    private Logger log = Logger.getLogger(getClass().getName());

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        int paySize = 0;
        int payPrice = 0;
        List<Product> basketList = getBasketList(servletRequest);

        if (basketList != null && !basketList.isEmpty()) {
            paySize = basketList.size();
            payPrice = sumPrice(basketList);
        }

        servletRequest.setAttribute(RequestParamEnum.BASKET_SIZE.getValue(), paySize);
        servletRequest.setAttribute(RequestParamEnum.BASKET_PRICE.getValue(), payPrice);

        log.info("Purchased " + paySize + " products.");

        return PagesPathEnum.PAY_PAGE.getPath();
    }

    private List<Product> getBasketList(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        List<Product> productList = (List<Product>) session.getAttribute(RequestParamEnum.BASKET_LIST.getValue());
        session.setAttribute(RequestParamEnum.BASKET_LIST.getValue(), null);
        return productList;
    }

    private int sumPrice(List<Product> basketList) {
        int sumPrice = 0;
        for (Product product : basketList) {
            sumPrice += product.getPrice();
        }
        return sumPrice;
    }
}
