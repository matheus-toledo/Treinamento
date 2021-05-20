package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository extends BaseRepository<Usuario> {

    public UsuarioRepository() {
        super(Usuario.class);
    }

    public List<Usuario> listar() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Usuario.class);
        return (List<Usuario>) criteria.list();
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
}
