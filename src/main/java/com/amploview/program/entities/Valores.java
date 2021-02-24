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
@Table(name = "Valores",uniqueConstraints= @UniqueConstraint(columnNames = {"descricao", "atributos_id"}) )
public class Valores implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "atributos_id")
	private Atributos atributo;
	
	public Valores() {
		//super();
	}

	public Valores(Integer id, String descricao, Atributos atributo) {
		//super();
		this.id = id;
		this.descricao = descricao;
		this.atributo = atributo;
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

	public Atributos getGrupo() {
		return atributo;
	}

	public void setGrupo(Atributos atributo) {
		this.atributo = atributo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((atributo == null) ? 0 : atributo.hashCode());
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
		Valores other = (Valores) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (atributo == null) {
			if (other.atributo != null)
				return false;
		} else if (!atributo.equals(other.atributo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Valores [id=" + id + ", descricao=" + descricao + ", atributo=" + atributo + "]";
	}
	
}
