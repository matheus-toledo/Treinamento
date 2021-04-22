package com.docnix.service;


import com.docnix.entity.Curso;
import com.docnix.repository.CursoRepository;

import java.util.List;

public class CursoService {
    private static final CursoRepository cursoRepository= new CursoRepository();

    public Curso inserir (Curso curso){
        return cursoRepository.salvar(curso);
    }

    public Curso obter(Long id){

        return cursoRepository.obter(id);
    }

    //listar
    public List<Curso> listar(){

        return cursoRepository.listar();
    }
    //editar
    public Curso editar(Curso curso){
        return cursoRepository.editar(curso);
    }
    //deletar
    public void deletar(Long id){
        cursoRepository.deletar(id);
    }

}