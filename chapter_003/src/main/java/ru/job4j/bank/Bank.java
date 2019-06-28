package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//    Необходимо реализовать возможность перечислять деньги,
//    как с одного счёта User на другой счёт того же User, так и на счёт другого User.

//    Посмотрите на методы Map.putIfAbsent и List.indexOf, как их можно применить в этом задании.

public class Bank {

    //  у каждого пользователя может быть список банковских счетов
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
        // а есть такой уже есть - просто НИЧЕГО не делаем?
    }

    public void deleteUser(User user) {
        users.remove(user);
        // если такого юзера нет, то ничего не делаем?

    }

    // добавить счёт пользователю
    public void addAccountToUser(String passport, Account account) {
        User user = findUser(passport);
        // если такой пользователь существует в базе
        if (user != null) {
            // то добавляем счёт к списку счетов юзера
            users.get(user).add(account);
        }
    }

    // удалить один счёт пользователя
    public void deleteAccountFromUser(String passport, Account account) {
        User user = findUser(passport);
        // если такой пользователь существует в базе
        if (user != null) {
            List<Account> accounts = users.get(user);
            int index = accounts.indexOf(account);
            // если такой счёт существует в списке счетов пользователя
            if (index != -1) {
                accounts.remove(index);
            }
        }
    }

    // получить список счетов для пользователя.
    public List<Account> getUserAccounts(String passport) {
        User user = findUser(passport);
        return users.get(user);
    }

    //  метод для перечисления денег с одного счёта на другой счёт:
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String dstPassport, String dstRequisite,
                                 int amount) {
        boolean result = false;

        Account srcAccount = findAccByReqAndPass(srcPassport, srcRequisite);
        Account dstAccount = findAccByReqAndPass(dstPassport, dstRequisite);

        // если у обоих юзеров существуют соответствующие аккаунты с заданными реквизитами
        if ((srcAccount != null) && (dstAccount != null)) {
            result = dstAccount.transfer(srcAccount, amount);
        }
        return result;
    }

    private Account findAccByReqAndPass(String srcPassport, String srcRequisite) {
        Account result = null;
        User user = findUser(srcPassport);
        if (user != null) {
            result = findAccount(user, srcRequisite);
        }
        return result;
    }

    public Account findAccount(User srcUser, String srcRequisite) {
        Account result = null;
        for (Account account : users.get(srcUser)) {
            if (account.getRequisites().equals(srcRequisite)) {
                result = account;
                break;
            }
        }
        return result;
    }

    public User findUser(String passport) {
        User result = null;
        for (Map.Entry<User, List<Account>> entry : users.entrySet()) {
            if (entry.getKey().getPassport().equals(passport)) {
                result = entry.getKey();
                break;
            }
        }
        return result;
    }
}