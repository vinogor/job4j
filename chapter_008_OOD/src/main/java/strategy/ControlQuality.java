package strategy;

import strategy.places.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    
    private List<Store> stores;
    private List<Food> foods;
    
    public ControlQuality(List<Store> stores, List<Food> foods) {
        this.stores = stores;
        this.foods = foods;
    }
    
    private void start() {
        
        MarketPlace.setNowDays(LocalDate.of(2019, 10, 25).toEpochDay());
        distribute();
        printStores();
        
        System.out.println();
        
        MarketPlace.setNowDays(LocalDate.of(2021, 4, 1).toEpochDay());
        resort();
        printStores();
        
    }
    
    private void resort() {
        cleanStores();
        distribute();
    }
    
    private void cleanStores() {
        for (Store store : stores) {
            store.clean();
        }
    }
    
    private void printStores() {
        for (Store store : stores) {
            System.out.println(store);
        }
    }
    
    private void addFood(Food food) {
        for (Store store : stores) {
            if (store.accept(food)) {
                store.add(food);
            }
        }
    }
    
    private void distribute() {
        for (Food food : foods) {
            addFood(food);
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
        
        new ControlQuality(stores, foods).start();
    }
}
