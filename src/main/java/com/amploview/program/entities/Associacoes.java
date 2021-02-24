package com.amploview.program.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Associacao",uniqueConstraints= @UniqueConstraint(columnNames = {"colecao", "item", "tipo" }) )
public class Associacoes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer colecao;
	private Integer item;
	private Integer tipo;
	
	public Associacoes() {
		//super();
	}

	public Associacoes(Integer id, Integer colecao, Integer item, Integer tipo) {
		//super();
		this.id = id;
		this.colecao = colecao;
		this.item = item;
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getColecao() {
		return colecao;
	}

	public void setColecao(Integer colecao) {
		this.colecao = colecao;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
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
		Associacoes other = (Associacoes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Associacoes [id=" + id + ", colecao=" + colecao + ", item=" + item + ", tipo=" + tipo + "]";
	}
	
	/*
	@Override
	public String toString() {
		String descricaoColecao = null, descricaoItem = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
		EntityManager em = emf.createEntityManager();
		TypedQuery<String> query = em.createQuery("Select descricao from Colecoes Where id = " + colecao, String.class);
		List<String> list=((javax.persistence.Query) query).getResultList();
		if (!list.isEmpty()) {
			descricaoColecao = list.get(0).toString();
		}
		query = em.createQuery("Select descricao from " + tipo + " Where id = '" + item + "'" , String.class);
		if (!list.isEmpty()) {
			descricaoItem = list.get(0).toString();
		}
		em.close();
		emf.close();
		if (descricaoColecao!=null && descricaoItem!=null) {
			return "Associacoes [id=" + id + ", colecao=" + descricaoColecao + ", item=" + descricaoItem
					+ ", tipo=" + tipo + "]";
		} else {
			return null;
		}
	}
	*/
}
