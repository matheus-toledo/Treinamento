package com.docnix.service;


import com.docnix.entity.Escola;
import com.docnix.exceptionMapper.NotFoundException;
import com.docnix.exceptionMapper.RegraDeNegocioException;
import com.docnix.exceptionMapper.ServerException;
import com.docnix.repository.EscolaRepository;



import java.util.List;

public class EscolaService {
    private static final EscolaRepository escolaRepository= new EscolaRepository();

    public Escola inserir (Escola escola) throws RegraDeNegocioException {
        if (escolaRepository.obterNome(escola.getNome()).isPresent()){
            throw new RegraDeNegocioException("Já existe uma escola com esse nome!");
        }
        return escolaRepository.salvar(escola);
    }

    public Escola obter(Long id) throws NotFoundException, ServerException {
        Escola escola = escolaRepository.obter(id);
        if(escola==null){
            throw new NotFoundException("Não existe essa escola no sistema!");
        }
        return escolaRepository.obter(id);
    }

    //listar
    public List<Escola> listar() throws ServerException {
        return escolaRepository.listar();
    }
    //editar
    public Escola editar(Escola escola) throws RegraDeNegocioException {
        if(escolaRepository.obterNome(escola.getNome(),escola.getId()).isPresent()){
            throw new RegraDeNegocioException("Já existe uma escola com esse nome!");
        }
        return escolaRepository.editar(escola);
    }
    //deletar
    public void deletar(Long id){
        escolaRepository.deletar(id);
    }

}
