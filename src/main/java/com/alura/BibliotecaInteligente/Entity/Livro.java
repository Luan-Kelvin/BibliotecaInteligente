package com.alura.BibliotecaInteligente.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livros", schema = "biblioteca")
@Getter
@Setter
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String isbn;

    private Double preco;

    private Integer estoque;

    private Integer anoPublicacao;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    Autor autor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "livro_categoria",
            schema = "biblioteca",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias = new ArrayList<>();

    public Livro(String titulo, String isbn, Double preco, Integer estoque, Integer anoPublicacao, Autor autor) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.preco = preco;
        this.estoque = estoque;
        this.anoPublicacao = anoPublicacao;
        this.autor = autor;

        if (autor != null){
            autor.adicionarLivro(this);
        }
    }

    public void adicionarCategoria(Categoria categoria){
        if(!categorias.contains(categoria)){
            categorias.add(categoria);
        }

        if(!categoria.getLivros().contains(this)){
            categoria.getLivros().add(this);
        }
    }


    @Override
    public String toString() {
        return "Titulo = " + titulo + " | Preco = R$" + preco + " |  Ano De Publicação =  " + anoPublicacao + " | Autor = "+autor.getNome();
    }
}
