package com.docnix.entity;

import javax.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TRE_CURSO")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome_curso", nullable = false)
    private String nome;

    @Column(name = "sigla_curso", nullable = false,length = 5)
    private String sigla;

    @ManyToOne
    @JoinColumn(name = "id_coordenador_curso", nullable = false)
    private Usuario coordenador;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TRE_MATERIA_CURSO",
            joinColumns = @JoinColumn(name = "id_curso"),
            inverseJoinColumns = @JoinColumn(name = "id_materia"))
    private Set<Materia> materias;

    @Column(name = "descricao_curso", nullable = false, length = 2500)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_escola_curso",nullable = false)
    private Escola escola;

    @Transient
    private List<Long> materiasIds;

    public Curso(String nome, String sigla, Usuario coordenador, Set<Materia> materias, String descricao, Escola escola) {
        this.nome = nome;
        this.sigla = sigla;
        this.coordenador = coordenador;
        this.materias = materias;
        this.descricao = descricao;
        this.escola = escola;
    }

    public Curso() {
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Usuario getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Usuario coordenador) {
        this.coordenador = coordenador;
    }

    public Set<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(Set<Materia> materias) {
        this.materias = materias;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public List<Long> getMateriasIds() {
        return materiasIds;
    }

    public void setMateriasIds(List<Long> materiasId) {
        this.materiasIds = materiasId;
    }
}
