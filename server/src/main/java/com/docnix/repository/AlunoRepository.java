package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Aluno;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import java.util.*;

public class AlunoRepository extends BaseRepository<Aluno> {

    public AlunoRepository() {
        super(Aluno.class);
    }

    public List listar() {

        return HibernateConfig.getSessionFactory().openSession()
                .createCriteria(getTClass(), "bean")
                .setProjection(Projections.distinct(Projections.projectionList()
                        .add(Projections.property("bean.id").as("id"))
                        .add(Projections.property("bean.nome").as("nome"))
                        .add(Projections.property("bean.email").as("email"))
                        .add(Projections.property("bean.idade").as("idade"))
                        .add(Projections.property("bean.dataDaMatricula").as("dataDaMatricula"))
                        .add(Projections.property("bean.sequencia").as("sequencia"))
                        .add(Projections.property("bean.matricula").as("matricula"))
                ))
                .setResultTransformer(Transformers.aliasToBean(this.getTClass()))
                .list();
    }

    public Optional<Long> getSequencia(String sigla) {
        Long result = (Long) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .createAlias("bean.turma", "alunoTurma")
                .add(Restrictions.eq("alunoTurma.sigla", sigla))
                .setProjection(Projections.projectionList()
                        .add(Projections.max("bean.sequencia")))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }



}
