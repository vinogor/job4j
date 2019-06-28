package com;

public class Account {

    // модификатор доступа private
    double values;
    String reqs;

    public Account(double values, String requisites) {
        this.values = values;
        this.reqs = requisites;
    }

    public double getValues() {
        return this.values;
    }


    public String getReqs () {
        return this.reqs;
    }

    boolean transfer(Account destination, double amount) {
        boolean success = false;
        if (amount > 0 && amount < this.values && destination != null) {
            success = true;
            this.values -= amount;
            destination.values += amount;
        }
        return success;
    }

    public String toString() {
        String otvet;
        otvet = "Account{" + "values=" + values + ", reqs='" + reqs + "\\" + "}";
        return otvet;
        // тут можно было в одну строку написать:
        // return "Account{" + "values=" + values + ", reqs='" + reqs + "\\" + "}";
    }

    // добавить аннотацию @Override
    // думаю стоит сделать чтобы иквелс зависил от всех полей класса
    public boolean equals(Object o) {
        // целых 3 return, а надо чтобы был один!
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        return this.reqs.equals(account.reqs);
    }

    // добавить аннотацию @Override
    // думаю стоит сделать чтобы хэшкод зависил от всех полей класса
    public int hashCode() {
        return this.reqs.hashCode();
    }

}