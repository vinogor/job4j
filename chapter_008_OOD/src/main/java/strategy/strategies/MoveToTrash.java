package strategy.strategies;

import strategy.Food;
import strategy.Strategy;
import strategy.places.MarketPlace;

public class MoveToTrash implements Strategy {

    private MarketPlace place;

    public MoveToTrash(MarketPlace place) {
        this.place = place;
    }

    @Override
    public void replaceProduct(Food food) {
        place.put(food);
    }
}
