package com.commandoby.sonyShop.commands;

import com.commandoby.sonyShop.classies.Basket;
import com.commandoby.sonyShop.classies.Category;
import com.commandoby.sonyShop.classies.Product;
import com.commandoby.sonyShop.classies.ShopContent;
import com.commandoby.sonyShop.exceptions.CommandException;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.commandoby.sonyShop.enums.PagesPathEnum.PRODUCTS_LIST_PAGE;
import static com.commandoby.sonyShop.enums.RequestParamEnum.*;

public class ProductListPageCommandImpl implements BaseCommand {
    private Logger log = Logger.getLogger(getClass().getName());

    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        String categoryTag = servletRequest.getParameter(CATEGORY_TAG.getValue());

        try {
            servletRequest.setAttribute(CATEGORY_TAG.getValue(), categoryTag);
            servletRequest.setAttribute(CATEGORY_NAME.getValue(), searchCategoryName(categoryTag));

            int productSize = getProductList(servletRequest, categoryTag);
            servletRequest.setAttribute(PRODUCT_SIZE.getValue(), productSize);

            String productAddName = servletRequest.getParameter(PRODUCT_ADD_NAME.getValue());
            if (productAddName != null) {
                addProductToBasket(servletRequest, productAddName);
            }
        } catch (NoFoundException e) {
            log.error(e);
        }

        int basketSize = BasketPageCommandImpl.getBasketSize(servletRequest);
        servletRequest.setAttribute(BASKET_SIZE.getValue(), basketSize);

        return PRODUCTS_LIST_PAGE.getPath();
    }

    private String searchCategoryName(String tag) throws NoFoundException {
        for (Category category : ShopContent.getCategoriesList()) {
            if (category.getTag().equals(tag)) return category.getName();
        }
        throw new NoFoundException("Category: " + tag + " not found.");
    }

    private int getProductList(HttpServletRequest servletRequest, String tag) {
        List<Product> productList = new ArrayList<>();
        for (Product product : ShopContent.getProductList()) {
            if (product.getCategories().getTag().equals(tag)) productList.add(product);
        }

        servletRequest.setAttribute(PRODUCT_LIST.getValue(), productList);

        return productList.size();
    }

    private void addProductToBasket(HttpServletRequest servletRequest, String productName) throws NoFoundException {
        HttpSession session = servletRequest.getSession();
        Basket basket = (Basket) session.getAttribute(BASKET.getValue());
        Product product = getProduct(productName);
        if (basket == null) basket = new Basket();
        basket.addProduct(product);
        session.setAttribute(BASKET.getValue(), basket);
    }

    private Product getProduct(String productName) throws NoFoundException {
        for (Product product : ShopContent.getProductList()) {
            if (product.getName().equals(productName)) return product;
        }
        throw new NoFoundException("Product: " + productName + " not found.");
    }
}
