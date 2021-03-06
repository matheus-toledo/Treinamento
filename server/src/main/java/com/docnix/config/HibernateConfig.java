package com.docnix.config;

import com.docnix.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {

    private static SessionFactory sessionFactory;

    public static void initSessionFactory(){
        if(sessionFactory != null){
            System.out.println("SESSÃO JÁ INICIADA");
            return;
        }

        Configuration config = new Configuration();
        config.configure();
        config.addAnnotatedClass(Aluno.class);
        config.addAnnotatedClass(Usuario.class);
        config.addAnnotatedClass(Escola.class);
        config.addAnnotatedClass(Materia.class);
        config.addAnnotatedClass(Curso.class);
        config.addAnnotatedClass(Turma.class);
        sessionFactory = config.buildSessionFactory();
    }

    public static void endSessionFactory(){
        if (sessionFactory!=null){
            System.out.println("FINALIZANDO SESSÃO");
            sessionFactory.close();
        }
    }

    public static SessionFactory getSessionFactory (){
        return  sessionFactory;
    }

}
