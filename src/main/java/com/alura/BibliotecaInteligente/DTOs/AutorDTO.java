package com.alura.BibliotecaInteligente.DTOs;

import java.util.List;

public record AutorDTO(
        Long id,
        String nome,
        String pais,
        String dataNascimento,
        List<String> livros
) {
}
