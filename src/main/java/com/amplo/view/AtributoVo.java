package com.amplo.view;

/**
 * @author Evandro
 */
public class AtributoVo {

	private String Id;
	private String Nome;
	private String Id_Elemento;

	public String getId() {return Id==null?"":Id;}
	public String getNome() {return Nome==null?"":Nome;}
	public String getId_Elemento() {return Id_Elemento==null?"":Id_Elemento;}

	public boolean isNullId() {return Id==null;}
	public boolean isNullNome() {return Nome==null;}
	public boolean isNullId_Elemento() {return Id_Elemento==null;}
	
	public void setId(String pId) {this.Id=pId;}
	public void setNome(String pNome) {this.Nome=pNome;}
	public void setId_Elemento(String pId_Elemento) {this.Id_Elemento=pId_Elemento;}
	
}
