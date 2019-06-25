
//  Реализован банкомат размена денег. Автомат принимает бумажную купюру и обменивает на монеты.
//  Метод должен возвращать список всех возможных вариантов размена купюры.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CashMachine {

    public static void main(String[] args) {
        CashMachine cashMachine = new CashMachine(new int[]{10, 5, 1});
        cashMachine.exchange(50);

    }

    private final int[] values;

    public CashMachine(final int[] values) {
        System.out.println("монеты для размена - " + Arrays.toString(values));
        this.values = values;
        Arrays.sort(this.values);
        System.out.println("отсортировали - " + Arrays.toString(values));
    }

    public List<List<Integer>> exchange(int note) {
        System.out.println("надо разменять - " + note);
        List<List<Integer>> result = new ArrayList<>();

        int length = this.values.length;
        System.out.println("кол-во номиналов для рамена - " + length);
        int[] maxQuantityOfOneTypeCoin = new int[length];
        for (int i = 0; i < length; i++) {
            maxQuantityOfOneTypeCoin[i] = note / values[i];
        }

        System.out.println("макc кол-во монет для размена - " + Arrays.toString(maxQuantityOfOneTypeCoin));

        int[] a = new int[length];
        a[0] = maxQuantityOfOneTypeCoin[0] - 1;
        System.out.println("массив для завершения подбора комбинаций = " + Arrays.toString(a));

        do {
            System.out.println();
            System.out.println("начинаем подбирать комбинации");

            List<Integer> list = new ArrayList<>();
            int balance = note;

            do {
                // формируем комбинацию

                // проходимся по монете каждого номинала, начиная с крупной
                for (int i = length - 1; i >= 0; i--) {

                    // берём самую крупную монетку
                    int coin = values[i];
                    System.out.println("    взяли монету номиналом " + coin);
                    // кидаем сдачу выбранной монеткой

                    for (int j = 0; j < maxQuantityOfOneTypeCoin[i]; j++) {
                        list.add(coin);
                        balance -= coin;
                        System.out.println("    баланс стал - " + balance);

                        // если вдруг разменивать нечего - выходим
                        if (balance == 0) {
                            System.out.println("    баланс = 0, комбинация подобрана");
                            break;
                        }
                    }

                    if (balance == 0) {
                        System.out.println("    баланс = 0, комбинация подобрана");
                        break;
                    }
                }

                // в следующей комбинации самого большого номенала будет на 1 монетку меньше (если уже не равно 0)
                for (int i = length - 1; i >= 0; i--) {
                    if (maxQuantityOfOneTypeCoin[i] != 0) {
                        maxQuantityOfOneTypeCoin[i]--;
                        break;
                    }
                }
                System.out.println("макc кол-во монет для размена - " + Arrays.toString(maxQuantityOfOneTypeCoin));

            } while (balance != 0);

            // добавляем комбинацию
            System.out.println("итоговая комбинация - " + list);
            result.add(list);

            // ищем очередную комбинацию пока выполняется условие
        } while (!Arrays.equals(maxQuantityOfOneTypeCoin, a));

        return result;
    }

// с рекурсией

//    public List<List<Integer>> exchange(int note) {
//        return exchange(note, this.values[this.values.length - 1]);
//    }
//
//    private List<List<Integer>> exchange(int note, int maxCoin) {
//        List<List<Integer>> result = new ArrayList<>();
//        if (note == 0) {
//            result.add(new ArrayList<>());
//        } else {
//            for (int i = this.values.length - 1; i >= 0; i--) {
//                int coin = values[i];
//                // второе условие убирает повторы (те же монеты, расставленные по другому)
//                if (coin > note || coin > maxCoin) {
//                    continue;
//                }
//                for (List<Integer> remain : exchange(note - coin, coin)) {
//                    List<Integer> set = new ArrayList<>();
//                    set.add(0, coin);
//                    set.addAll(0, remain);
//                    result.add(set);
//                }
//            }
//        }
//        return result;
//    }
}