package com.alura.BibliotecaInteligente.Controller;

import com.alura.BibliotecaInteligente.DTOs.EmprestimoDTO;
import com.alura.BibliotecaInteligente.Service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    EmprestimoService emprestimoService;

    @GetMapping
    public List<EmprestimoDTO> obterEmprestimos(){
        return emprestimoService.obterEmprestimos();
    }
}
