package com.amploview.program.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity()
@Table(name = "Atributos",uniqueConstraints= @UniqueConstraint(columnNames = {"descricao", "elementos_id"}) )
public class Atributos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "elementos_id")
	private Elementos elemento;
	
	public Atributos() {
		//super();
	}

	public Atributos(Integer id, String descricao, Elementos elemento) {
		//super();
		this.id = id;
		this.descricao = descricao;
		this.elemento = elemento;
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

	public Elementos getGrupo() {
		return elemento;
	}

	public void setGrupo(Elementos elemento) {
		this.elemento = elemento;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((elemento == null) ? 0 : elemento.hashCode());
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
		Atributos other = (Atributos) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (elemento == null) {
			if (other.elemento != null)
				return false;
		} else if (!elemento.equals(other.elemento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Atributos [id=" + id + ", descricao=" + descricao + ", elemento=" + elemento + "]";
	}
	
}
