package com.amplo.view;

/**
 * @author Evandro
 */
public class ValorVo {

	private String Id;
	private String Valor;
	private String Id_Atributo;

	public String getId() {return Id==null?"":Id;}
	public String getValor() {return Valor==null?"":Valor;}
	public String getId_Atributo() {return Id_Atributo==null?"":Id_Atributo;}

	public boolean isNullId() {return Id==null;}
	public boolean isNullValor() {return Valor==null;}
	public boolean isNullId_Atributo() {return Id_Atributo==null;}
	
	public void setId(String pId) {this.Id=pId;}
	public void setValor(String pValor) {this.Valor=pValor;}
	public void setId_Atributo(String pId_Atributo) {this.Id_Atributo=pId_Atributo;}
	
}
