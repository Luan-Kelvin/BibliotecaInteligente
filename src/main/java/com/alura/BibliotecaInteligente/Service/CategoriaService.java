package com.alura.BibliotecaInteligente.Service;

import com.alura.BibliotecaInteligente.DTOs.CategoriaDTO;
import com.alura.BibliotecaInteligente.Entity.Categoria;
import com.alura.BibliotecaInteligente.Repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaDTO converterCategoriaParaDTO(Categoria c){
        return new CategoriaDTO(c.getId(), c.getNome());
    }

    public List<CategoriaDTO> obterTodasCategorias(){
        List<Categoria> cat = categoriaRepository.findAll();

        if (cat.isEmpty()){
            throw new RuntimeException("Erro nenhuma categoria foi cadastrada no banco.");
        }

        List<CategoriaDTO> dtos = new ArrayList<>();

        cat.forEach(c -> {
            dtos.add(converterCategoriaParaDTO(c));
        });

        return dtos;
    }
}
