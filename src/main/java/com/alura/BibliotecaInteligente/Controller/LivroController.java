package com.alura.BibliotecaInteligente.Controller;

import com.alura.BibliotecaInteligente.DTOs.LivroDTO;
import com.alura.BibliotecaInteligente.Service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public LivroDTO adicionarLivro(@RequestBody LivroDTO livroDTO){
        return livroService.adicionarLivro(livroDTO);
    }

    @GetMapping("/autor/{nome}")
    public List<LivroDTO> livrosDeAutor(@PathVariable String nome){
        return livroService.obterLivrosDeAutor(nome);
    }

    @GetMapping("/caros")
    public List<LivroDTO> livrosCaros(){
        return livroService.livrosCaros();
    }

    @GetMapping("/estoque/vazio")
    public List<LivroDTO> estoqueVazio(){
        return livroService.livroComEstoqueVazio();
    }

    @PutMapping("/reajuste/{id}")
    public LivroDTO reajuseDePreco (@RequestBody LivroDTO livroDTO, @PathVariable Long id){
        return livroService.reajusteLivro(id, livroDTO);
    }

}
