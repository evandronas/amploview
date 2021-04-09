package com.amplo.view;

/**
 * @author Evandro
 */
public class GrupoVo {

	private String Id;
	private String Descricao;
	private String Id_Site;

	public String getId() {return Id==null?"":Id;}
	public String getDescricao() {return Descricao==null?"":Descricao;}
	public String getId_Site() {return Id_Site==null?"":Id_Site;}

	public boolean isNullId() {return Id==null;}
	public boolean isNullDescricao() {return Descricao==null;}
	public boolean isNullId_Site() {return Id_Site==null;}
	
	public void setId(String pId) {this.Id=pId;}
	public void setDescricao(String pDescricao) {this.Descricao=pDescricao;}
	public void setId_Site(String pId_Site) {this.Id_Site=pId_Site;}
	
}
