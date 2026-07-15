package com.alura.BibliotecaInteligente.DTOs;

public record EmprestimoDTO(
         Long id,

         String cliente,

         String dataEmprestimo,

         String dataDevolucao,

         String status,

         String livro
) {
}
