package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Escola;
import com.docnix.entity.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EscolaRepository extends BaseRepository<Escola> {
    public EscolaRepository() {
        super(Escola.class);
    }

    @SuppressWarnings("unchecked")
    public List<Escola> listar() {
        List<Map<String, Object>> result = HibernateConfig.getSessionFactory().openSession()
                .createCriteria(Escola.class, "bean")
                .createAlias("bean.diretor", "diretorEscola")
                .setProjection(Projections.distinct(Projections.projectionList()
                        .add(Projections.property("bean.id").as("id"))
                        .add(Projections.property("bean.nome").as("nome"))
                        .add(Projections.property("diretorEscola.nome").as("diretor.nome"))
                        .add(Projections.property("bean.ativa").as("ativa"))
                        .add(Projections.property("bean.descricao").as("descricao"))))
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();

        List<Escola> escolas = new ArrayList<>();

        result.forEach(row -> escolas.add(escolaBuilder(row)));

        return escolas;
    }

    private Escola escolaBuilder(Map<String, Object> row) {
        Usuario diretor = new Usuario();
        diretor.setNome((String) row.get("diretor.nome"));

        Escola escola = new Escola();
        escola.setId((Long) row.get("id"));
        escola.setNome((String) row.get("nome"));
        escola.setDescricao((String) row.get("descricao"));
        escola.setAtiva((boolean) row.get("ativa"));
        escola.setDiretor(diretor);
        return escola;
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

    public Optional<String> obterNome(String nome, long id) {
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
