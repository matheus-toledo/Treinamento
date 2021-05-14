package com.docnix.service;

import com.docnix.entity.Aluno;
import com.docnix.repository.AlunoRepository;

import java.util.*;

public class AlunoService {

    private static final AlunoRepository alunoRepository = new AlunoRepository();


    public Aluno inserir(Aluno aluno) {
        aluno.setDataDaMatricula(new Date());
        return alunoRepository.salvar(aluno);
    }

    private Long getSequencia(String sigla) {
        return alunoRepository.getSequencia(sigla).orElse(0L);
    }

    public Aluno obter(Long id) {
        return alunoRepository.obter(id);
    }

    public List listar() {
        return alunoRepository.listar();
    }

    public Aluno editar(Aluno aluno) {
        return alunoRepository.editar(aluno);
    }

    public void deletar(Long id) {
        alunoRepository.deletar(id);
    }

}
