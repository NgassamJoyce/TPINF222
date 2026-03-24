package com.example.Blog_simple.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Blog_simple.Util.Util;
import com.example.Blog_simple.dto.ArticleResponse;
import com.example.Blog_simple.dto.CreateArticleRequest;
import com.example.Blog_simple.exception.ArticleAlreadyExistsException;
import com.example.Blog_simple.exception.ArticleNotFoundException;
import com.example.Blog_simple.model.Article;
import com.example.Blog_simple.repository.ArticleRepository;

@Service
public class ArticleService {
    
    @Autowired
    ArticleRepository articleRepository;


    // Fuseau horaire du Cameroun

    // recuperer tout les articles
    public List<ArticleResponse> getAll(){

        List<Article> articleDeLaBD = articleRepository.findAll();
        List<ArticleResponse> articleTransformer = new ArrayList<>();

        for (Article article : articleDeLaBD) {
            ArticleResponse response = Util.convertion(article);
            articleTransformer.add(response);
        }

        return articleTransformer;
    }


    // recuperer un article en particulier
    public ArticleResponse getArticleById(int id){
        ArticleResponse articleResponse = new ArticleResponse();
        Article articleBD = articleRepository.findById(id).orElse(null);

    // quand quelqu`un appele ton API avec un ID qui n`existe pas il retourne l`erreur 404
        if (articleBD == null) {
            throw new ArticleNotFoundException(id);
        }
        
        articleResponse = Util.convertion(articleBD);
        return articleResponse;
    }


    // Recherche par mot clé par titre ou catégorie
    public List<ArticleResponse> search(String keyword) {
        List<Article> articles = articleRepository
            .findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(keyword, keyword);
    
        List<ArticleResponse> responses = new ArrayList<>();
           for (Article article : articles) {
             responses.add(Util.convertion(article));
            }
        return responses;
    }

    // Recherche par catégorie exacte
    public List<ArticleResponse> getByCategory(String category) {
        List<Article> articles = articleRepository
            .findByCategoryContainingIgnoreCase(category);
        List<ArticleResponse> responses = new ArrayList<>();
        for (Article article : articles) {
            responses.add(Util.convertion(article));
        }
        return responses;
    }

    // Recherche par plage de dates
   public List<ArticleResponse> getByDateRange(LocalDateTime from, LocalDateTime to) {
    // Avant : articleRepository.findByCreatedAtBetween(from, to)
    List<Article> articles = articleRepository.findByDateRange(from, to);
    List<ArticleResponse> responses = new ArrayList<>();
    for (Article article : articles) {
        responses.add(Util.convertion(article));
    }
    return responses;
   }


    // creation d`un article
    public ArticleResponse createArticle(CreateArticleRequest request){

        // Vérifier si un article avec ce titre existe déjà
        articleRepository.findByTitleIgnoreCase(request.getTitle())
            .ifPresent(existing -> {
                throw new ArticleAlreadyExistsException(request.getTitle());
            });
        Article article = new Article();

        article.setTitle(request.getTitle());
        article.setContain(request.getContain());
        article.setCategory(request.getCategory());
        article.setAutor(request.getAutor());
        article.setTags(request.getTags());
        article.setCreatedAt(LocalDateTime.now(ZoneId.of("Africa/Douala"))); 
        articleRepository.save(article);
        
        return Util.convertion(article);
    }


   // modifier un article en particulier
    public ArticleResponse updateArticle(int id, CreateArticleRequest request){
        Article article = articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));

        // Vérifier si un AUTRE article a déjà ce titre
        articleRepository.findByTitleIgnoreCase(request.getTitle())
            .ifPresent(existing -> {
                if (existing.getId() != id) {
                    throw new ArticleAlreadyExistsException(request.getTitle());
                }
            });

        article.setTitle(request.getTitle());
        article.setContain(request.getContain());
        article.setCategory(request.getCategory());
        article.setCreatedAt(LocalDateTime.now(ZoneId.of("Africa/Douala"))); 
        article.setTags(request.getTags());
        article.setAutor(request.getAutor());

        articleRepository.save(article);
        return Util.convertion(article);

    }


   // supprimer un Article
    public void deleteById(int id){
        Article article = articleRepository.findById(id).orElse(null);

        articleRepository.delete(article);
    }
    
}
