package com.example.Blog_simple.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {
    private int id;
    private String title;
    private String contain;
    private String autor;
    private LocalDateTime createdAt;
    private String category;
    private String tags;
}
