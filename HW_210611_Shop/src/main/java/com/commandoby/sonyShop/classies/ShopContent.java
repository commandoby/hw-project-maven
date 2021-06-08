package com.commandoby.sonyShop.classies;

import java.util.ArrayList;
import java.util.List;

public class ShopContent {
    private static final List<Category> categoriesList = new ArrayList<>();
    private static final List<Product> productList = new ArrayList<>();
    private static final Category phone = new Category("Phone", "phone.jpeg", "phone");
    private static final Category mp3Player = new Category("MP3 player", "player.jpeg", "player");
    private static final Category headphones = new Category("Headphones", "headphones.jpeg", "headphones");
    private static final Category TV = new Category("TV", "tv.jpeg", "tv");
    private static final Category photo = new Category("Photo", "photo.jpeg", "photo");
    private static final Category memoryCard = new Category("Memory card", "memcard.jpeg", "memcard");

    static {
        categoriesList.add(phone);
        categoriesList.add(mp3Player);
        categoriesList.add(headphones);
        categoriesList.add(TV);
        categoriesList.add(photo);
        categoriesList.add(memoryCard);

        productList.add(new Product("Sony Xperia 10 II XQ-AU52 Dual", "10_II_XQ-AU52_Dual.jpeg",
                phone, "Android, экран 6\" OLED (1080x2520), Qualcomm Snapdragon 665, ОЗУ 4 ГБ, " +
                "флэш-память 128 ГБ, карты памяти, камера 12 Мп, аккумулятор 3600 мАч, 2 SIM",899));
        productList.add(new Product("Sony Xperia 1 II XQ-AT52", "1_II_XQ-AT52.jpeg",
                phone, "Android, экран 6.5\" OLED (1644x3840), Qualcomm Snapdragon 865, ОЗУ 12 ГБ, " +
                "флэш-память 256 ГБ, карты памяти, камера 12 Мп, аккумулятор 4000 мАч, 2 SIM", 2679));
        productList.add(new Product("Sony Xperia 5 II Dual", "5_II_Dual.jpeg",
                phone, "Android, экран 6.1\" OLED (1080x2520), Qualcomm Snapdragon 865, ОЗУ 8 ГБ, " +
                "флэш-память 128 ГБ, карты памяти, камера 12 Мп, аккумулятор 4000 мАч, 2 SIM", 2250));
        productList.add(new Product("Sony Xperia 1", "1.jpeg",
                phone, "Android, экран 6.5\" OLED (1644x3840), Qualcomm Snapdragon 855, ОЗУ 6 ГБ, " +
                "флэш-память 64 ГБ, карты памяти, камера 12 Мп, аккумулятор 3330 мАч, 1 SIM", 1450));
        productList.add(new Product("Sony Xperia 5 J9210", "5_J9210.jpeg",
                phone, "Android, экран 6.1\" OLED (1080x2520), Qualcomm Snapdragon 855, ОЗУ 6 ГБ, " +
                "флэш-память 128 ГБ, карты памяти, камера 12 Мп, аккумулятор 3140 мАч, 2 SIM", 1699));
        productList.add(new Product("Sony Xperia 10 I3113 Dual", "10_I3113_Dual.jpeg",
                phone, "Android, экран 6\" IPS (1080x2520), Qualcomm Snapdragon 630, ОЗУ 3 ГБ, " +
                "флэш-память 64 ГБ, карты памяти, камера 13 Мп, аккумулятор 2870 мАч, 2 SIM", 699));
        productList.add(new Product("Sony Xperia L3 I4312 Dual", "L3_I4312_Dual.jpeg",
                phone, "Android, экран 5.7\" IPS (720x1440), Mediatek MT6762 Helio P22, ОЗУ 3 ГБ, " +
                "флэш-память 32 ГБ, карты памяти, камера 13 Мп, аккумулятор 3300 мАч, 2 SIM", 499));
        productList.add(new Product("Sony Xperia 10 Plus I4293 Dual", "10_Plus_I4293_Dual.jpeg",
                phone, "Android, экран 6.5\" IPS (1080x2520), Qualcomm Snapdragon 636, ОЗУ 6 ГБ, " +
                "флэш-память 64 ГБ, карты памяти, камера 12 Мп, аккумулятор 3000 мАч, 2 SIM", 939));
        productList.add(new Product("Sony Xperia XZ2 Dual", "XZ2_Dual.jpeg",
                phone, "Android, экран 5.7\" IPS (1080x2160), Qualcomm Snapdragon 845, ОЗУ 4 ГБ, " +
                "флэш-память 64 ГБ, карты памяти, камера 19 Мп, аккумулятор 3180 мАч, 2 SIM", 1100));
        productList.add(new Product("Sony Xperia XA1 Plus Dual", "XA1_Plus_Dual.jpeg",
                phone, "Android, экран 5.5\" IPS (1080x1920), Mediatek MT6757 Helio P20, ОЗУ 4 ГБ, " +
                "флэш-память 32 ГБ, карты памяти, камера 23 Мп, аккумулятор 3430 мАч, 2 SIM", 535));
        productList.add(new Product("Sony Xperia L1 Dual", "L1_Dual.jpeg",
                phone, "Android, экран 5.5\" IPS (720x1280), Mediatek MT6737T, ОЗУ 2 ГБ, " +
                "флэш-память 16 ГБ, карты памяти, камера 13 Мп, аккумулятор 2620 мАч, 2 SIM", 294));
    }

    public static List<Category> getCategoriesList() {
        return categoriesList;
    }

    public static List<Product> getProductList() {
        return productList;
    }
}
