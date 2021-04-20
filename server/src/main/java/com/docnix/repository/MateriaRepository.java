package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Materia;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

public class MateriaRepository {
    public Materia salvar(Materia materia){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(materia);
        session.getTransaction().commit();
        session.close();
        return materia;
    }

    public Materia obter(Long id){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        Materia materia = session.get(Materia.class, id);
        session.getTransaction().commit();
        session.close();
        return materia;
    }

    public List<Materia> listar(){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Materia.class);
        return (List<Materia>) criteria.list();
    }

    public Materia editar(Materia materia){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(materia);
        session.getTransaction().commit();
        session.close();
        return materia;
    }

    public void deletar (Long id){
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();

        Materia materia = session.get(Materia.class,id);
        if (materia!=null){
            session.delete(materia);
        }

        session.getTransaction().commit();
        session.close();

    }

}
