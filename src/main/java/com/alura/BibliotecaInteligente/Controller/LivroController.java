package com.alura.BibliotecaInteligente.Controller;

import com.alura.BibliotecaInteligente.DTOs.LivroDTO;
import com.alura.BibliotecaInteligente.Service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<LivroDTO> obterTodosOsLivros(){
        return livroService.obterTodosOsLivrosDTO();
    }

    @GetMapping("/autor/{nome}")
    public List<LivroDTO> livrosDeAutor(@PathVariable String nome){
        return livroService.obterLivrosDeAutor(nome);
    }

    @GetMapping("/caros")
    public List<LivroDTO> livrosCaros(){
        return livroService.livrosCaros();
    }

}
