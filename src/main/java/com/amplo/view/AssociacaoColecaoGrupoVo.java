package com.amplo.view;

/**
 * @author Evandro
 */
public class AssociacaoColecaoGrupoVo {

	private String Id;
	private String Id_Colecao;
	private String Descricao_Colecao;
	private String Id_Grupo;
	private String Descricao_Grupo;
	private String Id_Site;
	private String Descricao_Site;

	public String getId() {return Id==null?"":Id;}
	public String getId_Colecao() {return Id_Colecao==null?"":Id_Colecao;}
	public String getDescricao_Colecao() {return Descricao_Colecao==null?"":Descricao_Colecao;}
	public String getId_Grupo() {return Id_Grupo==null?"":Id_Grupo;}
	public String getDescricao_Grupo() {return Descricao_Grupo==null?"":Descricao_Grupo;}
	public String getId_Site() {return Id_Site==null?"":Id_Site;}
	public String getDescricao_Site() {return Descricao_Site==null?"":Descricao_Site;}

	public boolean isNullId() {return Id==null;}
	public boolean isNullId_Colecao() {return Id_Colecao==null;}
	public boolean isNullDescricao_Colecao() {return Descricao_Colecao==null;}
	public boolean isNullId_Grupo() {return Id_Grupo==null;}
	public boolean isNullDescricao_Grupo() {return Descricao_Grupo==null;}
	public boolean isNullId_Site() {return Id_Site==null;}
	public boolean isNullDescricao_Site() {return Descricao_Site==null;}
	
	public void setId(String pId) {this.Id=pId;}
	public void setId_Colecao(String pId_Colecao) {this.Id_Colecao=pId_Colecao;}
	public void setDescricao_Colecao(String pDescricao_Colecao) {this.Descricao_Colecao=pDescricao_Colecao;}
	public void setId_Grupo(String pId_Grupo) {this.Id_Grupo=pId_Grupo;}
	public void setDescricao_Grupo(String pDescricao_Grupo) {this.Descricao_Grupo=pDescricao_Grupo;}
	public void setId_Site(String pId_Site) {this.Id_Site=pId_Site;}
	public void setDescricao_Site(String pDescricao_Site) {this.Descricao_Site=pDescricao_Site;}
	
}
