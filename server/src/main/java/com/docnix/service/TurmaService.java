package com.docnix.service;

import com.docnix.entity.Turma;

import com.docnix.repository.TurmaRepository;


import java.util.List;

public class TurmaService {
    private static final TurmaRepository turmaRepository= new TurmaRepository();

    public Turma inserir (Turma turma){
        gerarSequencia(turma);
        return  turmaRepository.salvar(turma);
    }

    private void gerarSequencia(Turma turma) {
        Long result = turmaRepository.consultarMaiorSequencia(turma).orElse(1L);
        turma.setSequencia(result + 1);
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
