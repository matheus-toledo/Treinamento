package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

public class UsuarioRepository extends BaseRepository<Usuario> {

    public UsuarioRepository() {
        super(Usuario.class);
    }

    public List<Usuario> listar() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Usuario.class);
        return (List<Usuario>) criteria.list();
    }

}
