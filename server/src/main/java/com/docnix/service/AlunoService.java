package com.docnix.service;

import com.docnix.entity.Aluno;
import com.docnix.repository.AlunoRepository;
import com.docnix.repository.TurmaRepository;

import java.util.*;

public class AlunoService {

    private static final AlunoRepository alunoRepository = new AlunoRepository();
    private static final TurmaRepository turmaRepository = new TurmaRepository();

    public Aluno inserir(Aluno aluno) {
        aluno.setDataDaMatricula(new Date());
        return alunoRepository.salvar(aluno);
    }

    public void updateSequencia(List<Long> ids,Long sequencial) {
        alunoRepository.updateSequencia(ids,sequencial+1);
    }

    public Aluno obter(Long id) {
        Aluno aluno = alunoRepository.obter(id);
        aluno.setNomeTurma(turmaRepository.obterNomeDaTurmaDoAluno(aluno.getId()));
        return aluno;
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

    public void removerSequencias(List idsParaRemoverSequencias) {
        alunoRepository.removerSequencias(idsParaRemoverSequencias);
    }
}
