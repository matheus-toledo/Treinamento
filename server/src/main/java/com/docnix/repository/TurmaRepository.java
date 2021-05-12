package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Curso;
import com.docnix.entity.Turma;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TurmaRepository extends BaseRepository<Turma> {

    public TurmaRepository() {
        super(Turma.class);
    }

    public List<Turma> listar() {
        this.session = HibernateConfig.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(this.getTClass(), "bean");
        criteria.createAlias("bean.curso", "turmaCurso");
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("bean.id").as("id"));
        projectionList.add(Projections.property("bean.nome").as("nome"));
        projectionList.add(Projections.property("bean.matricula").as("matricula"));
        projectionList.add(Projections.property("bean.sequencia").as("sequencia"));
        projectionList.add(Projections.property("turmaCurso.id").as("cursoId"));
        projectionList.add(Projections.property("turmaCurso.nome").as("cursoNome"));
        criteria.setProjection(Projections.distinct(projectionList));

        criteria.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);

        List<Map<String, Object>> result = criteria.list();

        List<Turma> turmas = new ArrayList<>();

        result.forEach(elem -> turmas.add(turmaBuilder(elem)));

        return turmas;
    }

    private Turma turmaBuilder(Map<String, Object> elem) {
        Turma turma = new Turma();
        Curso curso = new Curso();

        curso.setId((Long) elem.get("cursoId"));
        curso.setNome((String) elem.get("cursoNome"));

        turma.setId((Long) elem.get("id"));
        turma.setNome((String) elem.get("nome"));
        turma.setMatricula((String) elem.get("matricula"));
        turma.setCurso(curso);
        return turma;
    }

    public Optional<Long> consultarMaiorSequencia(Turma turma) {
        Long result = (Long) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.curso.id", turma.getCurso().getId()))
                .setProjection(Projections.max("bean.sequencia").as("sequencia"))
                .setMaxResults(1)
                .uniqueResult();

        return result == null ? Optional.empty() : Optional.of(result);
    }

    public Optional<Long> consultarMaiorSequencia(String sigla) {
        Long result = (Long) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .createAlias("bean.curso", "turmaCurso")
                .add(Restrictions.eq("turmaCurso.sigla", sigla))
                .setProjection(Projections.max("bean.sequencia").as("sequencia"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }


}
