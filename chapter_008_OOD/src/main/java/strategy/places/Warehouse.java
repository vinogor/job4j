package strategy.places;

import strategy.Food;

public class Warehouse extends MarketPlace  {
    @Override
    public String toString() {
        return "Warehouse{ \n    " + super.toString() + " }";
    }

    @Override
    public boolean accept(Food food) {
        int leftProc = getLeftProc(food);
        return leftProc <= 25;
    }

    @Override
    public void add(Food food) {
        stock.put(food.getName(), food);
    }
}
