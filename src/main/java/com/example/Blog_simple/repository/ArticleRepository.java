package com.example.Blog_simple.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Blog_simple.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer>{
    
     // Vérifier l'existence d'un article par titre exact (insensible à la casse)
    Optional<Article> findByTitleIgnoreCase(String title);

    // Recherche par titre (insensible à la casse)
    List<Article> findByTitleContainingIgnoreCase(String title);
    
    // Recherche par catégorie (insensible à la casse)
    List<Article> findByCategoryContainingIgnoreCase(String category);

    // Remplacement de findByCreatedAtBetween par une @Query explicite
    @Query("SELECT a FROM Article a WHERE a.createdAt >= :from AND a.createdAt <= :to")
    List<Article> findByDateRange(
        @Param("from") LocalDateTime from,
        @Param("to") LocalDateTime to
    );
    

    List<Article> findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(String title, String category);
}
