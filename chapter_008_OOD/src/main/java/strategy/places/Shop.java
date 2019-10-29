package strategy.places;

import strategy.Food;
import strategy.Store;

public class Shop extends MarketPlace implements Store {
    @Override
    public String toString() {
        return "Shop{ \n    " + super.toString() + " }";
    }

    @Override
    public boolean accept(Food food) {
        int leftProc = getLeftProc(food);
        return leftProc > 25 && leftProc <= 75;
    }

    @Override
    public void add(Food food) {
        stock.put(food.getName(), food);
    }

    @Override
    public void clean() {
        stock.clear();
    }
}