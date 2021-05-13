package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Aluno;
import com.docnix.entity.Turma;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.*;

public class AlunoRepository extends BaseRepository<Aluno> {

    public AlunoRepository() {
        super(Aluno.class);
    }

    public List<Aluno> listar() {
        List result = HibernateConfig.getSessionFactory().openSession()
                .createCriteria(getTClass(), "bean")
                .createAlias("bean.turma", "alunoTurma")
                .setProjection(Projections.distinct(Projections.projectionList()
                        .add(Projections.property("bean.id").as("id"))
                        .add(Projections.property("bean.nome").as("nome"))
                        .add(Projections.property("bean.email").as("email"))
                        .add(Projections.property("bean.idade").as("idade"))
                        .add(Projections.property("bean.dataDaMatricula").as("dataDaMatricula"))
                        .add(Projections.property("bean.sequencia").as("sequencia"))
                        .add(Projections.property("alunoTurma.nome").as("turmaNome"))
                        .add(Projections.property("alunoTurma.sigla").as("turmaSigla"))))
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();

        List<Aluno> alunos = new ArrayList<>();

        result.forEach(elem -> alunos.add(alunoBuilder((Map<String, Object>) elem)));

        return alunos;

    }

    private Aluno alunoBuilder(Map<String, Object> elem) {
        Aluno aluno = new Aluno();
        Turma turma = new Turma();

        turma.setNome((String) elem.get("turmaNome"));
        turma.setSigla((String) elem.get("turmaSigla"));

        aluno.setId((Long) elem.get("id"));
        aluno.setNome((String) elem.get("nome"));
        aluno.setEmail((String) elem.get("email"));
        aluno.setIdade((Integer) elem.get("idade"));
        aluno.setDataDaMatricula((Date) elem.get("dataDaMatricula"));
        aluno.setSequencia((Long) elem.get("sequencia"));
        aluno.setTurma(turma);
        return aluno;
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

    public Set<Aluno> getAlunosEspecificos(Set<Long> ids) {
        List result = HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.in("bean.id", ids))
                .setProjection(Projections.distinct(Projections.projectionList()
                        .add(Projections.property("bean.id").as("id"))
                        .add(Projections.property("bean.nome").as("nome"))
                        .add(Projections.property("bean.email").as("email"))
                        .add(Projections.property("bean.idade").as("idade"))
                        .add(Projections.property("bean.dataDaMatricula").as("dataDaMatricula"))))
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();

        Set<Aluno> alunos = new HashSet<>();

        result.forEach(elem -> alunos.add(alunoBuilder((Map<String, Object>) elem)));

        return alunos;

    }

}
