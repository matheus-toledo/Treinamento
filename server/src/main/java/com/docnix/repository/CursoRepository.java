package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Aluno;
import com.docnix.entity.Curso;
import com.docnix.entity.Escola;
import com.docnix.entity.Usuario;
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

    public List<Curso> listar() {
        this.session = HibernateConfig.getSessionFactory().openSession();
        Criteria criteria = this.session.createCriteria(Curso.class, "bean");
        criteria.createAlias("bean.coordenador", "coordenadorCurso");
        criteria.createAlias("bean.escola", "escolaCurso");

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("bean.id").as("id"));
        projectionList.add(Projections.property("bean.nome").as("nome"));
        projectionList.add(Projections.property("bean.sigla").as("sigla"));
        projectionList.add(Projections.property("bean.descricao").as("descricao"));
        projectionList.add(Projections.property("coordenadorCurso.id").as("coordenador.id"));
        projectionList.add(Projections.property("coordenadorCurso.nome").as("coordenador.nome"));
        projectionList.add(Projections.property("escolaCurso.id").as("escola.id"));
        projectionList.add(Projections.property("escolaCurso.nome").as("escola.nome"));
        criteria.setProjection(Projections.distinct(projectionList));

        criteria.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);

        List<Map<String, Object>> result = criteria.list();

        List<Curso> cursos = new ArrayList<>();

        result.forEach(item -> cursos.add(cursoBuilder(item)));
        this.session.close();
        return cursos;
    }

    private Curso cursoBuilder(Map<String, Object> item) {
        Curso curso = new Curso();
        Usuario usuario = new Usuario();
        Escola escola = new Escola();
        usuario.setId((Long) item.get("coordenador.id"));
        usuario.setNome((String) item.get("coordenador.nome"));

        escola.setNome((String) item.get("escola.nome"));

        curso.setId((Long) item.get("id"));
        curso.setCoordenador(usuario);
        curso.setSigla((String) item.get("sigla"));
        curso.setNome((String) item.get("nome"));
        curso.setDescricao((String) item.get("descricao"));
        curso.setEscola(escola);

        return curso;
    }

    public Optional<String> obterNome(String nome) {
        String result = (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(),"bean")
                .add(Restrictions.eq("bean.nome",nome))
                .setProjection(Projections.property("bean.nome"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }

    public Optional<String> obterNome(String nome, Long id) {
        String result = (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(),"bean")
                .add(Restrictions.eq("bean.nome",nome))
                .add(Restrictions.ne("bean.id",id))
                .setProjection(Projections.property("bean.nome"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }

    public Optional<String> obterSigla(String sigla) {
        String result = (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(),"bean")
                .add(Restrictions.eq("bean.sigla",sigla))
                .setProjection(Projections.property("bean.sigla"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }
    public Optional<String> obterSigla(String sigla,Long id) {
        String result = (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(),"bean")
                .add(Restrictions.eq("bean.sigla",sigla))
                .add(Restrictions.ne("bean.id",id))
                .setProjection(Projections.property("bean.sigla"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }


}