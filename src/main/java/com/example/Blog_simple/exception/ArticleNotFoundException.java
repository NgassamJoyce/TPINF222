package com.example.Blog_simple.exception;

import org.springframework.http.HttpStatus;

public class ArticleNotFoundException extends BaseException {
    
    public ArticleNotFoundException(int id){
        super("article non trouver avec l`id" + id,
              "ARTICLE_NOT-FOUND",
               HttpStatus.NOT_FOUND
        );
    }
}
