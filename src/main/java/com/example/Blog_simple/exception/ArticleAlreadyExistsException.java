package com.example.Blog_simple.exception;

import org.springframework.http.HttpStatus;

public class ArticleAlreadyExistsException extends BaseException {
    
    public ArticleAlreadyExistsException(String title) {
        super(
            "Un article avec le titre '" + title + "' existe déjà",
            "ARTICLE_ALREADY_EXISTS",
            HttpStatus.CONFLICT
        );
    }
}
