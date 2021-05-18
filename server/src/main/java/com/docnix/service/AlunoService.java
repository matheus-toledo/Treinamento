package com.docnix.service;

import com.docnix.entity.Aluno;
import com.docnix.exceptionMapper.RegraDeNegocioException;
import com.docnix.exceptionMapper.ServerException;
import com.docnix.repository.AlunoRepository;
import com.docnix.repository.TurmaRepository;

import java.util.*;

public class AlunoService {

    private static final AlunoRepository alunoRepository = new AlunoRepository();
    private static final TurmaRepository turmaRepository = new TurmaRepository();

    public Aluno inserir(Aluno aluno) throws RegraDeNegocioException, ServerException {
        if(Optional.ofNullable(alunoRepository.consultaNome(aluno.getNome())).isPresent()){
            throw new RegraDeNegocioException("Já existe um aluno cadastrado com esse nome!");
        }

        if(Optional.ofNullable(alunoRepository.consultaEmail(aluno.getEmail())).isPresent()){
            throw new RegraDeNegocioException("Já existe um aluno cadastrado com esse email!");
        }

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
