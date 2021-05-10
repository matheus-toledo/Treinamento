package com.docnix.service;

import com.docnix.entity.Turma;

import com.docnix.repository.TurmaRepository;


import java.util.List;
import java.util.Map;

public class TurmaService {
    private static final TurmaRepository turmaRepository= new TurmaRepository();

    public Turma inserir (Turma turma){
        gerarSequencia(turma);
        return  turmaRepository.salvar(turma);
    }

    private void gerarSequencia(Turma turma) {
        Long result = turmaRepository.consultarMaiorSequencia(turma).orElse(0L);
        turma.setSequencia(result + 1);
    }

    public List listarSequencias(){
        List listaSequencias= turmaRepository.listarSequencias();
        return gerarProximaSequencia(listaSequencias);
    }

    private List gerarProximaSequencia(List<Map<String,Object>> listaSequencias){
        listaSequencias.forEach(elem->elem.replace("sequencial",(Long)elem.get("sequencial")+1));
        return listaSequencias;
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
