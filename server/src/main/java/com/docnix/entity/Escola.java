package com.docnix.entity;

import javax.persistence.*;

@Entity
@Table(name = "TRE_ESCOLA")
public class Escola {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_escola",nullable = false)
    private long id;

    @Column(name = "nome_escola",nullable = false, length = 255)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "id_usuario_fk"),nullable = false)
    private Usuario diretor;

    @Column(name = "descricao_escola",nullable = false, length = 300)
    private String descricao;

    @Column(name = "is_escola_ativa",nullable = false)
    private boolean ativa;


    public Escola(long id, String nome, Usuario diretor, String descricao, boolean ativa) {
        this.id = id;
        this.nome = nome;
        this.diretor = diretor;
        this.descricao = descricao;
        this.ativa = ativa;
    }

    public Escola(){

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

    public Usuario getDiretor() {
        return diretor;
    }

    public void setDiretor(Usuario diretor) {
        this.diretor = diretor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }


}
