package strategy.places;

import strategy.Food;

public class Shop extends MarketPlace  {
    
    private boolean makeDiscount = false;
    
    @Override
    public String toString() {
        return "Shop{ \n    " + super.toString() + " }";
    }

    @Override
    public boolean accept(Food food) {
        int leftProc = getLeftProc(food);
        makeDiscount = leftProc > 75 && leftProc <= 100;
        return (leftProc > 25 && leftProc <= 75) || makeDiscount;
    }

    @Override
    public void add(Food food) {
        if (makeDiscount) {
            food.setDiscount(50);
        }
        stock.put(food.getName(), food);
    }
}