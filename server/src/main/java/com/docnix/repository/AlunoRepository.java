package com.docnix.repository;

import com.docnix.config.HibernateConfig;
import com.docnix.entity.Aluno;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AlunoRepository extends BaseRepository<Aluno> {

    public AlunoRepository() {
        super(Aluno.class);
    }

    public List<Aluno> listar() {
        this.session = HibernateConfig.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(getTClass(), "bean");
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("bean.id").as("id"))
                .add(Projections.property("bean.nome").as("nome"))
                .add(Projections.property("bean.email").as("email"))
                .add(Projections.property("bean.idade").as("idade"))
                .add(Projections.property("bean.dataDaMatricula").as("dataDaMatricula"))
                .add(Projections.property("bean.sequencia").as("sequencia"))
                .add(Projections.property("bean.matricula").as("matricula"));

        criteria.setProjection(Projections.distinct(projectionList))
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);


        List<Aluno> alunos = new ArrayList<>();

        criteria.list().forEach(elem -> alunos.add(alunoBuilder((Map<String, Object>) elem)));

        return alunos;


    }

    private Aluno alunoBuilder(Map<String, Object> elem) {
        Aluno aluno = new Aluno();

        aluno.setId((Long) elem.get("id"));
        aluno.setNome((String) elem.get("nome"));
        aluno.setEmail((String) elem.get("email"));
        aluno.setIdade((Integer) elem.get("idade"));
        aluno.setMatricula((String) elem.get("matricula"));
        aluno.setDataDaMatricula((Date) elem.get("dataDaMatricula"));
        aluno.setSequencia((Long) elem.get("sequencia"));
        return aluno;
    }

}
