
// Задание такое.
//        Надо реализовать метод выдачи сдачи для автомата.
//        int[] changes(int value, int price)
//        value = купюра. 50 100 и тд.
//        price = цена кофе
//        в автомате монеты наминалом 1 2 5 10
//        Пример. Мы засунули 50 рублей выбрали кофе за 35. Сдачу автомат должен дать 15 рублей
//        Алгоритм должен вернуть наименьшее количество монет.
//        Метод вернет массив {10, 5} = 15 рублей
//        создать задачу залить в репозитори добавить ссылку

import java.util.ArrayList;
import java.util.List;

public class CoffeeMachine {

    int[] changes(int value, int price) {
        int change = value - price;
        List<Integer> result = new ArrayList<>();
        int[] coins = new int[]{1, 2, 5, 10};

        for (int i = coins.length-1; i >=0; i--) {
            int coin = coins[i]; // берём с конца, максимальную монету
            do {
                if (change - coin >= 0) {
                    change -= coin;
                    result.add(coin);
                } else {
                    break;
                }
            } while (change != 0);
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
}
