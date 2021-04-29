package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import org.hibernate.Session;

public abstract class BaseRepository<T> {

    protected Session session;
    private final Class<T> tClass;

    public BaseRepository(Class<T> tClass) {
        this.tClass = tClass;
    }

    public Class<T> getTClass() {
        return tClass;
    }

    public T salvar(T object) {
        this.session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        session.close();
        return object;
    }

    public T obter(Long id) {
        this.session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        T object = session.get(getTClass(), id);
        session.getTransaction().commit();
        session.close();
        return object;
    }

    public T editar(T objectSave) {
        this.session = HibernateConfig.getSessionFactory().openSession();
        this.session.beginTransaction();
        T result = (T) session.merge(objectSave);
        session.getTransaction().commit();
        session.close();
        return result;

    }

    public void deletar(Long id) {
        Object object = obter(id);
        this.session = HibernateConfig.getSessionFactory().openSession();
        this.session.beginTransaction();
        if (object != null) {
            session.delete(object);
        }
        session.getTransaction().commit();
        session.close();
    }

}
