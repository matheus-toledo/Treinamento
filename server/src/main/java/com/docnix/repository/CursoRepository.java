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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CursoRepository {
    public Curso salvar(Curso curso) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(curso);
        session.getTransaction().commit();
        session.close();
        return curso;
    }

    public Curso obter(Long id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        Curso curso = session.get(Curso.class, id);
        session.getTransaction().commit();
        session.close();
        return curso;
    }

    public List<Curso> listar() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Curso.class, "bean");
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

        result.forEach(item -> {
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
            cursos.add(curso);
        });

        return cursos;
    }

    public Curso editar(Curso curso) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(curso);
        session.getTransaction().commit();
        session.close();
        return curso;
    }

    public void deletar(Long id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();

        Curso curso = session.get(Curso.class, id);
        if (curso != null) {
            session.delete(curso);
        }

        session.getTransaction().commit();
        session.close();

    }

}
