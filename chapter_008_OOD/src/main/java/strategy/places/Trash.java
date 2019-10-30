package strategy.places;

import strategy.Food;

public class Trash extends MarketPlace  {
    @Override
    public String toString() {
        return "Trash{ \n    " + super.toString() + " }";
    }

    @Override
    public boolean accept(Food food) {
        int leftProc = getLeftProc(food);
        return leftProc > 100;
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