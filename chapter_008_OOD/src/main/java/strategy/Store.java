package strategy;

import java.util.List;

public interface Store {

    boolean accept(Food food);

    void add(Food food);

    String toString();

    List<Food> takeAllStock();
}