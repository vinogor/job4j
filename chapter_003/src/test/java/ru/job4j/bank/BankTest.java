package ru.job4j.bank;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class BankTest {

    private static Bank bank;
    private  static User user1;
    private  static User user2;
    private  static Account account1;
    private  static Account account2;

    @Before
    public void setUp() {
        bank = new Bank();
        user1 = new User("name1", "passport1");
        user2 = new User("name2", "passport2");
        account1 = new Account(1000, "requisites1");
        account2 = new Account(2000, "requisites2");
    }

    @Test
    public void addUser() {
        bank.addUser(user1);
        User user = bank.findUser("passport1");
        assertThat(user, is(user1));
    }

    @Test
    public void deleteUser() {
        bank.addUser(user1);
        bank.deleteUser(user1);
        User user = bank.findUser("passport1");
        assertThat(user, nullValue());
    }

    @Test
    public void addAccountToUser() {
        bank.addUser(user1);
        bank.addAccountToUser("passport1", account1);

        User user = bank.findUser("passport1");
        Account account = bank.findAccount(user, "requisites1");
        assertThat(account, is(account1));
    }

    @Test
    public void deleteAccountFromUser() {
        bank.addUser(user1);
        bank.addAccountToUser("passport1", account1);
        bank.deleteAccountFromUser("passport1", account1);

        User user = bank.findUser("passport1");
        Account account = bank.findAccount(user, "requisites1");
        assertThat(account, nullValue());
    }

    @Test
    public void getUserAccounts() {
        bank.addUser(user1);
        bank.addAccountToUser("passport1", account1);
        List<Account> accounts = bank.getUserAccounts("passport1");
        List<Account> expect = new ArrayList<>() {{
            add(account1);
        }};

        assertThat(accounts, is(expect));
    }

    @Test
    public void transferMoneyTest1() {
        bank.addUser(user1);
        bank.addAccountToUser("passport1", account1);
        bank.addUser(user2);
        bank.addAccountToUser("passport2", account2);

        bank.transferMoney(
                "passport1", "requisites1",
                "passport2", "requisites2",
                500);
        int user2money = account2.getValue();
        assertThat(user2money, is(2500));
    }

    @Test
    public void transferMoneyTest2() {
        bank.addUser(user1);
        bank.addAccountToUser("passport1", account1);
        bank.addUser(user2);
        bank.addAccountToUser("passport2", account2);

        bank.transferMoney(
                "passport1", "requisites1",
                "passport2", "requisites2",
                2000);
        int user2money = account2.getValue();
        assertThat(user2money, is(2000));
    }
}