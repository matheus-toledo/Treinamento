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

public class MateriaRepository extends BaseRepository<Materia> {
    public MateriaRepository() {
        super(Materia.class);
    }

    public List<Materia> listar() {
        this.session = HibernateConfig.getSessionFactory().openSession();
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

        result.forEach(elem -> materias.add(materiaBuilder(elem)));

        return materias;
    }

    private Materia materiaBuilder(Map<String, Object> elem) {
        Usuario professor = new Usuario();
        professor.setNome((String) elem.get("professor.nome"));
        Materia materia = new Materia();
        materia.setId((Long) elem.get("id"));
        materia.setNome((String) elem.get("nome"));
        materia.setDescricao((String) elem.get("descricao"));
        materia.setProfessor(professor);

        return materia;
    }

}
