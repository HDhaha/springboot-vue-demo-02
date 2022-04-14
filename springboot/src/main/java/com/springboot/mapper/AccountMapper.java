package com.springboot.mapper;

import com.springboot.domain.Account;

import java.util.List;

/**
 * @author 申浩东
 */
public interface AccountMapper {
    List<Account> findAccount(Account account);
    List<Account> findAccountByName(Account account);

    int addAccount(Account account);

    int updateAccount(Account account);
}
