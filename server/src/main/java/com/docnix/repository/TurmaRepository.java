package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Aluno;
import com.docnix.entity.Curso;
import com.docnix.entity.Turma;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.*;
import java.util.stream.Collectors;

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
        this.session.close();
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


    /*  public Turma salvar(Turma turma) {
          this.session = HibernateConfig.getSessionFactory().openSession();
          session.getTransaction().begin();
          turma = (Turma) session.merge(turma);
          session.getTransaction().commit();
          session.close();
          return turma;

      }
  */
    public Optional<Long> getSequenciaAluno(Long id) {
        Long result = (Long) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(Turma.class, "turma")
                .createAlias("turma.alunos", "alunos")
                .add(Restrictions.eq("turma.id", id))
                .setProjection(Projections.projectionList()
                        .add(Projections.max("alunos.sequencia")))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);

    }

    public Long consultarSequencia(Long id) {
        Long result = (Long) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.id", id))
                .setProjection(Projections.property("bean.sequencia"))
                .setMaxResults(1)
                .uniqueResult();

        return result;
    }

    public Optional<String> consultarSiglaCurso(String sigla, Long id) {
        String result = (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .createAlias("bean.curso", "curso")
                .add(Restrictions.eq("bean.id", id))
                .add(Restrictions.eq("curso.sigla", sigla))
                .setProjection(Projections.property("bean.sigla"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }

    public Long consultarCursoDaTurma(Long id) {
        Long result = (Long) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .createAlias("bean.curso", "curso")
                .add(Restrictions.eq("bean.id", id))
                .setProjection(Projections.property("curso.id"))
                .setMaxResults(1)
                .uniqueResult();

        return result;
    }


    public List obterIdAlunosDaTurmaOriginal(Long id) {
        List result = HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .createAlias("bean.alunos", "alunos")
                .add(Restrictions.eq("bean.id", id))
                .setProjection(Projections.property("alunos.id"))
                .list();

        return result;
    }


    public Optional<Long> obterMaiorSequencialDoAlunosDeUmaTurma(Long id) {
        Long result = (Long) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .createAlias("bean.alunos", "alunos")
                .add(Restrictions.eq("bean.id", id))
                .setProjection(Projections.max("alunos.sequencia"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }

    public Turma merge(Turma turma) {
        return (Turma) this.session.merge(turma);
    }

    @SuppressWarnings("unchecked")
    public Turma obter(Long id) {
        List<Map<String, Object>> result = (List<Map<String, Object>>) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .createAlias("bean.curso", "curso")
                .createAlias("bean.alunos", "alunos")
                .add(Restrictions.eq("bean.id", id))
                .setProjection(Projections.distinct(Projections.projectionList()
                        .add(Projections.property("bean.id").as("id"))
                        .add(Projections.property("bean.nome").as("nome"))
                        .add(Projections.property("bean.sigla").as("sigla"))
                        .add(Projections.property("bean.matricula").as("matricula"))
                        .add(Projections.property("bean.sequencia").as("sequencia"))
                        .add(Projections.property("curso.id").as("cursoId"))
                        .add(Projections.property("curso.sigla").as("cursoSigla"))
                        .add(Projections.property("alunos.id").as("alunoId"))))
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();

        Turma turma = new Turma();

        Curso curso = new Curso();

        curso.setId((Long) result.get(0).get("cursoId"));
        curso.setSigla((String) result.get(0).get("cursoSigla"));

        turma.setId((Long) result.get(0).get("id"));
        turma.setNome((String) result.get(0).get("nome"));
        turma.setSigla((String) result.get(0).get("sigla"));
        turma.setSequencia((Long) result.get(0).get("sequencia"));
        turma.setMatricula((String) result.get(0).get("matricula"));
        turma.setAlunosIds(new ArrayList<>());
        turma.setCurso(curso);

        result.forEach(elem -> {
            turma.getAlunosIds().add((Long) elem.get("alunoId"));
        });
        return turma;

    }

    public String obterSiglaParaAluno(Long id){
       return (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .createAlias("bean.alunos", "alunos")
                .add(Restrictions.eq("alunos.id", id))
                .setProjection(Projections.property("bean.sigla"))
                .setMaxResults(1)
                .uniqueResult();
    }

}
