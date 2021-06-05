package com.commandoby.sonyShop.App;

import com.commandoby.sonyShop.classies.Category;
import com.commandoby.sonyShop.classies.Product;
import com.commandoby.sonyShop.classies.ShopContent;

public class ApplicationTest {
    public static void main(String[] args) {
        Category cat1 = new Category("phone", "image");
        Product prod1 = new Product("k750", "image", cat1, "blabla", 100);
        System.out.println(prod1);
        System.out.println(ShopContent.getCategoriesList().get(0));
    }
}
