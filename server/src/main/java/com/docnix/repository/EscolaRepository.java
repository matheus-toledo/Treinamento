package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Escola;
import com.docnix.entity.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EscolaRepository {

    public Escola salvar(Escola escola) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(escola);
        session.getTransaction().commit();
        session.close();
        return escola;
    }

    public Escola obter(Long id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        Escola usuario = session.get(Escola.class, id);
        session.getTransaction().commit();
        session.close();
        return usuario;
    }

    public List<Escola> listar() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Escola.class, "bean");
        criteria.createAlias("bean.diretor", "diretorEscola");
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("bean.id").as("id"));
        projectionList.add(Projections.property("bean.nome").as("nome"));
        projectionList.add(Projections.property("diretorEscola.nome").as("diretor.nome"));
        projectionList.add(Projections.property("bean.ativa").as("ativa"));
        projectionList.add(Projections.property("bean.descricao").as("descricao"));
        criteria.setProjection(Projections.distinct(projectionList));

        criteria.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);

        List<Map<String, Object>> result = criteria.list();

        List<Escola> escolas = new ArrayList<>();

        result.forEach(row -> {
            Usuario diretor = new Usuario();
            diretor.setNome((String) row.get("diretor.nome"));

            Escola escola = new Escola();
            escola.setId((Long) row.get("id"));
            escola.setNome((String) row.get("nome"));
            escola.setDescricao((String) row.get("descricao"));
            escola.setAtiva((boolean) row.get("ativa"));
            escola.setDiretor(diretor);
            escolas.add(escola);
        });

        return escolas;
    }

    public Escola editar(Escola escola) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(escola);
        session.getTransaction().commit();
        session.close();
        return escola;
    }

    public void deletar(Long id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();

        Escola usuario = session.get(Escola.class, id);
        if (usuario != null) {
            session.delete(usuario);
        }

        session.getTransaction().commit();
        session.close();

    }

}
