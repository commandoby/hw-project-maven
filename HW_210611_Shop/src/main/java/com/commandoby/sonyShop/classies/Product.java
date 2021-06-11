package com.commandoby.sonyShop.classies;

import java.util.Objects;

public class Product {
    private String name;
    private String imageName;
    private Category categories;
    private String description;
    private int price;

    public Product(String name, String imageName, Category categories, String description, int price) {
        this.name = name;
        this.imageName = imageName;
        this.categories = categories;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Category getCategories() {
        return categories;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && Objects.equals(name, product.name) && Objects.equals(imageName, product.imageName) && Objects.equals(categories, product.categories) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imageName, categories, description, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", imageName='" + imageName + '\'' +
                ", categories=" + categories +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
