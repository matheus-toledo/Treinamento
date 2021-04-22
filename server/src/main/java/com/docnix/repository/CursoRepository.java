package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Curso;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

public class CursoRepository {
    public Curso salvar(Curso curso){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(curso);
        session.getTransaction().commit();
        session.close();
        return curso;
    }

    public Curso obter(Long id){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        Curso curso = session.get(Curso.class, id);
        session.getTransaction().commit();
        session.close();
        return curso;
    }

    public List<Curso> listar(){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Curso.class);
        return (List<Curso>) criteria.list();
    }

    public Curso editar(Curso curso){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(curso);
        session.getTransaction().commit();
        session.close();
        return curso;
    }

    public void deletar (Long id){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();

        Curso curso = session.get(Curso.class,id);
        if (curso!=null){
            session.delete(curso);
        }

        session.getTransaction().commit();
        session.close();

    }

}
