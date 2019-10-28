package strategy.places;

import strategy.Food;
import strategy.Store;

import java.time.LocalDate;

public class ShopDiscount extends Shop implements Store {

    @Override
    public boolean accept(Food food) {

        long nowDays = LocalDate.of(2019, 10, 25).toEpochDay();
        long expireDays = food.getExpireDate().toEpochDay();
        long createDays = food.getCreateDate().toEpochDay();
        long leftProc = (nowDays - createDays) * 100 / (expireDays - createDays);

        return leftProc > 75 && leftProc <= 100;
    }

    @Override
    public void add(Food food) {
        food.setDiscount(50);
        super.add(food);
    }
}
