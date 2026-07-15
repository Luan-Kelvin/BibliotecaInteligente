package com.alura.BibliotecaInteligente.Repository;

import com.alura.BibliotecaInteligente.Entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query(value = "SELECT * FROM biblioteca.livros_autor(:nome)", nativeQuery = true)
    List<Livro> obterLivrosDeUmAutro(@Param("nome") String nome);

//    @Query("""
//            SELECT l
//            FROM Livro l
//            ORDER BY l.preco DESC
//            LIMIT 10
//            """)
    @Query(value = "SELECT * FROM biblioteca.livros_caros()", nativeQuery = true)
    List<Livro> obterLivrosCaros();

    @Query("""
            SELECT l
            FROM Livro l
            WHERE l.estoque = 0
            """)
    List<Livro> estoqueVazio();
}
