package com.xqx.dao;

import com.xqx.domain.Account;

import java.util.ArrayList;

public interface AccountDao {
    int add(Account account);
    int delete(int id);
    ArrayList<Account> select();
    Account select(int id);
    int update(Account account);
}
