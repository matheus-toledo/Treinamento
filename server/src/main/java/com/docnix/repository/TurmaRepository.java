package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Curso;
import com.docnix.entity.Turma;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.*;

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
       this.session = HibernateConfig.getSessionFactory().openSession();
       Turma turma = this.session.get(this.getTClass(),id);
       Optional.ofNullable(turma).ifPresent(elem ->{
           turma.setAlunosIds(new ArrayList<>());
           turma.setCurso(turma.getCurso());
           turma.setAlunos(turma.getAlunos());
           turma.getAlunos().forEach(aluno -> {
               turma.getAlunosIds().add(aluno.getId());
           });
           turma.getCurso().setEscola(null);
           turma.getCurso().setCoordenador(null);
           turma.getCurso().setMaterias(null);
           turma.getCurso().setDescricao(null);
           turma.getCurso().setNome(null);
           turma.setAlunos(null);
       });
       this.session.close();
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

    public String obterNomeDaTurmaDoAluno(Long id){
        return (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .createAlias("bean.alunos", "alunos")
                .add(Restrictions.eq("alunos.id", id))
                .setProjection(Projections.property("bean.nome"))
                .setMaxResults(1)
                .uniqueResult();
    }

    public Optional<String> obterNome(String nome){
        String result = (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.nome",nome))
                .setProjection(Projections.property("bean.nome"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }

    public Optional<String> obterNome(String nome, Long id){
        String result = (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.nome",nome))
                .add(Restrictions.ne("bean.id",id))
                .setProjection(Projections.property("bean.nome"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }
    public Optional<String> obterSigla(String sigla){
        String result = (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.sigla",sigla))
                .setProjection(Projections.property("bean.sigla"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }
    public Optional<String> obterSigla(String sigla, Long id){
        String result = (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.sigla",sigla))
                .add(Restrictions.ne("bean.id",id))
                .setProjection(Projections.property("bean.sigla"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }

}
