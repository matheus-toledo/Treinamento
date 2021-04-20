package com.docnix.service;


import com.docnix.entity.Usuario;
import com.docnix.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {
    private static final UsuarioRepository usuarioRepository= new UsuarioRepository();

    public Usuario inserir (Usuario usuario){
        return usuarioRepository.salvar(usuario);
    }

    public Usuario obter(Long id){
        return usuarioRepository.obter(id);
    }

    //listar
    public List<Usuario> listar(){
        return usuarioRepository.listar();
    }
    //editar
    public Usuario editar(Usuario usuario){
        return usuarioRepository.editar(usuario);
    }
    //deletar
    public void deletar(Long id){
        usuarioRepository.deletar(id);
    }

}
