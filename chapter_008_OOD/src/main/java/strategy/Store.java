package strategy;

// тут лучше создать интерфейс Store
// с методом
// boolean accept(Food food);
// и в каждом хранилище его реализовать с учетом сроков хранения
// и тогда при добавлении продукта можно в цикле пробежаться по хранилищам
// и if(store.accept(food)) {store.add(food)}


public interface Store {

    boolean accept(Food food);

    void add(Food food);
}
