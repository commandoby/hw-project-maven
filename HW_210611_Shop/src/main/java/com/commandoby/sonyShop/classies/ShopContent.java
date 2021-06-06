package com.commandoby.sonyShop.classies;

import java.util.ArrayList;
import java.util.List;

public class ShopContent {
    private static final List<Category> categoriesList = new ArrayList<>();
    private static final List<Product> productList = new ArrayList<>();
    private static final Category phone = new Category("Phone", "phone.jpeg");
    private static final Category mp3Player = new Category("MP3 player", "player.jpeg");
    private static final Category headphones = new Category("Headphones", "headphones.jpeg");
    private static final Category TV = new Category("TV", "tv.jpeg");
    private static final Category photo = new Category("Photo", "photo.jpeg");
    private static final Category memoryCard = new Category("Memory card", "memcard.jpeg");

    static {
        categoriesList.add(phone);
        categoriesList.add(mp3Player);
        categoriesList.add(headphones);
        categoriesList.add(TV);
        categoriesList.add(photo);
        categoriesList.add(memoryCard);
    }

    public static List<Category> getCategoriesList() {
        return categoriesList;
    }

    public static List<Product> getProductList() {
        return productList;
    }
}
