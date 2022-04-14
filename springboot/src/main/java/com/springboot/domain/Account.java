package com.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 申浩东
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class Account {
    private Integer id;
    private String username;
    private String password;
    private String nickName;
    private String sex;
    private Integer age;
    private String address;
    private String register;
}
