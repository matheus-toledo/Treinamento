package com.docnix.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

import java.util.HashSet;
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

    @OneToMany(mappedBy = "turma")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JsonManagedReference
    private Set<Aluno> alunos = new HashSet<>();

    public Turma() {
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
}
