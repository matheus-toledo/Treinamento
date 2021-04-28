package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Materia;
import com.docnix.entity.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MateriaRepository {
    public Materia salvar(Materia materia) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(materia);
        session.getTransaction().commit();
        session.close();
        return materia;
    }

    public Materia obter(Long id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        Materia materia = session.get(Materia.class, id);
        session.getTransaction().commit();
        session.close();
        return materia;
    }

    public List<Materia> listar() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Materia.class, "bean");
        criteria.createAlias("bean.professor", "professorMateria");
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("bean.id").as("id"));
        projectionList.add(Projections.property("bean.nome").as("nome"));
        projectionList.add(Projections.property("bean.descricao").as("descricao"));
        projectionList.add(Projections.property("professorMateria.nome").as("professor.nome"));
        criteria.setProjection(Projections.distinct(projectionList));
        criteria.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);

        List<Map<String, Object>> result = criteria.list();

        List<Materia> materias = new ArrayList<>();

        result.forEach(row -> {
            Usuario professor = new Usuario();
            professor.setNome((String) row.get("professor.nome"));
            Materia materia = new Materia();
            materia.setId((Long) row.get("id"));
            materia.setNome((String) row.get("nome"));
            materia.setDescricao((String) row.get("descricao"));
            materia.setProfessor(professor);
            materias.add(materia);
        });

        return materias;
    }

    public Materia editar(Materia materia) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(materia);
        session.getTransaction().commit();
        session.close();
        return materia;
    }

    public void deletar(Long id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();

        Materia materia = session.get(Materia.class, id);
        if (materia != null) {
            session.delete(materia);
        }

        session.getTransaction().commit();
        session.close();

    }

}
