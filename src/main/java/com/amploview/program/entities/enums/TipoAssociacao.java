package com.amploview.program.entities.enums;

public enum TipoAssociacao {

	Grupos(1),
	Colecoes(2);

	private final Integer tipo;
	
	TipoAssociacao(Integer tipo) {
		this.tipo = tipo;
	}
	
	public Integer getTipo(){
		return tipo;
	}
}
