package ru.job4j.design.lsp.foodstore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Food {
    private String name;
    private double price;
    private int discount;
    private Calendar createDate;
    private Calendar expireDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public Calendar getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Calendar expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.getPrice(), getPrice()) == 0 && Objects.equals(getName(),
                food.getName()) && Objects.equals(getCreateDate(),
                food.getCreateDate()) && Objects.equals(getExpireDate(), food.getExpireDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getCreateDate(), getExpireDate());
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", price=" + price
                + ", discount=" + discount
                + ", createDate=" + new SimpleDateFormat("dd.MM.yyyy")
                                    .format(createDate.getTime())
                + ", expireDate=" + new SimpleDateFormat("dd.MM.yyyy")
                .format(expireDate.getTime())
                + '}';
    }
}
