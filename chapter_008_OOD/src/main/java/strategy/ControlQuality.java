package strategy;

import strategy.places.MarketPlace;
import strategy.places.Shop;
import strategy.places.Trash;
import strategy.places.Warehouse;
import strategy.strategies.MoveToShop;
import strategy.strategies.MoveToShopWithDiscount;
import strategy.strategies.MoveToTrash;
import strategy.strategies.MoveToWarehouse;

import java.time.LocalDate;

public class ControlQuality {

    private void start(
        MarketPlace marketPlace,
        Context control,
        Warehouse warehouse,
        Shop shop,
        Trash trash) {

//        long nowDays = LocalDate.now().toEpochDay();
        long nowDays = LocalDate.of(2019, 10, 25).toEpochDay();

        for (Food food : marketPlace.getAll()) {
            long expireDays = food.getExpireDate().toEpochDay();
            long createDays = food.getCreateDate().toEpochDay();
            long leftProc = (nowDays - createDays) * 100 / (expireDays - createDays);

            if (leftProc > 100) {
                control.setStrategy(new MoveToTrash(trash));
            } else if (leftProc > 75) {
                control.setStrategy(new MoveToShop(shop));
            } else if (leftProc > 25) {
                control.setStrategy(new MoveToShopWithDiscount(shop));
            } else {
                control.setStrategy(new MoveToWarehouse(warehouse));
            }
            control.execute(food);
        }
        printAllMarketPlaces(trash, shop, warehouse);
    }

    private void printAllMarketPlaces(Trash trash, Shop shop, Warehouse warehouse) {
        System.out.println(trash);
        System.out.println(shop);
        System.out.println(warehouse);
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

        new ControlQuality().start(
            marketPlace,
            new Context(),
            new Warehouse(),
            new Shop(),
            new Trash()
        );
    }
}
