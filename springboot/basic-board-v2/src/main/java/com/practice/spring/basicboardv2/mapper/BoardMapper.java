package com.practice.spring.basicboardv2.mapper;

import com.practice.spring.basicboardv2.model.Article;
import com.practice.spring.basicboardv2.model.Paging;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface BoardMapper {

    void saveArticle(Article article);

    List<Article> getBoardArticles(Paging page);

    int getArticleCnt();

    void updateArticle(Article article);
}
