package com.springboot.service.impl;

import com.springboot.domain.Account;
import com.springboot.mapper.AccountMapper;
import com.springboot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 申浩东
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;
    @Override
    public Account findAccount(Account account) {
        List<Account> acc = accountMapper.findAccount(account);
        if(acc.size()>0 && acc.get(0)!=null){
            return acc.get(0);
        }
        return null;
    }

    @Override
    public boolean addAccount(Account account) {

        if(accountMapper.findAccountByName(account).size()==0){
            accountMapper.addAccount(account);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAccount(Account account) {
        return accountMapper.updateAccount(account) >0 ? true:false;
    }
}
