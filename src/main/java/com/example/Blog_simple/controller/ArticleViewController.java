package com.example.Blog_simple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Blog_simple.dto.ArticleResponse;
import com.example.Blog_simple.dto.CreateArticleRequest;
import com.example.Blog_simple.service.ArticleService;

import jakarta.validation.Valid;





@Controller
@RequestMapping("/articles")
public class ArticleViewController {

    @Autowired
    private ArticleService articleService;

    // recuperer tous les articles
    @GetMapping("/list")
    public String listArticles(@RequestParam(required = false) String keyword, Model model) {
        List<ArticleResponse> articles;
    
        if (keyword != null && !keyword.isEmpty()) {
           articles = articleService.search(keyword);
           model.addAttribute("keyword", keyword);
        } else {
           articles = articleService.getAll();
        }
    
        model.addAttribute("articles", articles);
        return "articles/list";
    }


    // Formulaire de création → /blog/new
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        
        model.addAttribute("article", new CreateArticleRequest());
        return "articles/create"; // → templates/articles/create.html
    }


    // Soumettre le formulaire de création
    @PostMapping("/create")
    public String createArticle(@Valid @ModelAttribute CreateArticleRequest request, BindingResult bindingResult) {

        // si l`utilisateur veux publier l`article sans avoir remplis les champs
        if (bindingResult.hasErrors()) {
            return "articles/create";
        }
        articleService.createArticle(request);
        return "redirect:/blog"; // retour à la liste
       
       
    }


    // Formulaire de modification → /blog/edit/1
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        ArticleResponse article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "articles/edit"; // → templates/articles/edit.html
    }


    // Soumettre la modification
    @PostMapping("/edit/{id}")
    public String updateArticle(@PathVariable int id, @ModelAttribute CreateArticleRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "articles/edit";
        }
        articleService.updateArticle(id, request);
        return "redirect:/blog";
    }

    // Supprimer un article → /blog/delete/1
    @GetMapping("/delete/{id}")
    public String deleteArticle(@PathVariable int id) {
        articleService.deleteById(id);
        return "redirect:/blog";
    }

    // Détail d'un article → /blog/1
    @GetMapping("/{id}")
    public String detailArticle(@PathVariable int id, Model model) {
        ArticleResponse article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "articles/details";
    }
}


