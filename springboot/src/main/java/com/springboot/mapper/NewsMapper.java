package com.springboot.mapper;

import com.springboot.domain.News;

import java.util.List;

/**
 * @author 申浩东
 */
public interface NewsMapper {
    News getNews(Integer id);

    List<News> getNewss();

    int saveNews(News News);

    List<News> selectNews(String search);

    int updateNews(News News);

    int deleteNews(Integer id);
}
