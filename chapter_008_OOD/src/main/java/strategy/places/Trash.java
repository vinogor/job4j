package strategy.places;

import strategy.Food;
import strategy.Store;

import java.time.LocalDate;

public class Trash extends MarketPlace implements Store {
    @Override
    public String toString() {
        return "Trash{ \n    " + super.toString() + " }";
    }

    @Override
    public boolean accept(Food food) {
        long nowDays = LocalDate.of(2019, 10, 25).toEpochDay();
        long expireDays = food.getExpireDate().toEpochDay();
        long createDays = food.getCreateDate().toEpochDay();
        long leftProc = (nowDays - createDays) * 100 / (expireDays - createDays);

        return leftProc > 100;
    }

    @Override
    public void add(Food food) {
        stock.put(food.getName(), food);
    }
}