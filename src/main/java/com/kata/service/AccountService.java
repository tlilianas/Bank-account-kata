package com.kata.service;

import com.kata.model.Account;

import java.util.List;

public interface AccountService {

    public Account getAccountbyId(long id);
    public List<Account> getAllAccounts();
    public Account saveAccount(Account account);
    public void deleteAccount(Account account);
    public double getBalance(long accountId);

}
