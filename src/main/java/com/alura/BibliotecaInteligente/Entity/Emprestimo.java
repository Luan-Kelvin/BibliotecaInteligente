package com.alura.BibliotecaInteligente.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "emprestimos", schema = "biblioteca")
@Getter
@Setter
@NoArgsConstructor
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cliente;

    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucao;

    private String status;

    @ManyToOne()
    @JoinColumn(name = "livro_id")
    private Livro livro;

    public Emprestimo(String cliente, LocalDate dataEmprestimo, LocalDate dataDevolucao, String status) {
        this.cliente = cliente;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }

    public void pegarLivroEmprestado(Livro livro){
        if (livro.getEstoque() > 0){
            this.livro = livro;
            livro.setEstoque(livro.getEstoque() - 1);
        }
    }
    @Override
    public String toString() {
        return "Cliente = " + cliente + " | Data De Emprestimo = " + dataEmprestimo + " | Data De Devolucao = " + dataDevolucao + " | Status = " + status;
    }
}
