package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;

import java.util.*;

public class CursoRepository extends BaseRepository<Curso> {

    public CursoRepository() {
        super(Curso.class);
    }

    @SuppressWarnings("unchecked")
    public List<Curso> listar() {
        List<Map<String, Object>> result = HibernateConfig.getSessionFactory().openSession()
                .createCriteria(Curso.class, "bean")
                .createAlias("bean.coordenador", "coordenadorCurso")
                .createAlias("bean.escola", "escolaCurso")
                .setProjection(Projections.distinct(Projections.projectionList()
                        .add(Projections.property("bean.id").as("id"))
                        .add(Projections.property("bean.nome").as("nome"))
                        .add(Projections.property("bean.sigla").as("sigla"))
                        .add(Projections.property("bean.descricao").as("descricao"))
                        .add(Projections.property("coordenadorCurso.nome").as("coordenador.nome"))
                        .add(Projections.property("escolaCurso.nome").as("escola.nome"))))
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();

        List<Curso> cursos = new ArrayList<>();

        result.forEach(item -> cursos.add(cursoBuilder(item)));

        return cursos;
    }

    private Curso cursoBuilder(Map<String, Object> item) {
        Curso curso = new Curso();
        Usuario coordenador = new Usuario();
        Escola escola = new Escola();
        coordenador.setNome((String) item.get("coordenador.nome"));

        escola.setNome((String) item.get("escola.nome"));

        curso.setId((Long) item.get("id"));
        curso.setCoordenador(coordenador);
        curso.setSigla((String) item.get("sigla"));
        curso.setNome((String) item.get("nome"));
        curso.setDescricao((String) item.get("descricao"));
        curso.setEscola(escola);

        return curso;
    }

    @SuppressWarnings("unchecked")
    public Curso obter(Long id) {
        this.session = HibernateConfig.getSessionFactory().openSession();
        Curso curso = this.session.get(this.getTClass(), id);

        Optional.ofNullable(curso).ifPresent(elem -> {
            curso.setMateriasIds(new ArrayList<>());
            curso.setMaterias(curso.getMaterias());
            curso.getMaterias().forEach(materia -> curso.getMateriasIds().add(materia.getId()));
            curso.setMaterias(null);
            curso.getEscola().setDiretor(null);
            curso.getEscola().setDescricao(null);
            curso.getEscola().setDiretor(null);
        });

        this.session.close();

        return curso;

    }

    public Optional<String> obterNome(String nome) {
        String result = (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.nome", nome))
                .setProjection(Projections.property("bean.nome"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }

    public Optional<String> obterNome(String nome, Long id) {
        String result = (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.nome", nome))
                .add(Restrictions.ne("bean.id", id))
                .setProjection(Projections.property("bean.nome"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }

    public Optional<String> obterSigla(String sigla) {
        String result = (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.sigla", sigla))
                .setProjection(Projections.property("bean.sigla"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }

    public Optional<String> obterSigla(String sigla, Long id) {
        String result = (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.sigla", sigla))
                .add(Restrictions.ne("bean.id", id))
                .setProjection(Projections.property("bean.sigla"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }


}