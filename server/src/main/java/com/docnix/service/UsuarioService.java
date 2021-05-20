package com.docnix.service;


import com.docnix.entity.Usuario;
import com.docnix.exceptionMapper.NotFoundException;
import com.docnix.exceptionMapper.RegraDeNegocioException;
import com.docnix.exceptionMapper.ServerException;
import com.docnix.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {
    private static final UsuarioRepository usuarioRepository= new UsuarioRepository();

    public Usuario inserir (Usuario usuario) throws RegraDeNegocioException, ServerException {
        if (usuarioRepository.obterNome(usuario.getNome()).isPresent()){
            throw new RegraDeNegocioException("Já existe um usuário cadastrado com esse nome!");
        }
        return usuarioRepository.salvar(usuario);
    }

    public Usuario obter(Long id) throws ServerException, NotFoundException {
        Usuario usuario = usuarioRepository.obter(id);
        if (usuario == null){
            throw new NotFoundException("Não existe esse usuário no sistema!");
        }
        return usuario;
    }

    public List<Usuario> listar() throws ServerException {
        return usuarioRepository.listar();
    }

    public Usuario editar(Usuario usuario) throws RegraDeNegocioException, ServerException{
        if(usuarioRepository.obterNome(usuario.getNome(), usuario.getId()).isPresent()){
            throw new RegraDeNegocioException("Já existe um usuário com esse nome!");
        }
        return usuarioRepository.editar(usuario);
    }

    public void deletar(Long id){
        usuarioRepository.deletar(id);
    }

}
