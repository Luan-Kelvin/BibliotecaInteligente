package com.alura.BibliotecaInteligente.Repository;

import com.alura.BibliotecaInteligente.Entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query(value = "SELECT * FROM biblioteca.livros_autor(:nome)", nativeQuery = true)
    List<Livro> obterLivrosDeUmAutro(@Param("nome") String nome);
}
