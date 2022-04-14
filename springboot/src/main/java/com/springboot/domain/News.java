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
public class News {
    private Integer id;
    private String author;
    private String title;
    private String content;
    private String releaseTime;
}
