package com.commandoby.sonyShop.classies;

import java.util.ArrayList;
import java.util.List;

public class ShopContent {
    private static List<Category> categoriesList = new ArrayList<>();
    private static List<Product> productlist = new ArrayList<>();
    private static Category phone = new Category("Phone", "phone.jpeg");
    private static Category mp3Player = new Category("MP3 player", "player.jpeg");
    private static Category headphones = new Category("Headphones", "headphones.jpeg");
    private static Category TV = new Category("TV", "tv.jpeg");
    private static Category photo = new Category("Photo", "photo.jpeg");
    private static Category memoryCard = new Category("Memory card", "memcard.jpeg");

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

    public static List<Product> getProductlist() {
        return productlist;
    }
}
