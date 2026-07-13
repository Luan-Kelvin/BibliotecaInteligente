package com.alura.BibliotecaInteligente.Repository;

import com.alura.BibliotecaInteligente.Entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
