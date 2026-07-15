package com.alura.BibliotecaInteligente.Controller;

import com.alura.BibliotecaInteligente.DTOs.AutorDTO;
import com.alura.BibliotecaInteligente.Service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutroController {
    @Autowired
    private AutorService autorService;

    @GetMapping
    public List<AutorDTO> obterAutores(){
        return autorService.obeterAutores();
    }

    @GetMapping("/nome/{nome}")
    public AutorDTO obterAutorPorNome(@PathVariable String nome){
        return autorService.obterAutorPorNome(nome);
    }

    @GetMapping("/id/{id}")
    public AutorDTO obterPorId(@PathVariable Long id){
        return autorService.obterAutroPorId(id);
    }

    @PostMapping
    public AutorDTO cadastraAutor(@RequestBody AutorDTO autorDTO){
        return autorService.salvarAutor(autorDTO);
    }
}
