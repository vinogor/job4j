package ru.job4j.bank;

// это банковский счёт.
public class Account {
    private int value;  // кол-во денег
    private String requisites; // реквизиты счёта

    public Account(int value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }

    public boolean transfer(Account srcAccount, int amount) {
        boolean result = false;
        // если на счету-источника есть достаточное кол-во средств, то
        if (srcAccount.getValue() >= amount) {
            this.value += amount;
            srcAccount.setValue(srcAccount.getValue() - amount);
            result = true;
        }
        return result;
    }
}