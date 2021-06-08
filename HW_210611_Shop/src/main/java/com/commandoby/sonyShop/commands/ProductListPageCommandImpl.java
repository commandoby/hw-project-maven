package com.commandoby.sonyShop.commands;

import com.commandoby.sonyShop.classies.Category;
import com.commandoby.sonyShop.classies.Product;
import com.commandoby.sonyShop.classies.ShopContent;
import com.commandoby.sonyShop.enums.PagesPathEnum;
import com.commandoby.sonyShop.enums.RequestParamEnum;
import com.commandoby.sonyShop.exceptions.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ProductListPageCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest servletRequest) throws CommandException {
        String categoryTag = servletRequest.getParameter(RequestParamEnum.CATEGORY_TAG.getValue());
        servletRequest.setAttribute(RequestParamEnum.CATEGORY_TAG.getValue(), categoryTag);
        servletRequest.setAttribute(RequestParamEnum.CATEGORY_NAME.getValue(), searchCategoryName(categoryTag));

        int productSize = getProductList(servletRequest, categoryTag);
        servletRequest.setAttribute(RequestParamEnum.PRODUCT_SIZE.getValue(), productSize);

        String productAddName = servletRequest.getParameter(RequestParamEnum.PRODUCT_ADD_NAME.getValue());
        if (productAddName != null) {
            addProductToBasket(servletRequest, productAddName);
        }

        int basketSize = BasketPageCommandImpl.getBasketSize(servletRequest);
        servletRequest.setAttribute(RequestParamEnum.BASKET_SIZE.getValue(), basketSize);

        return PagesPathEnum.PRODUCTS_LIST_PAGE.getPath();
    }

    private String searchCategoryName(String tag) {
        for (Category category : ShopContent.getCategoriesList()) {
            if (category.getTag().equals(tag)) return category.getName();
        }
        return "No category";
    }

    private int getProductList(HttpServletRequest servletRequest, String tag) {
        List<Product> productList = new ArrayList<>();
        for (Product product : ShopContent.getProductList()) {
            if (product.getCategories().getTag().equals(tag)) productList.add(product);
        }

        servletRequest.setAttribute(RequestParamEnum.PRODUCT_LIST.getValue(), productList);

        return productList.size();
    }

    private void addProductToBasket(HttpServletRequest servletRequest, String productName) {
        HttpSession session = servletRequest.getSession();
        List<Product> productList = (List<Product>) session.getAttribute(RequestParamEnum.BASKET_LIST.getValue());
        Product product = getProduct(productName);
        if (productList == null) {
            productList = new ArrayList<>();
        }
        productList.add(product);
        session.setAttribute(RequestParamEnum.BASKET_LIST.getValue(), productList);
    }

    private Product getProduct(String productName) {
        for (Product product : ShopContent.getProductList()) {
            if (product.getName().equals(productName)) return product;
        }
        return null;
    }
}
