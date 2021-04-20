package com.docnix.service;

import com.docnix.entity.Aluno;
import com.docnix.repository.AlunoRepository;

import java.util.Date;
import java.util.List;

public class AlunoService {

    private static final AlunoRepository alunoRepository = new AlunoRepository();

    public Aluno inserir(Aluno aluno) {
        aluno.setDataDaMatricula(new Date());
        return alunoRepository.salvar(aluno);
    }

    public Aluno obter(Long id) {
        return alunoRepository.obter(id);
    }

    public List<Aluno> listar() {
        return alunoRepository.listar();
    }

    public Aluno editar(Aluno aluno) {
        return alunoRepository.editar(aluno);
    }

    public void deletar(Long id) {
        alunoRepository.deletar(id);
    }

}
