
//  Реализован банкомат размена денег. Автомат принимает бумажную купюру и обменивает на монеты.
//  Метод должен возвращать список всех возможных вариантов размена купюры.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CashMachine {

    private final int[] values;

    public CashMachine(final int[] values) {
        this.values = values;
        Arrays.sort(this.values);
    }

    public List<List<Integer>> exchange(int note) {
        return exchange(note, this.values[this.values.length - 1]);
    }

    private List<List<Integer>> exchange(int note, int maxCoin) {
        List<List<Integer>> result = new ArrayList<>();
        if (note == 0) {
            result.add(new ArrayList<>());
        } else {
            for (int i = this.values.length - 1; i >= 0; i--) {
                int coin = values[i];
                // второе условие убирает повторы (те же монеты, расставленные по другому)
                if (coin > note || coin > maxCoin) {
                    continue;
                }
                for (List<Integer> remain : exchange(note - coin, coin)) {
                    List<Integer> set = new ArrayList<>();
                    set.add(0, coin);
                    set.addAll(0, remain);
                    result.add(set);
                }
            }
        }
        return result;
    }
}