package com.alura.BibliotecaInteligente.DTOs;
import java.util.List;

public record LivroDTO(
         Long id,
         String titulo,
         String isbn,
         Double preco,
         Integer estoque,
         Integer anoPublicacao,
         String autor,
         Long idAutor,
         List<String> categorias
) {
}
