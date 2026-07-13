package com.alura.BibliotecaInteligente.Repository;

import com.alura.BibliotecaInteligente.Entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
