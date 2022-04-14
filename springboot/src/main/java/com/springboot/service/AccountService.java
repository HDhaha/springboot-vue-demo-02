package com.springboot.service;

import com.springboot.domain.Account;

/**
 * @author 申浩东
 */
public interface AccountService {
    Account findAccount(Account account);

    boolean addAccount(Account account);

    boolean updateAccount(Account account);
}
