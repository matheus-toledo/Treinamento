package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Aluno;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

public class AlunoRepository {

    public Aluno salvar(Aluno aluno) {

        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(aluno);
        session.getTransaction().commit();
        session.close();

        return aluno;
    }

    public Aluno obter(Long id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        Aluno aluno = session.get(Aluno.class, id);
        session.getTransaction().commit();
        session.close();
        return aluno;
    }

    public List<Aluno> listar() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Aluno.class);

        return (List<Aluno>) criteria.list();
    }

    public Aluno editar(Aluno aluno) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(aluno);
        session.getTransaction().commit();
        session.close();
        return aluno;
    }

    public void deletar(Long id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();

        Aluno aluno = session.get(Aluno.class, id);
        if (aluno != null) {
            session.delete(aluno);
        }

        session.getTransaction().commit();
        session.close();
    }

}
