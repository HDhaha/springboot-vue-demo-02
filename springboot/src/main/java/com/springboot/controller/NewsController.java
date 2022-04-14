package com.springboot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.domain.Result;
import com.springboot.domain.News;
import com.springboot.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 申浩东
 */
@RestController
@RequestMapping(value = "news")
public class NewsController {
    @Autowired
    NewsService newsService;
    @GetMapping("/getNews")
    public Result getNews(Integer id){
        News news = newsService.getNews(id);

        return Result.success(news);
    }
    @PostMapping("/getNewss")
    public Result getNewss(){
        List<News> lnews=newsService.getNewss();
        return Result.success(lnews);
    }
    @PostMapping("/saveNews")
    public Result saveNews(@RequestBody News news){
        boolean b = newsService.saveNews(news);
        System.out.println("news: "+news);
        if (b) {
            return Result.success();
        }
        return Result.error("200","数据保存失败");
    }
    @PutMapping("/updateNews")
    public Result updateNews(@RequestBody News usse){
        boolean b = newsService.updateNews(usse);
        if(b){
            return Result.success();
        }
        return Result.error("200","数据更改失败");
    }
    @GetMapping("/selectNews")
    public Result selectNews(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "1") Integer pageSize,
                             @RequestParam(defaultValue = "") String search){
        PageHelper.startPage(pageNum, pageSize);
        List<News> newss = newsService.selectNews(search);
        PageInfo<News> newsPageInfo = new PageInfo<>(newss);
        return Result.success(newsPageInfo);
    }
    @DeleteMapping("/deleteNews/{id}")
    public Result deleteNews(@PathVariable("id") Integer id){
        boolean b=newsService.deldeteNews(id);
        if(b){
            return Result.success();
        }
        return Result.error("200","数据更改失败");
    }
}
