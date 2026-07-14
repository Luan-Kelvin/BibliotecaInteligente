package com.alura.BibliotecaInteligente.Repository;

import com.alura.BibliotecaInteligente.Entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
