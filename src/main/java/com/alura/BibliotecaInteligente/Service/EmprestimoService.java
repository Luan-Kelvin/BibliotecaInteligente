package com.alura.BibliotecaInteligente.Service;

import com.alura.BibliotecaInteligente.DTOs.EmprestimoDTO;
import com.alura.BibliotecaInteligente.Entity.Emprestimo;
import com.alura.BibliotecaInteligente.Repository.EmprestimosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmprestimoService {
    private final EmprestimosRepository emprestimosRepository;

    public EmprestimoDTO converterParaDTO(Emprestimo emp){
        return new EmprestimoDTO(
                emp.getId(),
                emp.getCliente(),
                String.valueOf(emp.getDataEmprestimo()),
                String.valueOf(emp.getDataDevolucao()),
                emp.getStatus(),
                emp.getLivro().getTitulo());
    }

    public List<EmprestimoDTO> obterEmprestimos(){
        List<Emprestimo> emprestimos = emprestimosRepository.findAll();

        if (emprestimos.isEmpty()){
            throw new RuntimeException("Lista de emprstimos esta vazia.");
        }

        List<EmprestimoDTO> dtos = new ArrayList<>();

        emprestimos.forEach(e -> {
            dtos.add(converterParaDTO(e));
        });

        return dtos;
    }
}
