package strategy.strategies;

import strategy.Food;
import strategy.Strategy;
import strategy.places.MarketPlace;

public class MoveToShop implements Strategy {

    private MarketPlace place;

    public MoveToShop(MarketPlace place) {
        this.place = place;
    }

    @Override
    public void replaceProduct(Food food) {
        place.put(food);

    }
}
