package parkingOperating;

import parkingOperating.parkings.CarParking;
import parkingOperating.parkings.LorryParking;
import parkingOperating.parkings.Parking;
import parkingOperating.vehicles.Car;
import parkingOperating.vehicles.Lorry;
import parkingOperating.vehicles.Vehicle;

public class Main {

    public static void main(String[] args) {

        Parking carParking = new CarParking(4);
        Parking lorryParking = new LorryParking(3);
        ParkingOperator operator = new ParkingOperator();

        // создаём нужное кол-во транспорта
        Vehicle car = new Car();
        Vehicle lorry = new Lorry();

        // отправляем транспорт в парковку
        boolean result = operator.doJob(carParking, car);

        // сравниваем ожидаемый результат с фактическим

        // комбинации для проверки

        // car в carParking - есть много места (парковка пустая)
        // car в carParking - есть место только под 1
        // car в carParking - нет места совсем

        // lorry в lorryParking - есть много места  (парковка пустая)
        // lorry в lorryParking - есть место только под 1
        // lorry в lorryParking - нет места совсем

        // car в lorryParking - не совместимы типы
        // lorry в carParking - есть много места (парковка пустая)
        // lorry в carParking - есть чисто под 1 lorry (3 места)
        // lorry в carParking - нет достаточного кол-ва мест (2 места)
        // lorry в carParking - нет совсем свободных мест
    }
}
