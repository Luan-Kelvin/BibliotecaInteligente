package com.alura.BibliotecaInteligente.Service;

import com.alura.BibliotecaInteligente.DTOs.AutorDTO;
import com.alura.BibliotecaInteligente.Entity.Autor;
import com.alura.BibliotecaInteligente.Entity.Livro;
import com.alura.BibliotecaInteligente.Repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutorService {
    private static final Logger logger = LoggerFactory.getLogger(AutorService.class);
    private final AutorRepository autorRepository;

    public AutorDTO salvarAutor(AutorDTO a){
        Optional<Autor> autorRep = autorRepository.findByNomeIgnoreCase(a.nome());

        if (autorRep.isPresent()){
            throw new RuntimeException("Autor com id "+a.id()+" ja esta cadastrado no banco.");
        }

        List<Livro> livros = new ArrayList<>();

        Autor autor = new Autor(a.nome(), a.pais(), LocalDate.parse(a.dataNascimento()));

        autorRepository.save(autor);
        logger.info("Autor "+autor.getNome()+" cadastrado com sucesso!");

        return converterParaAutorDTO(autor);
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
