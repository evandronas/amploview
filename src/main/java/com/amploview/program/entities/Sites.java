package com.amploview.program.entities;

import java.io.Serializable;

public class Sites implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String Descricao;
	
	public Sites() {
		super();
	}

	public Sites(Integer id, String descricao) {
		super();
		this.id = id;
		Descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Descricao == null) ? 0 : Descricao.hashCode());
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
		Sites other = (Sites) obj;
		if (Descricao == null) {
			if (other.Descricao != null)
				return false;
		} else if (!Descricao.equals(other.Descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sites [id=" + id + ", Descricao=" + Descricao + "]";
	}

}
