package strategy.strategies;

import strategy.Food;
import strategy.Strategy;
import strategy.places.MarketPlace;

public class MoveToShopWithDiscount implements Strategy {

    private MarketPlace place;

    public MoveToShopWithDiscount(MarketPlace place) {
        this.place = place;
    }

    @Override
    public void replaceProduct(Food food) {
        food.setDiscount(50);
        place.put(food);
    }
}
