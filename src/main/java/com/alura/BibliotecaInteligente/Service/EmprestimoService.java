package com.alura.BibliotecaInteligente.Service;

import com.alura.BibliotecaInteligente.DTOs.EmprestimoDTO;
import com.alura.BibliotecaInteligente.Entity.Emprestimo;
import com.alura.BibliotecaInteligente.Entity.Livro;
import com.alura.BibliotecaInteligente.Repository.EmprestimosRepository;
import com.alura.BibliotecaInteligente.Repository.LivroRepository;
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
public class EmprestimoService {
    private static final Logger logger = LoggerFactory.getLogger(EmprestimoService.class);
    private final EmprestimosRepository emprestimosRepository;
    private final LivroRepository livroRepository;

    public EmprestimoDTO converterParaDTO(Emprestimo emp){
        return new EmprestimoDTO(
                emp.getId(),
                emp.getCliente(),
                String.valueOf(emp.getDataEmprestimo()),
                String.valueOf(emp.getDataDevolucao()),
                emp.getStatus(),
                emp.getLivro().getTitulo(),
                emp.getLivro().getId());
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

    public EmprestimoDTO realizarEmprestimo(EmprestimoDTO emprestimoDTO){
        Optional<Livro> livrorep = livroRepository.findById(emprestimoDTO.idLivro());

        if (!livrorep.isPresent()){
            throw new RuntimeException("Livro com id "+emprestimoDTO.idLivro()+"  não foi encontrado no banco.");
        }
        Emprestimo emprestimo = new Emprestimo(
                emprestimoDTO.cliente(),
                LocalDate.parse(emprestimoDTO.dataEmprestimo()),
                LocalDate.parse(emprestimoDTO.dataDevolucao()),
                emprestimoDTO.status()
        );

        emprestimo.pegarLivroEmprestado(livrorep.get());

        emprestimosRepository.save(emprestimo);

        return converterParaDTO(emprestimo);
    }
}
