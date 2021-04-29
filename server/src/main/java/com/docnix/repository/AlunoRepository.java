package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Aluno;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import java.util.List;

public class AlunoRepository extends BaseRepository<Aluno> {

    public AlunoRepository() {
        super(Aluno.class);
    }

    public List<Aluno> listar() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Aluno.class);

        return (List<Aluno>) criteria.list();
    }

}
