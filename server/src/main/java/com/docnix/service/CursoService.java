package com.docnix.service;


import com.docnix.entity.Curso;
import com.docnix.exceptionMapper.NotFoundException;
import com.docnix.exceptionMapper.RegraDeNegocioException;
import com.docnix.exceptionMapper.ServerException;
import com.docnix.repository.CursoRepository;

import java.util.List;

public class CursoService {
    private static final CursoRepository cursoRepository = new CursoRepository();

    public Curso inserir(Curso curso) throws RegraDeNegocioException {
        if (cursoRepository.obterNome(curso.getNome()).isPresent()) {
            throw new RegraDeNegocioException("Já existe um curso com esse nome!");
        }

        if (cursoRepository.obterSigla(curso.getSigla()).isPresent()) {
            throw new RegraDeNegocioException("Já existe um curso com essa sigla!");
        }
        return cursoRepository.salvar(curso);
    }

    public Curso obter(Long id) throws ServerException, NotFoundException {
        Curso curso = cursoRepository.obter(id);

        if (curso == null) {
            throw new NotFoundException("Não existe esse curso no sitema!");
        }

        return curso;
    }

    //listar
    public List<Curso> listar() throws ServerException {
        return cursoRepository.listar();
    }

    //editar
    public Curso editar(Curso curso) throws RegraDeNegocioException {
        if (cursoRepository.obterNome(curso.getNome(),curso.getId()).isPresent()) {
            throw new RegraDeNegocioException("Já existe um curso com esse nome!");
        }

        if (cursoRepository.obterSigla(curso.getSigla(),curso.getId()).isPresent()) {
            throw new RegraDeNegocioException("Já existe um curso com essa sigla!");
        }
        return cursoRepository.editar(curso);
    }

    //deletar
    public void deletar(Long id) {
        cursoRepository.deletar(id);
    }

}