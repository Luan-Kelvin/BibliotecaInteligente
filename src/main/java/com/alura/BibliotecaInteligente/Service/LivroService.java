package com.alura.BibliotecaInteligente.Service;

import com.alura.BibliotecaInteligente.DTOs.LivroDTO;
import com.alura.BibliotecaInteligente.Entity.Livro;
import com.alura.BibliotecaInteligente.Repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final LivroRepository livroRepository;
    private static final Logger logger = LoggerFactory.getLogger(LivroService.class);

    public LivroDTO converterParaDto(Livro livro){
        List<String> categorias = new ArrayList<>();

        livro.getCategorias().forEach(l -> {
            categorias.add(l.getNome());
        });

        return new LivroDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getPreco(),
                livro.getEstoque(),
                livro.getAnoPublicacao(),
                livro.getAutor().getNome(),
                livro.getAutor().getId(),
                categorias
        );
    }

    public List<LivroDTO> obterTodosOsLivrosDTO(){
        List<Livro> livros = livroRepository.findAll();

        if (livros.isEmpty()){
            throw new RuntimeException("Nenhum livro cadastrado no banco.");
        }

        List<LivroDTO> dtos = new ArrayList<>();

        livros.forEach(l -> {
            dtos.add(converterParaDto(l));
        });

        return dtos;
    }

    public List<LivroDTO> obterLivrosDeAutor(String nome){
        List<Livro> livros = livroRepository.obterLivrosDeUmAutro(nome);

        if (livros.isEmpty()){
            throw new RuntimeException("Nenhum Livro com autor "+nome+" foi encontrado.");
        }

        List<LivroDTO> dtos = new ArrayList<>();

        livros.forEach(l -> {
            dtos.add(converterParaDto(l));
        });

        return dtos;
    }

    public List<LivroDTO> livrosCaros(){
        List<Livro> caros = livroRepository.obterLivrosCaros();

        if (caros.isEmpty()){
            throw new RuntimeException("Nenhum livro cadastrado, lista está vazia.");
        }

        List<LivroDTO> dtos = new ArrayList<>();

        caros.forEach(l -> {
            dtos.add(converterParaDto(l));
        });

        return dtos;
    }

    public List<LivroDTO> livroComEstoqueVazio(){
        List<Livro> livros = livroRepository.estoqueVazio();

        if (livros.isEmpty()){
            logger.info("Não existe nenhum livro sem estoque.");
        }

        List<LivroDTO> dtos = new ArrayList<>();

        livros.forEach(l -> {
            dtos.add(converterParaDto(l));
        });

        return dtos;
    }
}
