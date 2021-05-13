package com.docnix.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "TRE_ALUNO")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_aluno")
    private long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "idade", nullable = false)
    private Integer idade;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_matricula", nullable = false)
    private Date dataDaMatricula;  //ISO 8601

    @ManyToOne
    @JoinColumn(name = "id_turma")
    @JsonBackReference
    private Turma turma;

    @Column(name = "sequencia_turma")
    private Long sequencia;

    @Transient
    private String matricula;

    public Aluno() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMatricula() {
        if (turma != null) {
            return turma.getSigla() + " - " + sequencia;
        }
        return null;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getDataDaMatricula() {
        return dataDaMatricula;
    }

    public void setDataDaMatricula(Date dataDaMatricula) {
        this.dataDaMatricula = dataDaMatricula;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Long getSequencia() {
        return sequencia;
    }

    public void setSequencia(Long sequencia) {
        this.sequencia = sequencia;
    }

}
