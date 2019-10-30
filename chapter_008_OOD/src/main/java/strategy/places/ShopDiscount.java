package strategy.places;

import strategy.Food;
import strategy.Store;

import java.time.LocalDate;

public class ShopDiscount extends Shop implements Store {

    @Override
    public boolean accept(Food food) {
        int leftProc = getLeftProc(food);
        return leftProc > 75 && leftProc <= 100;
    }

    @Override
    public void add(Food food) {
        food.setDiscount(50);
        super.add(food);
    }
}
