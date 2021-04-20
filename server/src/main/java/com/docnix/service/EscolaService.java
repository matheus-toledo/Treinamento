package com.docnix.service;


import com.docnix.entity.Escola;
import com.docnix.repository.EscolaRepository;


import java.util.List;

public class EscolaService {
    private static final EscolaRepository escolaRepository= new EscolaRepository();

    public Escola inserir (Escola escola){
        return escolaRepository.salvar(escola);
    }

    public Escola obter(Long id){
        return escolaRepository.obter(id);
    }

    //listar
    public List<Escola> listar(){
        return escolaRepository.listar();
    }
    //editar
    public Escola editar(Escola escola){
        return escolaRepository.editar(escola);
    }
    //deletar
    public void deletar(Long id){
        escolaRepository.deletar(id);
    }

}
