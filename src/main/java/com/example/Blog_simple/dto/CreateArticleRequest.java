package com.example.Blog_simple.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateArticleRequest {
    
    @NotBlank(message = "le titre est obligatoire")
    @Size(min = 5, max = 100, message = "le titre doit etre compris entre 5 et 30 caracteres")
    private String title;

    @Size(max = 1000, message = "le titre ne doit pas depasser 1000 caracteres")
    private String contain;

    @Size(max = 50, message = "le titre ne doit pas depasser 10 caracteres")
    private String autor;

    @NotBlank(message = "la category est obligatoire")
    @Size(max = 100, message = "la category ne doit pas depasser 100 caracteres")
    private String category;

    @NotBlank(message = "le tags est obligatoire")
    private String tags;
}
