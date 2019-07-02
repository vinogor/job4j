package com;

import ru.job4j.map.User;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Bank {

    // название переменной не отражает сути
    // плюс камел кейс не сделан
    // если использовать класс User, созданный мной, то надо добавить к нему
    // интерфес Comparable (Comparable), чтобы было понятно как сортировать
    private TreeMap<User, ArrayList<Account>> treemap = new TreeMap<>();

    public void addUser(User user) {
        this.treemap.put(user, new ArrayList<>());
    }

    public void delete(User user) {
        this.treemap.remove(user);
    }

    public void add(User user, Account account) {
        this.treemap.get(user).add(account);
    }

    private Account getActualAccount(User user, Account account) {
        ArrayList<Account> list = this.treemap.get(user);
        return list.get(list.indexOf(account));
    }

    public void deleteAccount(User user, Account account) {
        this.treemap.get(user).remove(account);
    }

    public List<Account> getAccounts(User user) {
        return this.treemap.get(user);
    }

    public boolean transfer(User user1, Account account1,
                                 User user2, Account account2, double amount) {
        return this.treemap.get(user1).contains(account1)
                && this.treemap.get(user2).contains(account2)
                && getActualAccount(user1, account1).transfer(
                getActualAccount(user2, account2), amount);
    }

    public String toString() {
        return "Bank{" + "accounts=" + treemap + "}";
    }
}