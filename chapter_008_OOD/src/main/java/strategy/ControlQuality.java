package strategy;

import strategy.places.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    
    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    private void distribute(List<Food> foods) {
        for (Food food : foods) {
            addFood(food);
        }
    }

    private void printStores() {
        for (Store store : stores) {
            System.out.println(store);
        }
    }

    private void resort() {
        distribute(takeAllFoods());
    }

    private List<Food> takeAllFoods() {
        List<Food> foods = new ArrayList<>();
        for (Store store : stores) {
            foods.addAll(store.takeAllStock());
        }
        return foods;
    }

    private void addFood(Food food) {
        for (Store store : stores) {
            if (store.accept(food)) {
                store.add(food);
                break;
            }
        }
    }

    public static void main(String[] args) {
        
        List<Store> stores = new ArrayList<>();
        stores.add(new Warehouse());
        stores.add(new Shop());
        stores.add(new Trash());
        
        List<Food> foods = new ArrayList<>();
        foods.add(new Food(
                "бананы",
                LocalDate.of(2019, 9, 20),
                LocalDate.of(2019, 10, 27),
                15,
                0
            )
        );
        foods.add(new Food(
                "молоко",
                LocalDate.of(2019, 10, 20),
                LocalDate.of(2019, 10, 24),
                22,
                0
            )
        );
        foods.add(new Food(
                "рис",
                LocalDate.of(2019, 5, 3),
                LocalDate.of(2021, 5, 3),
                36,
                0
            )
        );
        foods.add(new Food(
                "говядина",
                LocalDate.of(2019, 10, 18),
                LocalDate.of(2019, 10, 28),
                71,
                0
            )
        );

        ControlQuality control = new ControlQuality(stores);

        MarketPlace.setNowDays(LocalDate.of(2019, 10, 25).toEpochDay());
        control.distribute(foods);
        control.printStores();

        System.out.println();

        MarketPlace.setNowDays(LocalDate.of(2021, 4, 1).toEpochDay());
        control.resort();
        control.printStores();
    }
}
