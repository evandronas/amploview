package com.amploview.program.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Colecoes",uniqueConstraints= @UniqueConstraint(columnNames = {"descricao"}) )
public class Colecoes implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "AssociacoesGrupos" , 
	joinColumns = @JoinColumn(name = "Colecao"),
	inverseJoinColumns = @JoinColumn(name = "Grupo_Associado"))
	private Set<Grupos> grupos = new HashSet<>();

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "AssociacoesColecoes" , 
	joinColumns = @JoinColumn(name = "Colecao"),
	inverseJoinColumns = @JoinColumn(name = "Colecao_associada"))
	private Set<Colecoes> colecoes = new HashSet<>();

	@ManyToMany(mappedBy = "colecoes")
	private Set<Colecoes> colecoes_relacionadas = new HashSet<>();
	
	public Colecoes() {
		//super();
	}

	public Colecoes(Integer id, String descricao) {
		//super();
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Grupos> getGrupos() {
		return grupos;
	}

	public Set<Colecoes> getColecoes() {
		return colecoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Colecoes other = (Colecoes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Colecoes [id=" + id + ", descricao=" + descricao + "]";
	}

}
