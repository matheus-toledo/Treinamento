package com.docnix.entity;

import javax.persistence.*;

@Entity
@Table(name = "TRE_MATERIA")
public class Materia{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_materia",nullable = false)
    private long id;

    @Column(name = "nome_materia",nullable = false)
    private String nome;

    @Column(name = "descricao_materia",nullable = false,length = 2500)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_usuario",foreignKey = @ForeignKey(name = "id_usuario_fk"),nullable = false)
    private Usuario professor;

    public Materia(long id, String nome, String descricao, Usuario professor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.professor = professor;
    }

    public Materia() {
    }

    public long getId() {
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
        return ((Long)this.id).hashCode();
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
