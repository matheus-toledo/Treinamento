package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Aluno;
import com.docnix.entity.Turma;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;

import javax.persistence.JoinColumn;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class AlunoRepository extends BaseRepository<Aluno> {
    private TurmaRepository turmaRepository = new TurmaRepository();
    public AlunoRepository() {
        super(Aluno.class);
    }

    @SuppressWarnings("unchecked")
    public List listar() {

        List<Aluno> alunoList = (List<Aluno>) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(getTClass(), "bean")
                .setProjection(Projections.distinct(Projections.projectionList()
                        .add(Projections.property("bean.id").as("id"))
                        .add(Projections.property("bean.nome").as("nome"))
                        .add(Projections.property("bean.email").as("email"))
                        .add(Projections.property("bean.idade").as("idade"))
                        .add(Projections.property("bean.dataDaMatricula").as("dataDaMatricula"))
                        .add(Projections.property("bean.sequencia").as("sequencia"))
                ))
                .setResultTransformer(Transformers.aliasToBean(this.getTClass()))
                .list();

        alunoList.forEach(aluno ->{
            aluno.setMatricula(turmaRepository.obterSiglaParaAluno(aluno.getId()));
        });

        return alunoList;
    }


    public void updateSequencia(List<Long> ids, Long sequencial) {
        AtomicLong sequencia = new AtomicLong(sequencial);

        this.session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        ids.forEach(id -> {
            Aluno alunoPersistent = session.find(this.getTClass(),id);
            alunoPersistent.setSequencia(sequencia.getAndSet(sequencia.get() + 1));
        });
        session.getTransaction().commit();
        this.session.close();
    }

    public void removerSequencias(List<Long> ids) {
        this.session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        ids.forEach(id -> {
            Aluno alunoPersistent = session.find(this.getTClass(),id);
            alunoPersistent.setSequencia(null);
        });
        session.getTransaction().commit();
        this.session.close();
    }

    public Optional<String> consultaNome(String nome) {
        String result = (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.nome",nome))
                .setProjection(Projections.property("bean.nome").as("nome"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }

    public Optional<String> consultaEmail(String email) {
        String result = (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.email",email))
                .setProjection(Projections.property("bean.email").as("email"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }
}
