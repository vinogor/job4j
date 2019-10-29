package strategy.places;

import strategy.Food;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MarketPlace {

    protected Map<String, Food> stock = new LinkedHashMap<>();
    private static long nowDays;

    public Food takeFoodByName(String name) {
        Food food = new Food(stock.get(name));
        stock.remove(name);
        return food;
    }

    public void put(Food food) {
        stock.put(food.getName(), food);
    }

    public List<Food> getAll() {
        return new ArrayList<>(stock.values());
    }

    public int getLeftProc(Food food) {
        long expireDays = food.getExpireDate().toEpochDay();
        long createDays = food.getCreateDate().toEpochDay();
        return (int) ((nowDays - createDays) * 100 / (expireDays - createDays));
    }

    public void setNowDays(long nowDays) {
        MarketPlace.nowDays = nowDays;
    }

    @Override
    public String toString() {
        return "    stock = " + stock;
    }
}
