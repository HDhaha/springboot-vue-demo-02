package com.springboot.controller;

import com.springboot.domain.Account;
import com.springboot.domain.Result;
import com.springboot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author 申浩东
 */
@RestController
@RequestMapping(value = "account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @PostMapping("/login")
    public Result login(@RequestBody Account account, HttpSession httpSession){
        Account acc=accountService.findAccount(account);
        if(acc!=null){
            httpSession.setAttribute("loginUser",acc);
            return Result.success(acc);
        }
        return Result.error("200","账户不存在");
    }

    @PostMapping("/register")
    public Result register(@RequestBody Account account){
        boolean b=accountService.addAccount(account);
        if(b){
            return Result.success();
        }
        return Result.error("200","用户已存在");
    }
    @PutMapping("/updateAccount")
    public Result updateAccount(Account account){
        boolean b=accountService.updateAccount(account);
        if(b){
            return Result.success();
        }
        return Result.error("200","用户已存在");
    }
}
