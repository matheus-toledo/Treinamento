package com.docnix.service;

import com.docnix.entity.Aluno;
import com.docnix.entity.Turma;
import com.docnix.repository.AlunoRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class AlunoService {

    private static final AlunoRepository alunoRepository = new AlunoRepository();

    private static Set<Aluno> alunoSet;


    public Aluno inserir(Aluno aluno) {
        aluno.setDataDaMatricula(new Date());
        return alunoRepository.salvar(aluno);
    }


    private Long getSequencia(String sigla) {
        return alunoRepository.getSequencia(sigla).orElse(0L);
    }

    public Set<Aluno> obterAlunosEspecificosComTurma(Set<Aluno> alunoList, Turma turma) {
        alunoSet = alunoList;
        Set<Long> idList = getIdsDosAlunos(alunoList);

        alunoSet = alunoRepository.getAlunosEspecificos(idList);

        popularTurmaNaListaDeAlunos(turma);

        return alunoSet;

    }

    private void popularTurmaNaListaDeAlunos(Turma turma) {
        alunoSet = alunoSet.stream().map(aluno -> {
            aluno.setTurma(turma);
            return aluno;
        }).collect(Collectors.toSet());
        gerarSequenciaDeAlunos(turma.getSigla());
    }

    private Set<Long> getIdsDosAlunos(Set<Aluno> alunos) {
        Set<Long> alunosIdList = new HashSet<>();
        alunos.forEach(aluno -> {
            alunosIdList.add(aluno.getId());
        });
        return alunosIdList;
    }

    private void gerarSequenciaDeAlunos(String sigla) {
        AtomicReference<Long> result = new AtomicReference<>(getSequencia(sigla));
        alunoSet.forEach(aluno -> {
            aluno.setSequencia(result.getAndSet(result.get() + 1));
        });
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
