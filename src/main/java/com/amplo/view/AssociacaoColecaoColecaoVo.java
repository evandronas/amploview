package com.amplo.view;

/**
 * @author Evandro
 */
public class AssociacaoColecaoColecaoVo {

	private String Id;
	private String Id_ColecaoPai;
	private String Descricao_ColecaoPai;
	private String Id_ColecaoFilho;
	private String Descricao_ColecaoFilho;
	private String Id_Site;
	private String Descricao_Site;

	public String getId() {return Id==null?"":Id;}
	public String getId_ColecaoPai() {return Id_ColecaoPai==null?"":Id_ColecaoPai;}
	public String getDescricao_ColecaoPai() {return Descricao_ColecaoPai==null?"":Descricao_ColecaoPai;}
	public String getId_ColecaoFilho() {return Id_ColecaoFilho==null?"":Id_ColecaoFilho;}
	public String getDescricao_ColecaoFilho() {return Descricao_ColecaoFilho==null?"":Descricao_ColecaoFilho;}
	public String getId_Site() {return Id_Site==null?"":Id_Site;}
	public String getDescricao_Site() {return Descricao_Site==null?"":Descricao_Site;}

	public boolean isNullId() {return Id==null;}
	public boolean isNullId_ColecaoPai() {return Id_ColecaoPai==null;}
	public boolean isNullDescricao_ColecaoPai() {return Descricao_ColecaoPai==null;}
	public boolean isNullId_ColecaoFilho() {return Id_ColecaoFilho==null;}
	public boolean isNullDescricao_ColecaoFilho() {return Descricao_ColecaoFilho==null;}
	public boolean isNullId_Site() {return Id_Site==null;}
	public boolean isNullDescricao_Site() {return Descricao_Site==null;}
	
	public void setId(String pId) {this.Id=pId;}
	public void setId_ColecaoPai(String pId_ColecaoPai) {this.Id_ColecaoPai=pId_ColecaoPai;}
	public void setDescricao_ColecaoPai(String pDescricao_ColecaoPai) {this.Descricao_ColecaoPai=pDescricao_ColecaoPai;}
	public void setId_ColecaoFilho(String pId_ColecaoFilho) {this.Id_ColecaoFilho=pId_ColecaoFilho;}
	public void setDescricao_ColecaoFilho(String pDescricao_ColecaoFilho) {this.Descricao_ColecaoFilho=pDescricao_ColecaoFilho;}
	public void setId_Site(String pId_Site) {this.Id_Site=pId_Site;}
	public void setDescricao_Site(String pDescricao_Site) {this.Descricao_Site=pDescricao_Site;}
	
}
