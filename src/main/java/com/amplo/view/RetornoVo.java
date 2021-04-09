package com.amplo.view;

/**
 * @author Evandro
 */
public class RetornoVo {

	private String Code; //https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
						//200 - Sucesso
						//400 - Bad Request
	
	private String Descricao;
	private String JsonOuTxtComplemento;

	public String getCode() {return Code==null?"":Code;}
	public String getDescricao() {return Descricao==null?"":Descricao;}
	public String getJsonOuTxtComplemento() {return JsonOuTxtComplemento==null?"":JsonOuTxtComplemento;}

	public boolean isNullCode() {return Code==null;}
	public boolean isNullDescricao() {return Descricao==null;}
	public boolean isNullJsonOuTxtComplemento() {return JsonOuTxtComplemento==null;}
	
	public void setCode(String pCode) {this.Code=pCode;}
	public void setDescricao(String pDescricao) {this.Descricao=pDescricao;}
	public void setJsonOuTxtComplemento(String pJsonOuTxtComplemento) {this.JsonOuTxtComplemento=pJsonOuTxtComplemento;}
}
