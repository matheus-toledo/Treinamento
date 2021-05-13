package com.docnix.service;

import com.docnix.entity.Aluno;
import com.docnix.entity.Turma;

import com.docnix.repository.TurmaRepository;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TurmaService {
    private static final TurmaRepository turmaRepository= new TurmaRepository();
    private static final AlunoService alunoService = new AlunoService();

    public Turma inserir (Turma turma){
        gerarSequencia(turma);
        turma.setAlunos(alunoService.obterAlunosEspecificosComTurma(turma.getAlunos(),turma));

        return  turmaRepository.salvar(turma);
    }

    private void gerarSequencia(Turma turma) {
        Long result = turmaRepository.consultarMaiorSequencia(turma).orElse(0L);
        turma.setSequencia(result + 1);
    }

    public Long gerarSequencia(String sigla){
        Long result= turmaRepository.consultarMaiorSequencia(sigla).orElse(1L);
        return result;
    }

    public Turma obter(Long id){
        return turmaRepository.obter(id);
    }

    //listar
    public List<Turma> listar(){
        return turmaRepository.listar();
    }
    //editar
    public Turma editar(Turma turma){
        return turmaRepository.editar(turma);
    }
    //deletar
    public void deletar(Long id){
        turmaRepository.deletar(id);
    }

}
