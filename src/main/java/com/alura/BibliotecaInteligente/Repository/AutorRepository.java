package com.alura.BibliotecaInteligente.Repository;

import com.alura.BibliotecaInteligente.Entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNomeIgnoreCase(String nome);

    @Query(value = "SELECT * FROM biblioteca.estatisticas_autor(:id_autor)", nativeQuery = true)
    Optional<Autor> obterAutorPorId(@PathVariable("id_autor") Long id_autor);

}
