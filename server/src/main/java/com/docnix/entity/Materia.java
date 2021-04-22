package com.docnix.entity;

import javax.persistence.*;

@Entity
@Table(name = "TRE_MATERIA")
public class Materia{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_materia",nullable = false)
    private Long id;

    @Column(name = "nome_materia",nullable = false)
    private String nome;

    @Column(name = "descricao_materia",nullable = false,length = 2500)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_professor",nullable = false)
    private Usuario professor;

    public Materia(String nome, String descricao, Usuario professor) {
        this.nome = nome;
        this.descricao = descricao;
        this.professor = professor;
    }

    public Materia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    @Override
    public int hashCode() {
        if(this.id !=null){
            return this.id.hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(!(obj instanceof Materia)){
            return false;
        }
        if (obj==this){
            return true;
        }
        return this.getId() == ((Materia) obj).getId();
    }

}
