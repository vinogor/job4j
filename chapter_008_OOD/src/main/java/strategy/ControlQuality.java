package strategy;

import strategy.places.*;

import java.time.LocalDate;

public class ControlQuality {

    private Warehouse warehouse = new Warehouse();
    private Shop shop = new Shop();
    private ShopDiscount shopDiscount = new ShopDiscount();
    private Trash trash = new Trash();

    private void start(MarketPlace marketPlace) {
        marketPlace.setNowDays(LocalDate.of(2019, 10, 25).toEpochDay());
        distribute(marketPlace);
        printAllMarketPlaces();

        System.out.println();

        marketPlace.setNowDays(LocalDate.of(2020, 10, 25).toEpochDay());
        resort(marketPlace);
        printAllMarketPlaces();
    }

    private void cleanAllStores() {
        warehouse.clean();
        shop.clean();
        shopDiscount.clean();
        trash.clean();
    }

    private void distribute(MarketPlace marketPlace) {
        for (Food food : marketPlace.getAll()) {
            if (warehouse.accept(food)) {
                warehouse.add(food);
            } else if (shop.accept(food)) {
                shop.add(food);
            } else if (shopDiscount.accept(food)) {
                shopDiscount.add(food);
            } else {
                trash.add(food);
            }
        }
    }

    private void resort(MarketPlace marketPlace) {
        cleanAllStores();
        distribute(marketPlace);
    }

    private void printAllMarketPlaces() {
        System.out.println(warehouse);
        System.out.println(shop);
        System.out.println(shopDiscount);
        System.out.println(trash);
    }

    public static void main(String[] args) {

        // склад с исходными продуктами, которые будем распределять
        MarketPlace marketPlace = new MarketPlace();

        marketPlace.put(new Food(
                "бананы",
                LocalDate.of(2019, 9, 20),
                LocalDate.of(2019, 10, 27),
                15,
                0
            )
        );

        marketPlace.put(new Food(
                "молоко",
                LocalDate.of(2019, 10, 20),
                LocalDate.of(2019, 10, 24),
                22,
                0
            )
        );

        marketPlace.put(new Food(
                "рис",
                LocalDate.of(2019, 5, 3),
                LocalDate.of(2021, 5, 3),
                36,
                0
            )
        );

        marketPlace.put(new Food(
                "говядина",
                LocalDate.of(2019, 10, 18),
                LocalDate.of(2019, 10, 28),
                71,
                0
            )
        );

        new ControlQuality().start(marketPlace);
    }
}