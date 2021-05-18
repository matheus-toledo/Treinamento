package com.docnix.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.util.List;
import java.util.Set;
@Entity
@Table(name = "TRE_TURMA")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_turma")
    private Long id;

    @Column(name = "nome",nullable = false)
    private String nome;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "sequencia")
    private Long sequencia;

    @Column(name ="sigla")
    private String sigla;

    @ManyToOne
    @JoinColumn(name = "id_turma_curso",nullable = false)
    private Curso curso;

    @ManyToMany(targetEntity = Aluno.class, fetch = FetchType.LAZY)
    @JoinTable(name = "TRE_TURMA_ALUNO", joinColumns = @JoinColumn(name = "id_turma"), inverseJoinColumns = @JoinColumn(name = "id_aluno"),uniqueConstraints = @UniqueConstraint(columnNames = {"id_aluno"}))
    private Set<Aluno> alunos;

    @Transient
    private List<Long> alunosIds;

    public Turma() {
    }

    public Turma(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Long getSequencia() {
        return sequencia;
    }

    public void setSequencia(Long sequencia) {
        this.sequencia = sequencia;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<Long> getAlunosIds() {
        return alunosIds;
    }

    public void setAlunosIds(List<Long> alunosIds) {
        this.alunosIds = alunosIds;
    }
}
