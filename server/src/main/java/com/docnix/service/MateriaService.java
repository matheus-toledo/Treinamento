package com.docnix.service;


import com.docnix.entity.Materia;
import com.docnix.entity.Usuario;
import com.docnix.repository.MateriaRepository;

import java.util.List;

public class MateriaService {
    private static final MateriaRepository materiaRepository= new MateriaRepository();

    public Materia inserir (Materia materia){
        return materiaRepository.salvar(materia);
    }

    public Materia obter(Long id){

        return materiaRepository.obter(id);
    }

    //listar
    public List<Materia> listar(){

        return materiaRepository.listar();
    }
    //editar
    public Materia editar(Materia materia){
        return materiaRepository.editar(materia);
    }
    //deletar
    public void deletar(Long id){
        materiaRepository.deletar(id);
    }

}
