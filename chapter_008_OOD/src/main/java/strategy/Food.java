package strategy;

import java.time.LocalDate;
import java.util.Objects;

public class Food {

    private String name;
    private LocalDate createDate;
    private LocalDate expireDate;
    private int price;
    private int discount;

    public Food(String name, LocalDate createDate, LocalDate expireDate, int price, int discount) {
        this.name = name;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.price = price;
        this.discount = discount;
    }

    public Food(Food food) {
        this(food.getName(), food.getCreateDate(), food.getExpireDate(), food.getPrice(), food.getDiscount());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return price == food.price &&
            discount == food.discount &&
            Objects.equals(name, food.name) &&
            Objects.equals(createDate, food.createDate) &&
            Objects.equals(expireDate, food.expireDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, createDate, expireDate, price, discount);
    }

    @Override
    public String toString() {
        return "Food{" +
            "name='" + name + '\'' +
            ", createDate=" + createDate +
            ", expireDate=" + expireDate +
            ", price=" + price +
            ", discount=" + discount +
            '}';
    }
}
