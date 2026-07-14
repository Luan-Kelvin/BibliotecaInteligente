package com.alura.BibliotecaInteligente.Service;

import com.alura.BibliotecaInteligente.DTOs.AutorDTO;
import com.alura.BibliotecaInteligente.Entity.Autor;
import com.alura.BibliotecaInteligente.Entity.Livro;
import com.alura.BibliotecaInteligente.Repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutorService {
    private final AutorRepository autorRepository;

    public void salvarAutor(Autor a){
        Optional<Autor> autorRep = autorRepository.findById(a.getId());

        if (autorRep.isPresent()){
            throw new RuntimeException("Autor com id "+a.getId()+" ja esta cadastrado no banco.");
        }

        autorRepository.save(a);
        System.out.println(a.getNome()+" salvo com sucesso!");
    }

    public AutorDTO converterParaAutorDTO(Autor a){
        List<String> livros = new ArrayList<>();

        a.getLivros().forEach(l -> {
            livros.add(l.getTitulo());
        });

        return new AutorDTO(a.getId(), a.getNome(), a.getPais(), String.valueOf(a.getDataNascimento()), livros);
    }

    public List<AutorDTO> obeterAutores(){
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()){
            throw new RuntimeException("Nenhum autor cadastrado ate o momento.");
        }

        List<AutorDTO> dtos = new ArrayList<>();

        autores.forEach(a -> {
            dtos.add(converterParaAutorDTO(a));
        });

        return dtos;
    }

    public AutorDTO obterAutorPorNome(String nome){
        Optional<Autor> autor = autorRepository.findByNomeIgnoreCase(nome);

        if (!autor.isPresent()){
            throw new RuntimeException("Autor com nome "+nome+" não foi encontrado no banco");
        }

        return converterParaAutorDTO(autor.get());
    }

    public AutorDTO obterAutroPorId(Long id){
        Optional<Autor> autor = autorRepository.obterAutorPorId(id);

        if (!autor.isPresent()){
            throw new RuntimeException("Autor com id "+id+" não foi encontrado.");
        }

        return converterParaAutorDTO(autor.get());
    }
}
