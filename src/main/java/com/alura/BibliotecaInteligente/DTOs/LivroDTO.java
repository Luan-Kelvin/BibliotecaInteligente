package com.alura.BibliotecaInteligente.DTOs;
import java.util.List;

public record LivroDTO(
         Long id,
         String titulo,
         String isbn,
         Double preco,
         Integer estoque,
         Integer anoPublicacao,
         AutorDTO autor,
         List<String> categorias
) {
}
