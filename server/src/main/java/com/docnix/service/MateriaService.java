package com.docnix.service;


import com.docnix.entity.Materia;
import com.docnix.entity.Usuario;
import com.docnix.exceptionMapper.RegraDeNegocioException;
import com.docnix.exceptionMapper.ServerException;
import com.docnix.repository.MateriaRepository;

import java.util.List;

public class MateriaService {
    private static final MateriaRepository materiaRepository = new MateriaRepository();

    public Materia inserir(Materia materia) throws RegraDeNegocioException, ServerException {
        if (materiaRepository.obterNome(materia.getNome()).isPresent()){
            throw new RegraDeNegocioException("Já existe uma matéria com esse nome!");
        }
            return materiaRepository.salvar(materia);
    }

    public Materia obter(Long id) throws RegraDeNegocioException, ServerException {
        Materia materia = materiaRepository.obter(id);

        if(materia==null){
            throw new RegraDeNegocioException("Não existe essa matéria no sistema!",404);
        }
        return materia;
    }

    //listar
    public List<Materia> listar() throws ServerException {
        return materiaRepository.listar();
    }

    //editar
    public Materia editar(Materia materia) throws RegraDeNegocioException, ServerException {
        if(materiaRepository.obterNome(materia.getNome(),materia.getId()).isPresent()){
            throw new RegraDeNegocioException("Já existe uma matéria com esse nome!");
        }
        return materiaRepository.editar(materia);
    }

    //deletar
    public void deletar(Long id) {
        materiaRepository.deletar(id);
    }

}
