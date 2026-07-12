package com.alura.BibliotecaInteligente.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores", schema = "biblioteca")
@Getter
@Setter
@NoArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String pais;

    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<Livro> livros = new ArrayList<>();

    public Autor(String nome, String pais, LocalDate dataNascimento) {
        this.nome = nome;
        this.pais = pais;
        this.dataNascimento = dataNascimento;
    }

    public void adicionarLivro(Livro l){
        livros.add(l);

        if (l.getAutor() != this){
            l.setAutor(this);
        }
    }

    @Override
    public String toString() {
        return "Autor = " + nome + " | Pais = " + pais + ".";
    }
}
