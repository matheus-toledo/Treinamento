package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Escola;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

public class EscolaRepository {

    public Escola salvar(Escola escola){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(escola);
        session.getTransaction().commit();
        session.close();
        return escola;
    }

    public Escola obter(Long id){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        Escola usuario = session.get(Escola.class, id);
        session.getTransaction().commit();
        session.close();
        return usuario;
    }

    public List<Escola> listar(){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Escola.class);
        return (List<Escola>) criteria.list();
    }

    public Escola editar(Escola escola){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(escola);
        session.getTransaction().commit();
        session.close();
        return escola;
    }

    public void deletar (Long id){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();

        Escola usuario = session.get(Escola.class,id);
        if (usuario!=null){
            session.delete(usuario);
        }

        session.getTransaction().commit();
        session.close();

    }

}
