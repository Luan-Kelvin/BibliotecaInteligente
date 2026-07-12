package com.alura.BibliotecaInteligente.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria", schema = "biblioteca")
@Getter
@Setter
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany(mappedBy = "categorias")
    private List<Livro> livros = new ArrayList<>();

    public Categoria(String nome) {
        this.nome = nome;
    }

    public void adicionarLivro(Livro livro){
        if(!livros.contains(livro)){
            livros.add(livro);
        }

        if(!livro.getCategorias().contains(this)){
            livro.getCategorias().add(this);
        }
    }

    @Override
    public String toString() {
        return "Categoria: " + nome;
    }
}
