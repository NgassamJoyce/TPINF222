package com.example.Blog_simple.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Blog_simple.dto.ArticleResponse;
import com.example.Blog_simple.dto.CreateArticleRequest;
import com.example.Blog_simple.service.ArticleService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;






@RestController
@RequestMapping("/api/article")
public class ArticleController {
    
   @Autowired
   private ArticleService articleService; // le controller pointe vers le service


   // recuperer tous les articles
   @GetMapping
   public ResponseEntity<List<ArticleResponse>> getAll() {
       
       List<ArticleResponse> articleResponses = articleService.getAll();
       return ResponseEntity.ok(articleResponses);
   }
   

   // recuperer un utilisateur par son id
   @GetMapping("/{id}")
   public ResponseEntity<ArticleResponse> getById(@PathVariable int id){
       
      try {
        ArticleResponse articleResponse = articleService.getArticleById(id);
        return ResponseEntity.ok(articleResponse);
      } catch (Exception e) {
         return ResponseEntity.notFound().build();
      }
    }

    // Recherche par mot-clé dans titre ou catégorie
    @GetMapping("/search")
    public ResponseEntity<List<ArticleResponse>> search(
            @RequestParam String keyword) {
        List<ArticleResponse> results = articleService.search(keyword);
        return ResponseEntity.ok(results);
    }


     // Récupérer les articles par catégorie
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ArticleResponse>> getByCategory(
            @PathVariable String category) {
        List<ArticleResponse> results = articleService.getByCategory(category);
        return ResponseEntity.ok(results);
    }

    // Récupérer les articles par plage de dates
    // Exemple: /api/article/date?from=2024-01-01T00:00:00&to=2024-12-31T23:59:59
    @GetMapping("/date")
    public ResponseEntity<List<ArticleResponse>> getByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        List<ArticleResponse> results = articleService.getByDateRange(from, to);
        return ResponseEntity.ok(results);
    }

    

    // creation d`une nouvelle article
    @PostMapping
    public ResponseEntity<ArticleResponse>CreateArticle(@Valid @RequestBody CreateArticleRequest request) {
        
        try {
            ArticleResponse articleResponse = articleService.createArticle(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(articleResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    // modifier un article en particulier
    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponse>updateArticle(@PathVariable int id, @Valid @RequestBody CreateArticleRequest request){

        try{
           ArticleResponse articleResponse = articleService.updateArticle(id, request);
           return ResponseEntity.ok(articleResponse);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // suppression d`un article
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteById(@PathVariable int id){

        try {
            articleService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
   
}
