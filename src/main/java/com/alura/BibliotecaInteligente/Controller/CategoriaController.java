package com.alura.BibliotecaInteligente.Controller;

import com.alura.BibliotecaInteligente.DTOs.CategoriaDTO;
import com.alura.BibliotecaInteligente.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> categorias(){
        return categoriaService.obterTodasCategorias();
    }
}
