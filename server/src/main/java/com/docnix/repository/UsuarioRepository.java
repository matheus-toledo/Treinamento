package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

public class UsuarioRepository {
    public Usuario salvar(Usuario usuario){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(usuario);
        session.getTransaction().commit();
        session.close();
        return usuario;
    }

    public Usuario obter(Long id){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        Usuario usuario = session.get(Usuario.class, id);
        session.getTransaction().commit();
        session.close();
        return usuario;
    }

    public List<Usuario> listar(){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Usuario.class);
        return (List<Usuario>) criteria.list();
    }

    public Usuario editar(Usuario usuario){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(usuario);
        session.getTransaction().commit();
        session.close();
        return usuario;
    }

    public void deletar (Long id){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();

        Usuario usuario = session.get(Usuario.class,id);
        if (usuario!=null){
            session.delete(usuario);
        }

        session.getTransaction().commit();
        session.close();

    }

}
