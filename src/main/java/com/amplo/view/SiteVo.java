package com.amplo.view;

/**
 * @author Evandro
 */
public class SiteVo {

	private String Id;
	private String Descricao;

	public String getId() {return Id==null?"":Id;}
	public String getDescricao() {return Descricao==null?"":Descricao;}

	public boolean isNullId() {return Id==null;}
	public boolean isNullDescricao() {return Descricao==null;}
	
	public void setId(String pId) {this.Id=pId;}
	public void setDescricao(String pDescricao) {this.Descricao=pDescricao;}
	
}
