package com.springboot.service.impl;

import com.springboot.domain.News;
import com.springboot.mapper.NewsMapper;
import com.springboot.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 申浩东
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsMapper newsMapper;
    @Override
    public News getNews(Integer id) {
        News news = newsMapper.getNews(id);
        return news;
    }

    @Override
    public List<News> getNewss() {
        return newsMapper.getNewss();
    }

    @Override
    public boolean saveNews(News news) {
//        if(news.getId()){
//
//        }
        int i = newsMapper.saveNews(news);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public List<News> selectNews(String search) {
        return newsMapper.selectNews(search);
    }

    @Override
    public boolean updateNews(News news) {
        return newsMapper.updateNews(news) >0 ? true:false;
    }

    @Override
    public boolean deldeteNews(Integer id) {
        return newsMapper.deleteNews(id) >0 ?true:false;
    }
}
