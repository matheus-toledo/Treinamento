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
import java.util.Optional;

public class MateriaRepository extends BaseRepository<Materia> {
    public MateriaRepository() {
        super(Materia.class);
    }

    @SuppressWarnings("unchecked")
    public List<Materia> listar() {
        List<Map<String, Object>> result = HibernateConfig.getSessionFactory().openSession()
                .createCriteria(Materia.class, "bean")
                .createAlias("bean.professor", "professorMateria")
                .setProjection(Projections.distinct(Projections.projectionList()
                        .add(Projections.property("bean.id").as("id"))
                        .add(Projections.property("bean.nome").as("nome"))
                        .add(Projections.property("bean.descricao").as("descricao"))
                        .add(Projections.property("professorMateria.nome").as("professor.nome"))))
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();

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
}
