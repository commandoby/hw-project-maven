package com.commandoby.sonyShop.classies;

import java.util.Objects;

public class Product extends Category {
    private Category categories;
    private String description;
    private int price;

    public Product(String name, String imageName, Category categories, String description, int price) {
        super(name, imageName);
        this.categories = categories;
        this.description = description;
        this.price = price;
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
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return price == product.price && Objects.equals(categories, product.categories) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), categories, description, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name=" + getName() +
                ", categories=" + categories.getName() +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
