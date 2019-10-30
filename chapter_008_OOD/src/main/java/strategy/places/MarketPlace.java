package strategy.places;

import strategy.Food;
import strategy.Store;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class MarketPlace implements Store {

    protected Map<String, Food> stock = new LinkedHashMap<>();
    private static long nowDays;

    public Food takeFoodByName(String name) {
        Food food = new Food(stock.get(name));
        stock.remove(name);
        return food;
    }
    
    public int getLeftProc(Food food) {
        long expireDays = food.getExpireDate().toEpochDay();
        long createDays = food.getCreateDate().toEpochDay();
        return (int) ((nowDays - createDays) * 100 / (expireDays - createDays));
    }

    public static void setNowDays(long nowDays) {
        MarketPlace.nowDays = nowDays;
    }
    
    @Override
    public String toString() {
        return "stock = " + stock + " }";
    }
}
