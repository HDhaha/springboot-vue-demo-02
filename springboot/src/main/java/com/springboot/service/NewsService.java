package com.springboot.service;

import com.springboot.domain.News;

import java.util.List;

/**
 * @author 申浩东
 */
public interface NewsService {

    News getNews(Integer id);

    List<News> getNewss();

    boolean saveNews(News News);

    List<News> selectNews(String search);

    boolean updateNews(News usse);

    boolean deldeteNews(Integer id);
}
