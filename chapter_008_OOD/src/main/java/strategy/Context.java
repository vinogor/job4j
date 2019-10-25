package strategy;

// Создать класс обработчик перераспределения продуктов в место использования.
// ControlQuality. Класс должен перераспределять еду по хранилищам в зависимости от условиый.

public class Context {

    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute(Food food) {
        strategy.replaceProduct(food);
    }
}