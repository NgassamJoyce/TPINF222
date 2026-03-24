package com.example.Blog_simple.Util;

import org.springframework.stereotype.Component;

import com.example.Blog_simple.dto.ArticleResponse;
import com.example.Blog_simple.model.Article;

@Component
public class Util {
    public static ArticleResponse convertion(Article article){
        return new ArticleResponse(
           article.getId(),
           article.getTitle(),
           article.getContain(),
           article.getAutor(),
           article.getCreatedAt(),
           article.getCategory(),
           article.getTags()
        );
    }
}
