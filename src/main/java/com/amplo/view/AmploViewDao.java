package com.amplo.view;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.amplo.view.DbException;

/**
 * @author Evandro
 */
public class AmploViewDao extends Dao {

	public Connection connection = null;

    final Logger LOGGER = LogManager.getLogger(AmploViewDao.class);

    private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	AmploViewDao(String banco) throws ClassNotFoundException, NamingException, SQLException {
		LOGGER.info("Efetuando conexão ao banco " + banco + "..." );
		try {
			Properties props = loadProperties();
			String url = props.getProperty("dburl");
			connection = DriverManager.getConnection(url, props);
		} catch (Exception e) {
			
			LOGGER.error("Erro ao conectar com o banco de dados", e);
			throw e;
		}
		LOGGER.info("Conectado ao banco " + banco + "!" );
	}
	
	public Connection getConnection()
	{
		return connection;
	}
	
	void Finalizar() throws ClassNotFoundException, NamingException, SQLException {
		try {
			if(connection != null){
				LOGGER.info("Encerrando conexão com banco..." );
				connection.close();
				LOGGER.info("Encerado conexão com o banco!" );
			}
		} catch (Exception e) {
			
			LOGGER.error("Erro falha ao fechar a conexão", e);				
			throw e;
		}
	}
	
	/**
	 * Metodo para inserir Sites
	 * @throws Exception 
	 */
	public void insertSite(String Descricao) throws Exception{
		
		LOGGER.info("### Inicio do Insert do Site " + Descricao);
		try {
			String insert_Site;
			if (Descricao==null) {
				insert_Site = "Insert into Sites  (Descricao) values (" + Descricao + ")";
			} else {
				insert_Site = "Insert into Sites  (Descricao) values ('" + Descricao + "')";
			}
			Statement stInsert_Site = null;
			stInsert_Site = connection.createStatement();
			stInsert_Site.executeUpdate(insert_Site);
			stInsert_Site.close();
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um insert na tabela de Sites, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um insert na tabela de Sites!", e);
			}
			throw e;
		}
	}

	/**
	 * Metodo para select Sites
	 * @throws Exception 
	 */
	public String selectSite(String Descricao_site) throws Exception{
	
		String retorno = null;
		ArrayList<SiteVo> voSite = null;
		LOGGER.info("### Inicio do Select do Site " + Descricao_site);
		try {
			if (Descricao_site == null) {
				Descricao_site = "";
			}
			voSite = ConsultaSites("",Descricao_site);
			if (voSite!=null) {
				Gson gson = new Gson();
				retorno = gson.toJson(voSite);
				retorno += "\n";
			}
		}catch (Exception e) {		
			LOGGER.error("Erro não previsto ao tentar executar um select na tabela de Grupos!", e);
			throw e;
		}
		return retorno;
	}
	
	/**
	 * Metodo para update Sites
	 * @throws Exception 
	 */
	public void updateSite(String Descricao, String Descricao_to) throws Exception{
		
		LOGGER.info("### Inicio do Update do Site " + Descricao + " para " + Descricao_to);
		try {
			String update_Site;
			if (Descricao_to==null) {
				update_Site = "Update Sites set Descricao = " + Descricao_to + " where Descricao = '" + Descricao + "'";
			} else {
				update_Site = "Update Sites set Descricao = '" + Descricao_to + "' where Descricao = '" + Descricao + "'";
			}
			Statement stUpdate_Site = null;
			stUpdate_Site = connection.createStatement();
			int rowsaffected = stUpdate_Site.executeUpdate(update_Site);
			if (rowsaffected<=0) {
				LOGGER.error("Falha ao tentar executar um update na tabela de Sites, não econtrado nehum registro para alterar!");
				throw new Exception("Falha ao tentar executar um update na tabela de Sites, não econtrado nehum registro para alterar!");
			}
			stUpdate_Site.close();
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um update na tabela de Sites, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um update na tabela de Sites!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para delete Sites
	 * @throws Exception 
	 */
	public void deleteSite(String Descricao) throws Exception{
		
		LOGGER.info("### Inicio do Delete do Site " + Descricao);
		try {
			String delete_Site = "Delete from Sites where Descricao = '" + Descricao + "'";
			Statement stDelete_Site = null;
			stDelete_Site = connection.createStatement();
			int rowsaffected = stDelete_Site.executeUpdate(delete_Site);
			if (rowsaffected<=0) {
				LOGGER.error("Falha ao tentar executar um delete na tabela de Sites, não econtrado nehum registro para excluir!");
				throw new Exception("Falha ao tentar executar um delete na tabela de Sites, não econtrado nehum registro para excluir!");
			}
			stDelete_Site.close();
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um delete na tabela de Sites, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um delete na tabela de Sites!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para clean Sites
	 * @throws Exception 
	 */
	public void cleanSite(String Descricao) throws Exception{
		
		LOGGER.info("### Inicio do Clean do Site " + Descricao);
		//tem que mudar logica para fazer um delete cascate
		try {
			String Id_Site = "";
			ArrayList<SiteVo> voSite = ConsultaSites("", Descricao);
			ArrayList<GrupoVo> voGrupo;
			ArrayList<ElementoVo> voElemento;
			ArrayList<AtributoVo> voAtributo;
			for (int i=0;i<voSite.size();i++) {
				Id_Site = voSite.get(i).getId();
				if (!Id_Site.contentEquals("")) {
					String Id_Grupo = "";
					voGrupo = ConsultaGrupos("", "", Id_Site);
					for (int j=0;j<voGrupo.size();j++) {
						Id_Grupo = voGrupo.get(j).getId();
						if (!Id_Grupo.contentEquals("")) {
							String Id_Elemento = "";
							voElemento = ConsultaElementos("", "", Id_Grupo, Id_Site);
							for (int k=0;k<voElemento.size();k++) {
								Id_Elemento = voElemento.get(k).getId();
								if (!Id_Elemento.contentEquals("")) {
									String Id_Atributo = "";
									voAtributo = ConsultaAtributos("", "", Id_Elemento);
									for (int l=0;i<voAtributo.size();l++) {
										Id_Atributo = voAtributo.get(l).getId();
										if (!Id_Atributo.contentEquals("")) {
											String delete_Valor = "Delete from Valores where Id_Atributo = " + Id_Atributo;
											Statement stDelete_Valor = null;
											stDelete_Valor = connection.createStatement();
											int rowsaffected = stDelete_Valor.executeUpdate(delete_Valor);
											stDelete_Valor.close();
											if (rowsaffected == 0) {
												break;
											}
										}
									}
									String delete_Atributo = "Delete from Atributos where Id_Elemento = " + Id_Elemento;
									Statement stDelete_Atributo = null;
									stDelete_Atributo = connection.createStatement();
									int rowsaffected = stDelete_Atributo.executeUpdate(delete_Atributo);
									stDelete_Atributo.close();
								}
							}
							String delete_Elemento = "Delete from Elementos where Id_Grupo = " + Id_Grupo + " and Id_Site = " + Id_Site;
							Statement stDelete_Elemento = null;
							stDelete_Elemento = connection.createStatement();
							int rowsaffected = stDelete_Elemento.executeUpdate(delete_Elemento);
							stDelete_Elemento.close();
						}
					}
				}
				String delete_Grupo = "Delete from Grupos where Id_Site = " + Id_Site;
				Statement stDelete_Grupo = null;
				stDelete_Grupo = connection.createStatement();
				int rowsaffected = stDelete_Grupo.executeUpdate(delete_Grupo);
				stDelete_Grupo.close();
			}
			String clean_Site = "Delete from Sites where Descricao = '" + Descricao + "'";
			Statement stClean_Site = null;
			stClean_Site = connection.createStatement();
			int rowsaffected = stClean_Site.executeUpdate(clean_Site);
			if (rowsaffected<=0) {
				LOGGER.error("Falha ao tentar executar um clean na tabela de Sites, não econtrado nehum registro para limpar!");
				throw new Exception("Falha ao tentar executar um clean na tabela de Sites, não econtrado nehum registro para limpar!");
			}
			stClean_Site.close();
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um clean na tabela de Sites, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um clean na tabela de Sites!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para inserir Grupos
	 * @throws Exception 
	 */
	public void insertGrupo(String Descricao, String Descricao_Site) throws Exception{
		
		LOGGER.info("### Inicio do Insert do Grupo/Site " + Descricao + "/" + Descricao_Site);
		try {
			String Id_Site = "";
			ArrayList<SiteVo> vo = ConsultaSites("", Descricao_Site);
			for (int i=0;i<vo.size();i++) {
				Id_Site = vo.get(i).getId();
				break;
			}
			if (!Id_Site.contentEquals("")) {
				String insert_Grupo;
				if (Descricao==null) {
					insert_Grupo = "Insert into Grupos  (Descricao, Id_Site) values (" + Descricao + "," + Id_Site + ")";
				} else {
					insert_Grupo = "Insert into Grupos  (Descricao, Id_Site) values ('" + Descricao + "'," + Id_Site + ")";
				}
				Statement stInsert_Grupo = null;
				stInsert_Grupo = connection.createStatement();
				stInsert_Grupo.executeUpdate(insert_Grupo);
				stInsert_Grupo.close();
			} else {
				LOGGER.error("Falha ao tentar efetuar insert na tabela de Grupos, Id_Site não loalizado!");
				throw new Exception("Falha ao tentar efetuar insert na tabela de Grupos, Id_Site não loalizado!");
			}
		}catch (Exception e) {
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um insert na tabela de Grupos, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um insert na tabela de Grupos!", e);
			}
			throw e;
		}
	}

	/**
	 * Metodo para select Grupos
	 * @throws Exception 
	 */
	public String selectGrupo(String Descricao_grupo, String Descricao_site) throws Exception{
	
		String retorno = null;
		ArrayList<GrupoVo> voGrupo = null;
		LOGGER.info("### Inicio do Select do Grupo " + Descricao_grupo);
		try {
			if (Descricao_grupo == null) {
				Descricao_grupo = "";
			}
			ArrayList<SiteVo> voSite = ConsultaSites("",Descricao_site);
			if (voSite!=null) {
				for(int i=0;i<voSite.size();i++) {
					voGrupo = ConsultaGrupos("",Descricao_grupo,voSite.get(i).getId());
					if (voGrupo!=null) {
						Gson gson = new Gson();
						retorno = retorno==null?"":retorno;
						retorno += gson.toJson(voGrupo);
						retorno += "\n";
					}
				}
			}
		}catch (Exception e) {		
			LOGGER.error("Erro não previsto ao tentar executar um select na tabela de Grupos!", e);
			throw e;
		}
		return retorno;
	}
	
	/**
	 * Metodo para update Grupos
	 * @throws Exception 
	 */
	public void updateGrupo(String Descricao_grupo, String Descricao_site, String Descricao_grupo_to) throws Exception{
		
		LOGGER.info("### Inicio do Update do Grupo " + Descricao_grupo + " para " + Descricao_grupo_to);
		try {
			String update_Grupo;
			if (Descricao_grupo_to==null) {
				update_Grupo = "Update Grupos set Descricao = " + Descricao_grupo_to + " where Descricao = '" + Descricao_grupo + "'";
			} else {
				update_Grupo = "Update Grupos set Descricao = '" + Descricao_grupo_to + "' where Descricao = '" + Descricao_grupo + "'";
			}
			Statement stUpdate_Grupo = null;
			stUpdate_Grupo = connection.createStatement();
			int rowsaffected = stUpdate_Grupo.executeUpdate(update_Grupo);
			if (rowsaffected<=0) {
				LOGGER.error("Falha ao tentar executar um update na tabela de Grupos, não econtrado nehum registro para alterar!");
				throw new Exception("Falha ao tentar executar um update na tabela de Grupos, não econtrado nehum registro para alterar!");
			}
			stUpdate_Grupo.close();
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um update na tabela de Grupos, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um update na tabela de Grupos!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para delete Grupos
	 * @throws Exception 
	 */
	public void deleteGrupo(String Descricao_grupo, String Descricao_site) throws Exception{
		
		LOGGER.info("### Inicio do Delete do Grupo " + Descricao_grupo);
		try {
			String delete_Grupo = "Delete from Grupos where Descricao = '" + Descricao_grupo + "'";
			Statement stDelete_Grupo = null;
			stDelete_Grupo = connection.createStatement();
			int rowsaffected = stDelete_Grupo.executeUpdate(delete_Grupo);
			if (rowsaffected<=0) {
				LOGGER.error("Falha ao tentar executar um delete na tabela de Grupos, não econtrado nehum registro para excluir!");
				throw new Exception("Falha ao tentar executar um delete na tabela de Grupos, não econtrado nehum registro para excluir!");
			}
			stDelete_Grupo.close();
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um delete na tabela de Grupos, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um delete na tabela de Grupos!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para clean Grupos
	 * @throws Exception 
	 */
	public void cleanGrupo(String Descricao_grupo, String Descricao_site) throws Exception{
		
		LOGGER.info("### Inicio do Clean do Grupo " + Descricao_grupo);
		//tem que mudar logica para fazer um delete cascate
		try {
			String Id_Site = "";
			ArrayList<SiteVo> voSite = ConsultaSites("", Descricao_site);
			ArrayList<GrupoVo> voGrupo;
			ArrayList<ElementoVo> voElemento;
			ArrayList<AtributoVo> voAtributo;
			for (int i=0;i<voSite.size();i++) {
				Id_Site = voSite.get(i).getId();
				if (!Id_Site.contentEquals("")) {
					String Id_Grupo = "";
					voGrupo = ConsultaGrupos("", "", Id_Site);
					for (int j=0;i<voGrupo.size();j++) {
						Id_Grupo = voGrupo.get(j).getId();
						if (!Id_Grupo.contentEquals("")) {
							String Id_Elemento = "";
							voElemento = ConsultaElementos("", "", Id_Grupo, Id_Site);
							for (int k=0;k<voElemento.size();k++) {
								Id_Elemento = voElemento.get(k).getId();
								if (!Id_Elemento.contentEquals("")) {
									String Id_Atributo = "";
									voAtributo = ConsultaAtributos("", "", Id_Elemento);
									for (int l=0;l<voAtributo.size();l++) {
										Id_Atributo = voAtributo.get(l).getId();
										if (!Id_Atributo.contentEquals("")) {
											String delete_Valor = "Delete from Valores where Id_Atributo = " + Id_Atributo;
											Statement stDelete_Valor = null;
											stDelete_Valor = connection.createStatement();
											int rowsaffected = stDelete_Valor.executeUpdate(delete_Valor);
											stDelete_Valor.close();
											if (rowsaffected == 0) {
												break;
											}
										}
									}
									String delete_Atributo = "Delete from Atributos where Id_Elemento = " + Id_Elemento;
									Statement stDelete_Atributo = null;
									stDelete_Atributo = connection.createStatement();
									int rowsaffected = stDelete_Atributo.executeUpdate(delete_Atributo);
									stDelete_Atributo.close();
									if (rowsaffected == 0) {
										break;
									}
								}
							}
							String delete_Elemento = "Delete from Elementos where Id_Grupo = " + Id_Grupo;
							Statement stDelete_Elemento = null;
							stDelete_Elemento = connection.createStatement();
							int rowsaffected = stDelete_Elemento.executeUpdate(delete_Elemento);
							stDelete_Elemento.close();
							if (rowsaffected == 0) {
								break;
							}
						}
					}
				}
				String clean_Grupo = "Delete from Grupos where Descricao = '" + Descricao_grupo + "'";
				Statement stClean_Grupo = null;
				stClean_Grupo = connection.createStatement();
				int rowsaffected = stClean_Grupo.executeUpdate(clean_Grupo);
				if (rowsaffected<=0) {
					LOGGER.error("Falha ao tentar executar um clean na tabela de Grupos, não econtrado nehum registro para limpar!");
					throw new Exception("Falha ao tentar executar um clean na tabela de Grupos, não econtrado nehum registro para limpar!");
				}
				stClean_Grupo.close();
			}
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um clean na tabela de Grupos, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um clean na tabela de Grupos!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para inserir Elementos
	 * @throws Exception 
	 */
	public void insertElemento(String Descricao, String Descricao_Grupo, String Descricao_Site) throws Exception{
		
		LOGGER.info("### Inicio do Insert do Elemento/Grupo/Site " + Descricao + "/" + Descricao_Grupo + "/" + Descricao_Site);
		try {
			String Id_Site = "";
			ArrayList<SiteVo> voSite = ConsultaSites("", Descricao_Site);
			for (int i=0;i<voSite.size();i++) {
				Id_Site = voSite.get(i).getId();
				break;
			}
			String Id_Grupo = "";
			ArrayList<GrupoVo> voGrupo = ConsultaGrupos("", Descricao_Grupo, Id_Site);
			for (int i=0;i<voGrupo.size();i++) {
				Id_Grupo = voGrupo.get(i).getId();
				break;
			}
			if (!Id_Grupo.contentEquals("") && !Id_Site.contentEquals("")) {
				String insert_Elemento;
				if (Descricao==null) {
					insert_Elemento = "Insert into Elementos  (Descricao, Id_grupo, Id_Site) values (" + Descricao + ", " + Id_Grupo + ", " + Id_Site + ")";
				} else {
					insert_Elemento = "Insert into Elementos  (Descricao, Id_grupo, Id_Site) values ('" + Descricao + "', " + Id_Grupo + ", " + Id_Site + ")";
				}
				Statement stInsert_Elemento = null;
				stInsert_Elemento = connection.createStatement();
				stInsert_Elemento.executeUpdate(insert_Elemento);
				stInsert_Elemento.close();
			} else {
				if (Id_Grupo.contentEquals("")) {
					LOGGER.error("Falha ao tentar efetuar insert na tabela de Elementos, Id_Grupo não loalizado!");
					throw new Exception("Falha ao tentar efetuar insert na tabela de Elementos, Id_Grupo não loalizado!");
				}
				if (Id_Site.contentEquals("")) {
					LOGGER.error("Falha ao tentar efetuar insert na tabela de Elementos, Id_Site não loalizado!");
					throw new Exception("Falha ao tentar efetuar insert na tabela de Elementos, Id_Site não loalizado!");
				}
			}
		}catch (Exception e) {
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um insert na tabela de Elementos, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um insert na tabela de Elementos!", e);
			}
			throw e;
		}
	}

	/**
	 * Metodo para select Elementos
	 * @throws Exception 
	 */
	public String selectElemento(String Descricao_elemento, String Descricao_grupo, String Descricao_site) throws Exception{
	
		String retorno = null;
		ArrayList<ElementoVo> voElemento = null;
		LOGGER.info("### Inicio do Select do Elemento " + Descricao_elemento);
		try {
			if (Descricao_elemento == null) {
				Descricao_elemento = "";
			}
			ArrayList<SiteVo> voSite = ConsultaSites("",Descricao_site);
			if (voSite!=null) {
				for(int i=0;i<voSite.size();i++) {
					ArrayList<GrupoVo> voGrupo = ConsultaGrupos("",Descricao_grupo,voSite.get(i).getId());
					if (voGrupo!=null) {
						for(int j=0;j<voGrupo.size();j++) {
							voElemento = ConsultaElementos("",Descricao_elemento,voGrupo.get(j).getId(),voSite.get(i).getId());
							if (voElemento!=null) {
								Gson gson = new Gson();
								retorno = retorno==null?"":retorno;
								retorno += gson.toJson(voElemento);
								retorno += "\n";
							}
						}
					}
				}
			}
		}catch (Exception e) {		
			LOGGER.error("Erro não previsto ao tentar executar um select na tabela de Elementos!", e);
			throw e;
		}
		return retorno;
	}
	
	/**
	 * Metodo para update Elementos
	 * @throws Exception 
	 */
	public void updateElemento(String Descricao_elemento, String Descricao_grupo, String Descricao_site, String Descricao_elemento_to) throws Exception{
		
		LOGGER.info("### Inicio do Update do Elemento " + Descricao_elemento + " para " + Descricao_elemento_to);
		try {
			String update_Elemento;
			if (Descricao_elemento_to==null) {
				update_Elemento = "Update Elementos set Descricao = " + Descricao_elemento_to + " where Descricao = '" + Descricao_elemento + "'";
			} else {
				update_Elemento = "Update Elementos set Descricao = '" + Descricao_elemento_to + "' where Descricao = '" + Descricao_elemento + "'";
			}
			Statement stUpdate_Elemento = null;
			stUpdate_Elemento = connection.createStatement();
			int rowsaffected = stUpdate_Elemento.executeUpdate(update_Elemento);
			if (rowsaffected<=0) {
				LOGGER.error("Falha ao tentar executar um update na tabela de Elementos, não econtrado nehum registro para alterar!");
				throw new Exception("Falha ao tentar executar um update na tabela de Elementos, não econtrado nehum registro para alterar!");
			}
			stUpdate_Elemento.close();
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um update na tabela de Elementos, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um update na tabela de Elementos!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para delete Elementos
	 * @throws Exception 
	 */
	public void deleteElemento(String Descricao_elemento, String Descricao_grupo, String Descricao_site) throws Exception{
		
		LOGGER.info("### Inicio do Delete do Elemento " + Descricao_elemento);
		try {
			String delete_Elemento = "Delete from Elementos where Descricao = '" + Descricao_elemento + "'";
			Statement stDelete_Elemento = null;
			stDelete_Elemento = connection.createStatement();
			int rowsaffected = stDelete_Elemento.executeUpdate(delete_Elemento);
			if (rowsaffected<=0) {
				LOGGER.error("Falha ao tentar executar um delete na tabela de Elementos, não econtrado nehum registro para excluir!");
				throw new Exception("Falha ao tentar executar um delete na tabela de Elementos, não econtrado nehum registro para excluir!");
			}
			stDelete_Elemento.close();
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um delete na tabela de Elementos, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um delete na tabela de Elementos!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para clean Elementos
	 * @throws Exception 
	 */
	public void cleanElemento(String Descricao_elemento, String Descricao_grupo, String Descricao_site) throws Exception{
		
		LOGGER.info("### Inicio do Clean do Elemento " + Descricao_elemento);
		//tem que mudar logica para fazer um delete cascate
		try {
			String Id_Site = "";
			ArrayList<SiteVo> voSite = ConsultaSites("", Descricao_site);
			ArrayList<GrupoVo> voGrupo;
			ArrayList<ElementoVo> voElemento;
			ArrayList<AtributoVo> voAtributo;
			for (int i=0;i<voSite.size();i++) {
				Id_Site = voSite.get(i).getId();
				if (!Id_Site.contentEquals("")) {
					String Id_Grupo = "";
					voGrupo = ConsultaGrupos("", "", Id_Site);
					for (int j=0;j<voGrupo.size();j++) {
						Id_Grupo = voGrupo.get(j).getId();
						if (!Id_Grupo.contentEquals("")) {
							String Id_Elemento = "";
							voElemento = ConsultaElementos("", "", Id_Grupo, Id_Site);
							for (int k=0;k<voElemento.size();k++) {
								Id_Elemento = voElemento.get(k).getId();
								if (!Id_Elemento.contentEquals("")) {
									String Id_Atributo = "";
									voAtributo = ConsultaAtributos("", "", Id_Elemento);
									for (int l=0;l<voAtributo.size();l++) {
										Id_Atributo = voAtributo.get(l).getId();
										if (!Id_Atributo.contentEquals("")) {
											String delete_Valor = "Delete from Valores where Id_Atributo = " + Id_Atributo;
											Statement stDelete_Valor = null;
											stDelete_Valor = connection.createStatement();
											int rowsaffected = stDelete_Valor.executeUpdate(delete_Valor);
											stDelete_Valor.close();
											if (rowsaffected == 0) {
												break;
											}
										}
									}
									String delete_Atributo = "Delete from Atributos where Id_Elemento = " + Id_Elemento;
									Statement stDelete_Atributo = null;
									stDelete_Atributo = connection.createStatement();
									int rowsaffected = stDelete_Atributo.executeUpdate(delete_Atributo);
									stDelete_Atributo.close();
								}
							}
						}
					}
				} else {
					LOGGER.error("Falha ao tentar executar um clean na tabela de Elementos, Site Informado Inválido!");
					throw new Exception("Falha ao tentar executar um clean na tabela de Elementos, Site Informado Inválido!");
				}
				String clean_Elemento = "Delete from Elementos where Descricao = '" + Descricao_elemento + "'";
				Statement stClean_Elemento = null;
				stClean_Elemento = connection.createStatement();
				int rowsaffected = stClean_Elemento.executeUpdate(clean_Elemento);
				if (rowsaffected<=0) {
					LOGGER.error("Falha ao tentar executar um clean na tabela de Elementos, não econtrado nehum registro para limpar!");
					throw new Exception("Falha ao tentar executar um clean na tabela de Elementos, não econtrado nehum registro para limpar!");
				}
				stClean_Elemento.close();
			}
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um clean na tabela de Elementos, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um clean na tabela de Elementos!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para inserir Atributos
	 * @throws Exception 
	 */
	public void insertAtributo(String Nome, String Descricao_Elemento, String Descricao_Grupo, String Descricao_Site) throws Exception{
		
		LOGGER.info("### Inicio do Insert do NomeAtributo/DescricaoElemento/DescricaoGrupo/DescricaoSite " + Nome + "/" + 
				Descricao_Elemento + "/" + Descricao_Grupo + "/" + Descricao_Site);
		try {
			String Id_Site = "";
			ArrayList<SiteVo> voSite = ConsultaSites("", Descricao_Site);
			for (int i=0;i<voSite.size();i++) {
				Id_Site = voSite.get(i).getId();
				break;
			}
			String Id_Grupo = "";
			ArrayList<GrupoVo> voGrupo = ConsultaGrupos("", Descricao_Grupo, Id_Site);
			for (int i=0;i<voGrupo.size();i++) {
				Id_Grupo = voGrupo.get(i).getId();
				break;
			}
			String Id_Elemento = "";
			ArrayList<ElementoVo> voElemento = ConsultaElementos("", Descricao_Elemento, Id_Grupo, Id_Site);
			for (int i=0;i<voElemento.size();i++) {
				Id_Elemento = voElemento.get(i).getId();
				break;
			}
			if (!Id_Elemento.contentEquals("")) {
				String insert_Atributo;
				if (Nome==null) {
					insert_Atributo = "Insert into Atributos  (Nome, Id_Elemento) values (" + Nome + ", " + Id_Elemento + ")";
				} else {
					insert_Atributo = "Insert into Atributos  (Nome, Id_Elemento) values ('" + Nome + "', " + Id_Elemento + ")";
				}
				Statement stInsert_Atributo = null;
				stInsert_Atributo = connection.createStatement();
				stInsert_Atributo.executeUpdate(insert_Atributo);
				stInsert_Atributo.close();
			} else {
				LOGGER.error("Falha ao tentar efetuar insert na tabela de Atributos, Id_Elemento não loalizado!");
				throw new Exception("Falha ao tentar efetuar insert na tabela de Atributos, Id_Elemento não loalizado!");
			}
		}catch (Exception e) {
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um insert na tabela de Atributos, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um insert na tabela de Atributos!", e);
			}
			throw e;
		}
	}

	/**
	 * Metodo para select Atributos
	 * @throws Exception 
	 */
	public String selectAtributo(String Nome_atributo, String Descricao_elemento, String Descricao_grupo, String Descricao_site) throws Exception{
	
		String retorno = null;
		ArrayList<AtributoVo> voAtributo = null;
		LOGGER.info("### Inicio do Select do Atributo " + Nome_atributo);
		try {
			if (Nome_atributo == null) {
				Nome_atributo = "";
			}
			ArrayList<SiteVo> voSite = ConsultaSites("",Descricao_site);
			if (voSite!=null) {
				for(int i=0;i<voSite.size();i++) {
					ArrayList<GrupoVo> voGrupo = ConsultaGrupos("",Descricao_grupo,voSite.get(i).getId());
					if (voGrupo!=null) {
						for(int j=0;j<voGrupo.size();j++) {
							ArrayList<ElementoVo> voElemento = ConsultaElementos("",Descricao_elemento,voGrupo.get(j).getId(),voSite.get(i).getId());
							if (voElemento!=null) {
								for(int l=0;l<voElemento.size();l++) {
									voAtributo = ConsultaAtributos("",Nome_atributo,voElemento.get(l).getId());
									if (voAtributo!=null) {
										Gson gson = new Gson();
										retorno = retorno==null?"":retorno;
										retorno += gson.toJson(voAtributo);
										retorno += "\n";
									}
								}
							}
						}
					}
				}
			}
		}catch (Exception e) {		
			LOGGER.error("Erro não previsto ao tentar executar um select na tabela de Atributos!", e);
			throw e;
		}
		return retorno;
	}
	
	/**
	 * Metodo para update Atributos
	 * @throws Exception 
	 */
	public void updateAtributo(String Nome_atributo, String Descricao_elemento, String Descricao_grupo, String Descricao_site, String Nome_atributo_to) throws Exception{
		
		LOGGER.info("### Inicio do Update do Atributo " + Nome_atributo + " para " + Nome_atributo_to);
		try {
			String update_Atributo;
			if (Nome_atributo_to==null) {
				update_Atributo = "Update Atributos set Nome = " + Nome_atributo_to + " where Descricao = '" + Nome_atributo + "'";
			} else {
				update_Atributo = "Update Atributos set Nome = '" + Nome_atributo_to + "' where Descricao = '" + Nome_atributo + "'";
			}
			Statement stUpdate_Atributo = null;
			stUpdate_Atributo = connection.createStatement();
			int rowsaffected = stUpdate_Atributo.executeUpdate(update_Atributo);
			if (rowsaffected<=0) {
				LOGGER.error("Falha ao tentar executar um update na tabela de Atributos, não econtrado nehum registro para alterar!");
				throw new Exception("Falha ao tentar executar um update na tabela de Atributos, não econtrado nehum registro para alterar!");
			}
			stUpdate_Atributo.close();
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um update na tabela de Atributos, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um update na tabela de Atributos!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para delete Atributos
	 * @throws Exception 
	 */
	public void deleteAtributo(String Nome_atributo, String Descricao_elemento, String Descricao_grupo, String Descricao_site) throws Exception{
		
		LOGGER.info("### Inicio do Delete do Atributo " + Nome_atributo);
		try {
			String delete_Atributo = "Delete from Atributos where Nome = '" + Nome_atributo + "'";
			Statement stDelete_Atributo = null;
			stDelete_Atributo = connection.createStatement();
			int rowsaffected = stDelete_Atributo.executeUpdate(delete_Atributo);
			if (rowsaffected<=0) {
				LOGGER.error("Falha ao tentar executar um delete na tabela de Atributos, não econtrado nehum registro para excluir!");
				throw new Exception("Falha ao tentar executar um delete na tabela de Atributos, não econtrado nehum registro para excluir!");
			}
			stDelete_Atributo.close();
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um delete na tabela de Atributos, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um delete na tabela de Atributos!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para clean Atributos
	 * @throws Exception 
	 */
	public void cleanAtributo(String Nome_atributo, String Descricao_elemento, String Descricao_grupo, String Descricao_site) throws Exception{
		
		LOGGER.info("### Inicio do Clean do Atributo " + Nome_atributo);
		//tem que mudar logica para fazer um delete cascate
		try {
			String Id_Site = "";
			ArrayList<SiteVo> voSite = ConsultaSites("", Descricao_site);
			ArrayList<GrupoVo> voGrupo;
			ArrayList<ElementoVo> voElemento;
			ArrayList<AtributoVo> voAtributo;
			for (int i=0;i<voSite.size();i++) {
				Id_Site = voSite.get(i).getId();
				if (!Id_Site.contentEquals("")) {
					String Id_Grupo = "";
					voGrupo = ConsultaGrupos("", "", Id_Site);
					for (int j=0;j<voGrupo.size();j++) {
						Id_Grupo = voGrupo.get(j).getId();
						if (!Id_Grupo.contentEquals("")) {
							String Id_Elemento = "";
							voElemento = ConsultaElementos("", "", Id_Grupo, Id_Site);
							for (int k=0;k<voElemento.size();k++) {
								Id_Elemento = voElemento.get(k).getId();
								if (!Id_Elemento.contentEquals("")) {
									String Id_Atributo = "";
									voAtributo = ConsultaAtributos("", "", Id_Elemento);
									for (int l=0;l<voAtributo.size();l++) {
										Id_Atributo = voAtributo.get(l).getId();
										if (!Id_Atributo.contentEquals("")) {
											String delete_Valor = "Delete from Valores where Id_Atributo = " + Id_Atributo;
											Statement stDelete_Valor = null;
											stDelete_Valor = connection.createStatement();
											int rowsaffected = stDelete_Valor.executeUpdate(delete_Valor);
											stDelete_Valor.close();
											if (rowsaffected == 0) {
												break;
											}
										}
									}
								}
							}
						}
					}
				} else {
					LOGGER.error("Falha ao tentar executar um clean na tabela de Atributos, Site Informado Inválido!");
					throw new Exception("Falha ao tentar executar um clean na tabela de Atributos, Site Informado Inválido!");
				}
				String clean_Atributo = "Delete from Atributos where Nome = '" + Nome_atributo + "'";
				Statement stClean_Atributo = null;
				stClean_Atributo = connection.createStatement();
				int rowsaffected = stClean_Atributo.executeUpdate(clean_Atributo);
				if (rowsaffected<=0) {
					LOGGER.error("Falha ao tentar executar um clean na tabela de Atributos, não econtrado nehum registro para limpar!");
					throw new Exception("Falha ao tentar executar um clean na tabela de Atributos, não econtrado nehum registro para limpar!");
				}
				stClean_Atributo.close();
			}
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um clean na tabela de Atributos, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um clean na tabela de Atributos!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para inserir Valores
	 * @throws Exception 
	 */
	public void insertValor(String Valor, String Nome_Atributo, String Descricao_Elemento, String Descricao_Grupo, String Descricao_Site) throws Exception{
		
		LOGGER.info("### Inicio do Insert do Valor/NomeAtributo/DescricaoElemento/DescricaoGrupo/DescricaoSite " + Valor + "/" + Nome_Atributo + "/" + 
				Descricao_Elemento + "/" + Descricao_Grupo + "/" + Descricao_Site);
		try {
			String Id_Site = "";
			ArrayList<SiteVo> voSite = ConsultaSites("", Descricao_Site);
			for (int i=0;i<voSite.size();i++) {
				Id_Site = voSite.get(i).getId();
				break;
			}
			String Id_Grupo = "";
			ArrayList<GrupoVo> voGrupo = ConsultaGrupos("", Descricao_Grupo, Id_Site);
			for (int i=0;i<voGrupo.size();i++) {
				Id_Grupo = voGrupo.get(i).getId();
				break;
			}
			String Id_Elemento = "";
			ArrayList<ElementoVo> voElemento = ConsultaElementos("", Descricao_Elemento, Id_Grupo, Id_Site);
			for (int i=0;i<voElemento.size();i++) {
				Id_Elemento = voElemento.get(i).getId();
				break;
			}
			String Id_Atributo = "";
			ArrayList<AtributoVo> voAtributo = ConsultaAtributos("", Nome_Atributo, Id_Elemento);
			for (int i=0;i<voAtributo.size();i++) {
				Id_Atributo = voAtributo.get(i).getId();
				break;
			}
			if (!Id_Atributo.contentEquals("")) {
				String insert_Valor;
				if (Valor==null) {
					insert_Valor = "Insert into Valores  (Valor, Id_Atributo) values (" + Valor + ", " + Id_Atributo + ")";
				} else {
					insert_Valor = "Insert into Valores  (Valor, Id_Atributo) values ('" + Valor + "', " + Id_Atributo + ")";
				}
				Statement stInsert_Valor = null;
				stInsert_Valor = connection.createStatement();
				stInsert_Valor.executeUpdate(insert_Valor);
				stInsert_Valor.close();
			} else {
				LOGGER.error("Falha ao tentar efetuar insert na tabela de Valores, Id_Atributo não loalizado!");
				throw new Exception("Falha ao tentar efetuar insert na tabela de Valores, Id_Atributo não loalizado!");
			}
		}catch (Exception e) {
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um insert na tabela de Valores, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um insert na tabela de Valores!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para select Valores
	 * @throws Exception 
	 */
	public String selectValor(String Valor_valor, String Valor_atributo, String Valor_elemento, String Valor_grupo, String Valor_site) throws Exception{
		
		String retorno = null;
		ArrayList<ValorVo> voValor = null;
		LOGGER.info("### Inicio do Select do Valor " + Valor_valor);
		try {
			if (Valor_valor == null) {
				Valor_valor = "";
			}
			ArrayList<SiteVo> voSite = ConsultaSites("",Valor_site);
			if (voSite!=null) {
				for(int i=0;i<voSite.size();i++) {
					ArrayList<GrupoVo> voGrupo = ConsultaGrupos("",Valor_grupo,voSite.get(i).getId());
					if (voGrupo!=null) {
						for(int j=0;j<voGrupo.size();j++) {
							ArrayList<ElementoVo> voElemento = ConsultaElementos("",Valor_elemento,voGrupo.get(j).getId(),voSite.get(i).getId());
							if (voElemento!=null) {
								for(int k=0;k<voElemento.size();k++) {
									ArrayList<AtributoVo> voAtributo = ConsultaAtributos("",Valor_atributo,voElemento.get(k).getId());
									if (voAtributo!=null) {
										for(int l=0;l<voAtributo.size();l++) {
											voValor = ConsultaValores("",Valor_valor,voAtributo.get(l).getId());
											if (voValor!=null) {
												Gson gson = new Gson();
												retorno = retorno==null?"":retorno;
												retorno += gson.toJson(voValor);
												retorno += "\n";
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}catch (Exception e) {		
			LOGGER.error("Erro não previsto ao tentar executar um select na tabela de Valores!", e);
			throw e;
		}
		return retorno;
	}
	
	/**
	 * Metodo para update Valores
	 * @throws Exception 
	 */
	public void updateValor(String Valor_valor, String Valor_atributo, String Valor_elemento, String Valor_grupo, String Valor_site, String Valor_valor_to) throws Exception{
		
		LOGGER.info("### Inicio do Update do Valor " + Valor_valor + " para " + Valor_valor_to);
		try {
			String update_Valor;
			if (Valor_valor_to==null) {
				update_Valor = "Update Valores set Valor = " + Valor_valor_to + " where Valor = '" + Valor_valor + "'";
			} else {
				update_Valor = "Update Valores set Valor = '" + Valor_valor_to + "' where Valor = '" + Valor_valor + "'";
			}
			Statement stUpdate_Valor = null;
			stUpdate_Valor = connection.createStatement();
			int rowsaffected = stUpdate_Valor.executeUpdate(update_Valor);
			if (rowsaffected<=0) {
				LOGGER.error("Falha ao tentar executar um update na tabela de Valores, não econtrado nehum registro para alterar!");
				throw new Exception("Falha ao tentar executar um update na tabela de Valores, não econtrado nehum registro para alterar!");
			}
			stUpdate_Valor.close();
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um update na tabela de Valores, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um update na tabela de Valores!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para delete Valores
	 * @throws Exception 
	 */
	public void deleteValor(String Valor_valor, String Valor_atributo, String Valor_elemento, String Valor_grupo, String Valor_site) throws Exception{
		
		LOGGER.info("### Inicio do Delete do Valor " + Valor_valor);
		try {
			String delete_Valor = "Delete from Valores where Valor = '" + Valor_valor + "'";
			Statement stDelete_Valor = null;
			stDelete_Valor = connection.createStatement();
			int rowsaffected = stDelete_Valor.executeUpdate(delete_Valor);
			if (rowsaffected<=0) {
				LOGGER.error("Falha ao tentar executar um delete na tabela de Valores, não econtrado nehum registro para excluir!");
				throw new Exception("Falha ao tentar executar um delete na tabela de Valores, não econtrado nehum registro para excluir!");
			}
			stDelete_Valor.close();
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um delete na tabela de Valores, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um delete na tabela de Valores!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para clean Valores
	 * @throws Exception 
	 */
	public void cleanValor(String Valor_valor, String Valor_atributo, String Valor_elemento, String Valor_grupo, String Valor_site) throws Exception{
		
		LOGGER.info("### Inicio do Clean do Valor " + Valor_valor);
		//tem que mudar logica para fazer um delete cascate
		try {
			String Id_Site = "";
			ArrayList<SiteVo> voSite = ConsultaSites("", Valor_site);
			ArrayList<GrupoVo> voGrupo;
			ArrayList<ElementoVo> voElemento;
			ArrayList<AtributoVo> voAtributo;
			for (int i=0;i<voSite.size();i++) {
				Id_Site = voSite.get(i).getId();
				if (!Id_Site.contentEquals("")) {
					String Id_Grupo = "";
					voGrupo = ConsultaGrupos("", "", Id_Site);
					for (int j=0;j<voGrupo.size();j++) {
						Id_Grupo = voGrupo.get(j).getId();
						if (!Id_Grupo.contentEquals("")) {
							String Id_Elemento = "";
							voElemento = ConsultaElementos("", "", Id_Grupo, Id_Site);
							for (int k=0;k<voElemento.size();k++) {
								Id_Elemento = voElemento.get(k).getId();
								if (!Id_Elemento.contentEquals("")) {
									String Id_Atributo = "";
									voAtributo = ConsultaAtributos("", "", Id_Elemento);
									for (int l=0;l<voAtributo.size();l++) {
										Id_Atributo = voAtributo.get(l).getId();
										if (!Id_Atributo.contentEquals("")) {
											String clean_Valor = "Delete from Valores where Valor = '" + Valor_valor + "' and Id_Atributo = " + Id_Atributo;
											Statement stClean_Valor = null;
											stClean_Valor = connection.createStatement();
											int rowsaffected = stClean_Valor.executeUpdate(clean_Valor);
											if (rowsaffected<=0) {
												LOGGER.error("Falha ao tentar executar um clean na tabela de Valores, não econtrado nehum registro para limpar!");
												throw new Exception("Falha ao tentar executar um clean na tabela de Valores, não econtrado nehum registro para limpar!");
											}
											stClean_Valor.close();
										} else {
											LOGGER.error("Falha ao tentar executar um clean na tabela de Valores, Atributo Informado Inválido!");
											throw new Exception("Falha ao tentar executar um clean na tabela de Valores, Atributo Informado Inválido!");
										}
									}
								}
							}
						}
					}
				}
			}
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um clean na tabela de Valores, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um clean na tabela de Valores!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para consultar Sites
	 * @throws Exception 
	 */
	public ArrayList<SiteVo> ConsultaSites(String Id, String Descricao) throws Exception{

		Statement stConsulta = null;		
		ArrayList<SiteVo> retorno = null;
		SiteVo vo;
		
		LOGGER.info("### Inicio da consultaSites");
		try {

			String consulta =   "select Id, Descricao from Sites ";
			String where_Id = "";
			String where_Descricao = "";
			if (!Id.isEmpty() && !Id.equals("")) {
				where_Id += "Id = " + Id + " ";
			}
			if (!Descricao.isEmpty() && !Descricao.equals("")) {
				where_Descricao += "Descricao = " + Descricao;
			}
			String where = "";
			if (!where_Id.equals("") || !where_Descricao.equals("")) {
				if (!where_Id.equals("")) {
					where = "Id = " + Id + " ";
				}
				if (!where_Descricao.equals("")) {
					if (!where.equals("")) {
						where += "and Descricao = '" + Descricao + "' ";
					} else {
						where += "Descricao = '" + Descricao + "' ";
					}
				}
				if (!where.equals("")) {
					consulta += "where " + where;
				}
			}
			consulta += " order by Id";
			LOGGER.info("### Executando Consulta: " + consulta);
			stConsulta = connection.createStatement();
			stConsulta.setQueryTimeout(1);
			ResultSet rs = stConsulta.executeQuery(consulta);
			
			retorno = new ArrayList<SiteVo>();
			if (rs.isBeforeFirst()) {
				LOGGER.info("Varrendo ResultSet retornado...");
				for (; rs.next(); ) {
					vo = new SiteVo();
					vo.setId(rs.getString(1)); //Id
					vo.setDescricao(rs.getString(2)); //Descricao
					retorno.add(vo);
					LOGGER.info("Inserido Id=" + rs.getString(1) + " Descricao=" + rs.getString(2) + " ao SiteVo");
				}
				LOGGER.info("ResultSet retornado varrido!");
			} else {
				LOGGER.info("ResultSet retornado não retornou nenhuma linha!");
			}
			
		}catch (Exception e) {		
			LOGGER.error("Erro não previsto ao executar a consulta", e);

			throw e;

		}finally {
			try {
				if(stConsulta != null){
					stConsulta.close();
				}
			} catch (Exception e) {
				
				LOGGER.error("Erro não previsto ao fechar o statement stConsulta", e);
			}
		} 

		return retorno;
	}
	
	/**
	 * Metodo para consultar Grupos
	 * @throws Exception 
	 */
	public ArrayList<GrupoVo> ConsultaGrupos(String Id, String Descricao, String Id_Site) throws Exception{

		Statement stConsulta = null;		
		ArrayList<GrupoVo> retorno = null;
		GrupoVo vo;
		
		LOGGER.info("### Inicio da consultaGrupos");
		try {

			String consulta =   "select Id, Descricao, Id_Site from Grupos ";
			String where_Id = "";
			String where_Descricao = "";
			String where_Id_Site = "";
			if (!Id.isEmpty() && !Id.equals("")) {
				where_Id += "Id = " + Id + " ";
			}
			if (!Descricao.isEmpty() && !Descricao.equals("")) {
				where_Descricao += "Descricao = " + Descricao;
			}
			String where = "";
			if (!where_Id.equals("") || !where_Descricao.equals("")) {
				if (!where_Id.equals("")) {
					where = "Id = " + Id + " ";
				}
				if (!where_Descricao.equals("")) {
					if (!where.equals("")) {
						where += "and Descricao = '" + Descricao + "' ";
					} else {
						where += "Descricao = '" + Descricao + "' ";
					}
				}
				if (!where_Id_Site.equals("")) {
					if (!where.equals("")) {
						where += "and Id_Site = " + Id_Site + " ";
					} else {
						where += "Id_Site = " + Id_Site + " ";
					}
				}
				if (!where.equals("")) {
					consulta += "where " + where;
				}
			}
			consulta += " order by Id_Site, Id";
			LOGGER.info("### Executando Consulta: " + consulta);
			stConsulta = connection.createStatement();
			stConsulta.setQueryTimeout(1);
			ResultSet rs = stConsulta.executeQuery(consulta);
		
			retorno = new ArrayList<GrupoVo>();
			if (rs.isBeforeFirst()) {
				LOGGER.info("Varrendo ResultSet retornado...");
				for (; rs.next(); ) {
					vo = new GrupoVo();
					vo.setId(rs.getString(1)); //Id
					vo.setDescricao(rs.getString(2)); //Descricao
					vo.setId_Site(rs.getString(3)); //Id_Site
					retorno.add(vo);
					LOGGER.info("Inserido Id=" + rs.getString(1) + " Descricao=" + rs.getString(2) + " Id_Site=" + rs.getString(3) + " ao GrupoVo");
				}
				LOGGER.info("ResultSet retornado varrido!");
			} else {
				LOGGER.info("ResultSet retornado não retornou nenhuma linha!");
			}
			
		}catch (Exception e) {		
			LOGGER.error("Erro não previsto ao executar a consulta", e);

			throw e;

		}finally {
			try {
				if(stConsulta != null){
					stConsulta.close();
				}
			} catch (Exception e) {
				
				LOGGER.error("Erro não previsto ao fechar o statement stConsulta", e);
			}
		} 
		
		return retorno;
	}
	
	/**
	 * Metodo para consultar Elementos
	 * @throws Exception 
	 */
	public ArrayList<ElementoVo> ConsultaElementos(String Id, String Descricao, String Id_Grupo, String Id_Site) throws Exception{

		Statement stConsulta = null;		
		ArrayList<ElementoVo> retorno = null;
		ElementoVo vo;
		
		LOGGER.info("### Inicio da consultaElementos");
		try {

			String consulta =   "select Id, Descricao, Id_Grupo, Id_Site from Elementos ";
			String where_Id = "";
			String where_Descricao = "";
			String where_Id_Grupo = "";
			String where_Id_Site = "";
			if (!Id.isEmpty() && !Id.equals("")) {
				where_Id += "Id = " + Id + " ";
			}
			if (!Descricao.isEmpty() && !Descricao.equals("")) {
				where_Descricao += "Descricao = " + Descricao;
			}
			if (!Id_Grupo.isEmpty() && !Id_Grupo.equals("")) {
				where_Id_Grupo += "Id_Grupo = " + Id_Grupo + " ";
			}
			if (!Id_Site.isEmpty() && !Id_Site.equals("")) {
				where_Id_Site += "Id_Site = " + Id_Site + " ";
			}
			String where = "";
			if (!where_Id.equals("") || !where_Descricao.equals("") || !where_Id_Grupo.equals("") || !where_Id_Site.equals("")) {
				if (!where_Id.equals("")) {
					where = "Id = " + Id + " ";
				}
				if (!where_Descricao.equals("")) {
					if (!where.equals("")) {
						where += "and Descricao = '" + Descricao + "' ";
					} else {
						where += "Descricao = '" + Descricao + "' ";
					}
				}
				if (!where_Id_Grupo.equals("")) {
					if (!where.equals("")) {
						where += "and Id_Grupo = " + Id_Grupo + " ";
					} else {
						where += "Id_Grupo = " + Id_Grupo + " ";
					}
				}
				if (!where_Id_Site.equals("")) {
					if (!where.equals("")) {
						where += "and Id_Site = " + Id_Site + " ";
					} else {
						where += "Id_Site = " + Id_Site + " ";
					}
				}
				if (!where.equals("")) {
					consulta += "where " + where;
				}
			}
			consulta += " order by Id_Site, Id_Grupo, Id";
			LOGGER.info("### Executando Consulta: " + consulta);
			stConsulta = connection.createStatement();
			stConsulta.setQueryTimeout(1);
			ResultSet rs = stConsulta.executeQuery(consulta);
		
			retorno = new ArrayList<ElementoVo>();
			if (rs.isBeforeFirst()) {
				LOGGER.info("Varrendo ResultSet retornado...");
				for (; rs.next(); ) {
					vo = new ElementoVo();
					vo.setId(rs.getString(1)); //Id
					vo.setDescricao(rs.getString(2)); //Descricao
					vo.setId_Grupo(rs.getString(3)); //Id_Grupo
					vo.setId_Site(rs.getString(4)); //Id_Site
					retorno.add(vo);
					LOGGER.info("Inserido Id=" + rs.getString(1) + " Descricao=" + rs.getString(2) + " Id_Grupo=" + rs.getString(3) + " Id_Site=" + rs.getString(4) + " ao ElementoVo");
				}
				LOGGER.info("ResultSet retornado varrido!");
			} else {
				LOGGER.info("ResultSet retornado não retornou nenhuma linha!");
			}
			
		}catch (Exception e) {		
			LOGGER.error("Erro não previsto ao executar a consulta", e);

			throw e;

		}finally {
			try {
				if(stConsulta != null){
					stConsulta.close();
				}
			} catch (Exception e) {
				
				LOGGER.error("Erro não previsto ao fechar o statement stConsulta", e);
			}
		} 
		
		return retorno;
	}

	/**
	 * Metodo para consultar Atributos
	 * @throws Exception 
	 */
	public ArrayList<AtributoVo> ConsultaAtributos(String Id, String Nome, String Id_Elemento) throws Exception{

		Statement stConsulta = null;		
		ArrayList<AtributoVo> retorno = null;
		AtributoVo vo;
		
		LOGGER.info("### Inicio da consultaAtributos");
		try {

			String consulta =   "select Id, Nome, Id_Elemento from Atributos ";
			String where_Id = "";
			String where_Nome = "";
			String where_Id_Elemento = "";
			if (!Id.isEmpty() && !Id.equals("")) {
				where_Id += "Id = " + Id + " ";
			}
			if (!Nome.isEmpty() && !Nome.equals("")) {
				where_Nome += "Nome = " + Nome;
			}
			if (!Id_Elemento.isEmpty() && !Id_Elemento.equals("")) {
				where_Id_Elemento += "Id_Elemento = " + Id_Elemento;
			}
			String where = "";
			if (!where_Id.equals("") || !where_Nome.equals("") || !where_Id_Elemento.equals("")) {
				if (!where_Id.equals("")) {
					where = "Id = " + Id + " ";
				}
				if (!where_Nome.equals("")) {
					if (!where.equals("")) {
						where += "and Nome = '" + Nome + "' ";
					} else {
						where += "Nome = '" + Nome + "' ";
					}
				}
				if (!where_Id_Elemento.equals("")) {
					if (!where.equals("")) {
						where += "and Id_Elemento = " + Id_Elemento + " ";
					} else {
						where += "Id_Elemento = " + Id_Elemento + " ";
					}
				}
				if (!where.equals("")) {
					consulta += "where " + where;
				}
			}
			consulta += " order by Id_Elemento, Id";
			LOGGER.info("### Executando Consulta: " + consulta);
			stConsulta = connection.createStatement();
			stConsulta.setQueryTimeout(1);
			ResultSet rs = stConsulta.executeQuery(consulta);
		
			retorno = new ArrayList<AtributoVo>();
			if (rs.isBeforeFirst()) {
				LOGGER.info("Varrendo ResultSet retornado...");
				for (; rs.next(); ) {
					vo = new AtributoVo();
					vo.setId(rs.getString(1)); //Id
					vo.setNome(rs.getString(2)); //Nome
					vo.setId_Elemento(rs.getString(3)); //Id_Elemento
					retorno.add(vo);
					LOGGER.info("Inserido Id=" + rs.getString(1) + " Nome=" + rs.getString(2) + " Id_Elemento=" + rs.getString(3) + " ao AtributoVo");
				}
				LOGGER.info("ResultSet retornado varrido!");
			} else {
				LOGGER.info("ResultSet retornado não retornou nenhuma linha!");
			}
			
		}catch (Exception e) {		
			LOGGER.error("Erro não previsto ao executar a consulta", e);

			throw e;

		}finally {
			try {
				if(stConsulta != null){
					stConsulta.close();
				}
			} catch (Exception e) {
				
				LOGGER.error("Erro não previsto ao fechar o statement stConsulta", e);
			}
		} 
		
		return retorno;
	}

	/**
	 * Metodo para consultar Valores
	 * @throws Exception 
	 */
	public ArrayList<ValorVo> ConsultaValores(String Id, String Valor, String Id_Atributo) throws Exception{

		Statement stConsulta = null;		
		ArrayList<ValorVo> retorno = null;
		ValorVo vo;
		
		LOGGER.info("### Inicio da consultaValores");
		try {

			String consulta =   "select Id, Valor, Id_Atributo from Valores ";
			String where_Id = "";
			String where_Valor = "";
			String where_Id_Atributo = "";
			if (!Id.isEmpty() && !Id.equals("")) {
				where_Id += "Id = " + Id + " ";
			}
			if (!Valor.isEmpty() && !Valor.equals("")) {
				where_Valor += "Valor = " + Valor;
			}
			if (!Id_Atributo.isEmpty() && !Id_Atributo.equals("")) {
				where_Id_Atributo += "Id_Atributo = " + Id_Atributo + " ";
			}
			String where = "";
			if (!where_Id.equals("") || !where_Valor.equals("") || !where_Id_Atributo.equals("")) {
				if (!where_Id.equals("")) {
					where = "Id = " + Id + " ";
				}
				if (!where_Valor.equals("")) {
					if (!where.equals("")) {
						where += "and Valor = '" + Valor + "' ";
					} else {
						where += "Valor = '" + Valor + "' ";
					}
				}
				if (!where_Id_Atributo.equals("")) {
					if (!where.equals("")) {
						where += "and Id_Atributo = " + Id_Atributo + " ";
					} else {
						where += "Id_Atributo = " + Id_Atributo + " ";
					}
				}
				if (!where.equals("")) {
					consulta += "where " + where;
				}
			}
			consulta += " order by Id_Atributo, Id";
			LOGGER.info("### Executando Consulta: " + consulta);
			stConsulta = connection.createStatement();
			stConsulta.setQueryTimeout(1);
			ResultSet rs = stConsulta.executeQuery(consulta);
		
			retorno = new ArrayList<ValorVo>();
			if (rs.isBeforeFirst()) {
				LOGGER.info("Varrendo ResultSet retornado...");
				for (; rs.next(); ) {
					vo = new ValorVo();
					vo.setId(rs.getString(1)); //Id
					vo.setValor(rs.getString(2)); //Valor
					vo.setId_Atributo(rs.getString(3)); //Id_Atributo
					retorno.add(vo);
					LOGGER.info("Inserido Id=" + rs.getString(1) + " Valor=" + rs.getString(2) + " Id_Atributo=" + rs.getString(3) + " ao ValorVo");
				}
				LOGGER.info("ResultSet retornado varrido!");
			} else {
				LOGGER.info("ResultSet retornado não retornou nenhuma linha!");
			}
			
		}catch (Exception e) {		
			LOGGER.error("Erro não previsto ao executar a consulta", e);

			throw e;

		}finally {
			try {
				if(stConsulta != null){
					stConsulta.close();
				}
			} catch (Exception e) {
				
				LOGGER.error("Erro não previsto ao fechar o statement stConsulta", e);
			}
		} 
		
		return retorno;
	}

	/**
	 * Metodo para consultar Colecoes
	 * @throws Exception 
	 */
	public ArrayList<ColecaoVo> ConsultaColecoes(String Id, String Descricao, String Id_Site) throws Exception{

		Statement stConsulta = null;		
		ArrayList<ColecaoVo> retorno = null;
		ColecaoVo vo;
		
		LOGGER.info("### Inicio da consultaColecoes");
		try {

			String consulta =   "select Id, Descricao, Id_Site from Colecoes ";
			String where_Id = "";
			String where_Descricao = "";
			String where_Id_Site = "";
			if (!Id.isEmpty() && !Id.equals("")) {
				where_Id += "Id = " + Id + " ";
			}
			if (!Descricao.isEmpty() && !Descricao.equals("")) {
				where_Descricao += "Descricao = " + Descricao;
			}
			String where = "";
			if (!where_Id.equals("") || !where_Descricao.equals("")) {
				if (!where_Id.equals("")) {
					where = "Id = " + Id + " ";
				}
				if (!where_Descricao.equals("")) {
					if (!where.equals("")) {
						where += "and Descricao = '" + Descricao + "' ";
					} else {
						where += "Descricao = '" + Descricao + "' ";
					}
				}
				if (!where_Id_Site.equals("")) {
					if (!where.equals("")) {
						where += "and Id_Site = " + Id_Site + " ";
					} else {
						where += "Id_Site = " + Id_Site + " ";
					}
				}
				if (!where.equals("")) {
					consulta += "where " + where;
				}
			}
			consulta += " order by Id_Site, Id";
			LOGGER.info("### Executando Consulta: " + consulta);
			stConsulta = connection.createStatement();
			stConsulta.setQueryTimeout(1);
			ResultSet rs = stConsulta.executeQuery(consulta);
		
			retorno = new ArrayList<ColecaoVo>();
			if (rs.isBeforeFirst()) {
				LOGGER.info("Varrendo ResultSet retornado...");
				for (; rs.next(); ) {
					vo = new ColecaoVo();
					vo.setId(rs.getString(1)); //Id
					vo.setDescricao(rs.getString(2)); //Descricao
					vo.setId_Site(rs.getString(3)); //Id_Site
					retorno.add(vo);
					LOGGER.info("Inserido Id=" + rs.getString(1) + " Descricao=" + rs.getString(2) + " Id_Site=" + rs.getString(3) + " ao ColecaoVo");
				}
				LOGGER.info("ResultSet retornado varrido!");
			} else {
				LOGGER.info("ResultSet retornado não retornou nenhuma linha!");
			}
			
		}catch (Exception e) {		
			LOGGER.error("Erro não previsto ao executar a consulta", e);

			throw e;

		}finally {
			try {
				if(stConsulta != null){
					stConsulta.close();
				}
			} catch (Exception e) {
				
				LOGGER.error("Erro não previsto ao fechar o statement stConsulta", e);
			}
		} 
		
		return retorno;
	}

	/**
	 * Metodo para inserir Colecoes
	 * @throws Exception 
	 */
	public void insertColecao(String Descricao, String Descricao_Site) throws Exception{
		
		LOGGER.info("### Inicio do Insert do Colecoes/Site " + Descricao + "/" + Descricao_Site);
		try {
			String Id_Site = "";
			ArrayList<SiteVo> vo = ConsultaSites("", Descricao_Site);
			for (int i=0;i<vo.size();i++) {
				Id_Site = vo.get(i).getId();
				break;
			}
			if (!Id_Site.contentEquals("")) {
				String insert_Colecoes;
				if (Descricao==null) {
					insert_Colecoes = "Insert into Colecoes  (Descricao, Id_Site) values (" + Descricao + "," + Id_Site + ")";
				} else {
					insert_Colecoes = "Insert into Colecoes  (Descricao, Id_Site) values ('" + Descricao + "'," + Id_Site + ")";
				}
				Statement stInsert_Colecoes = null;
				stInsert_Colecoes = connection.createStatement();
				stInsert_Colecoes.executeUpdate(insert_Colecoes);
				stInsert_Colecoes.close();
			} else {
				LOGGER.error("Falha ao tentar efetuar insert na tabela de Colecoes, Id_Site não loalizado!");
				throw new Exception("Falha ao tentar efetuar insert na tabela de Colecoes, Id_Site não loalizado!");
			}
		}catch (Exception e) {
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um insert na tabela de Colecoes, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um insert na tabela de Colecoes!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para select Colecoes
	 * @throws Exception 
	 */
	public String selectColecao(String Descricao_Colecao, String Descricao_Site) throws Exception{
	
		String retorno = null;
		ArrayList<ColecaoVo> voColecao = null;
		LOGGER.info("### Inicio do Select do Colecao " + Descricao_Colecao);
		try {
			if (Descricao_Colecao == null) {
				Descricao_Colecao = "";
			}
			ArrayList<SiteVo> voSite = ConsultaSites("", Descricao_Site);
			if (voSite!=null) {
				for(int i=0;i<voSite.size();i++) {
					voColecao = ConsultaColecoes("",Descricao_Colecao,voSite.get(i).getId());
					if (voColecao!=null) {
						Gson gson = new Gson();
						retorno = retorno==null?"":retorno;
						retorno += gson.toJson(voColecao);
						retorno += "\n";
					}
				}
			}
		}catch (Exception e) {		
			LOGGER.error("Erro não previsto ao tentar executar um select na tabela de Colecoes!", e);
			throw e;
		}
		return retorno;
	}
	
	/**
	 * Metodo para update Colecoes
	 * @throws Exception 
	 */
	public void updateColecao(String Descricao, String Descricao_Site, String Descricao_to, String Descricao_Site_to) throws Exception{
		
		LOGGER.info("### Inicio do Update do Colecao " + Descricao + " para " + Descricao_to);
		try {
			String Id_Site = "";
			ArrayList<SiteVo> voSite = ConsultaSites("", Descricao_Site);
			for (int i=0;i<voSite.size();i++) {
				Id_Site = voSite.get(i).getId();
				break;
			}
			String Id_Site_to = "";
			ArrayList<SiteVo> voSiteto = ConsultaSites("", Descricao_Site_to);
			for (int i=0;i<voSiteto.size();i++) {
				Id_Site_to = voSiteto.get(i).getId();
				break;
			}
			String update_Colecao = "";
			if (Descricao_to!=null||Descricao_Site_to!=null) {
				if (Descricao_to!=null) {
					update_Colecao = "Update Colecoes set Descricao = '" + Descricao_to + "'";
					if (Descricao_Site_to!=null) {
						update_Colecao = ", Id_Site = " + Id_Site_to;
					}
				} else { //Descricao_Site_to!=null
					update_Colecao = "Update Colecoes set Id_Site = " + Id_Site_to;
				}
				update_Colecao += " where Descricao = '" + Descricao + "' and Id_Site = " + Id_Site;
			} else {
				LOGGER.error("Falha ao tentar efetuar update na tabela de Colecoes, Descricao e Site não informados para operação!");
				throw new Exception("Falha ao tentar efetuar update na tabela de Colecoes, Descricao e Site não informados para operação!");
			}
			Statement stUpdate_Colecao = null;
			stUpdate_Colecao = connection.createStatement();
			int rowsaffected = stUpdate_Colecao.executeUpdate(update_Colecao);
			if (rowsaffected<=0) {
				LOGGER.error("Falha ao tentar executar um update na tabela de Colecoes, não econtrado nehum registro para alterar!");
				throw new Exception("Falha ao tentar executar um update na tabela de Colecoes, não econtrado nehum registro para alterar!");
			}
			stUpdate_Colecao.close();
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um update na tabela de Colecoes, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um update na tabela de Colecoes!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para delete Colecoes
	 * @throws Exception 
	 */
	public void deleteColecao(String Descricao, String Descricao_Site) throws Exception{
		
		LOGGER.info("### Inicio do Delete do Colecao " + Descricao);
		try {
			if (Descricao==null) {
				LOGGER.error("Falha ao tentar executar um delete na tabela de Colecoes, não foi informado coleção a ser excluída!");
				throw new Exception("Falha ao tentar executar um delete na tabela de Colecoes, não foi informado coleção a ser excluída!");
			}
			if (Descricao_Site==null) {
				LOGGER.error("Falha ao tentar executar um delete na tabela de Colecoes, não foi informado de qual site pertence a coleção!");
				throw new Exception("Falha ao tentar executar um delete na tabela de Colecoes, não foi informado de qual site pertence a coleção!");
			}
			String Id_Site = "";
			ArrayList<SiteVo> vo = ConsultaSites("", Descricao_Site);
			for (int i=0;i<vo.size();i++) {
				Id_Site = vo.get(i).getId();
				break;
			}
			ArrayList<ColecaoVo> voColecao = ConsultaColecoes("", Descricao, Id_Site);
			String delete_Colecao = "Delete from Colecoes where Descricao = '" + Descricao + "' and Id_Site = " + Id_Site;
			Statement stDelete_Colecao = null;
			stDelete_Colecao = connection.createStatement();
			int rowsaffected = stDelete_Colecao.executeUpdate(delete_Colecao);
			if (rowsaffected<=0) {
				LOGGER.error("Falha ao tentar executar um delete na tabela de Colecoes, não econtrado nehum registro para excluir!");
				throw new Exception("Falha ao tentar executar um delete na tabela de Colecoes, não econtrado nehum registro para excluir!");
			}
			stDelete_Colecao.close();
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um delete na tabela de Colecoes, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um delete na tabela de Colecoes!", e);
			}
			throw e;
		}
	}
	
	/**
	 * Metodo para clean Colecoes
	 * @throws Exception 
	 */
	public void cleanColecao(String Descricao, String Descricao_Site) throws Exception{
		
		LOGGER.info("### Inicio do Clean do Colecao " + Descricao);
		//tem que mudar logica para fazer um delete cascate
		try {
			if (Descricao_Site==null) {
				LOGGER.error("Falha ao tentar executar um clean na tabela de Colecoes, não foi informado de qual site pertence a coleção!");
				throw new Exception("Falha ao tentar executar um clean na tabela de Colecoes, não foi informado de qual site pertence a coleção!");
			}
			String Id_Colecao = "";
			String Id_Site = "";
			ArrayList<SiteVo> vo = ConsultaSites("", Descricao_Site);
			for (int i=0;i<vo.size();i++) {
				Id_Site = vo.get(i).getId();
				break;
			}
			ArrayList<ColecaoVo> voColecao = ConsultaColecoes("", Descricao, Id_Site);
			String clean_Colecao;
			if (Descricao==null) {
				clean_Colecao = "Delete from Colecoes where Id_Site = " + Id_Site;
			} else {
				clean_Colecao = "Delete from Colecoes where Descricao = '" + Descricao + "' and Id_Site = " + Id_Site;
			}
			Statement stClean_Colecao = null;
			stClean_Colecao = connection.createStatement();
			int rowsaffected = stClean_Colecao.executeUpdate(clean_Colecao);
			if (rowsaffected<=0) {
				LOGGER.error("Falha ao tentar executar um clean na tabela de Colecoes, não econtrado nehum registro para limpar!");
				throw new Exception("Falha ao tentar executar um clean na tabela de Colecoes, não econtrado nehum registro para limpar!");
			}
			stClean_Colecao.close();
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um clean na tabela de Colecoes, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um clean na tabela de Colecoes!", e);
			}
			throw e;
		}
	}

	public void insertAssociacoesColecaoGrupo(String colecao_descricao, String grupo_descricao, String site_descricao) throws Exception {

		LOGGER.info("### Inicio do Insert do Colecao " + colecao_descricao + "/" + grupo_descricao);
		try {
			String Id_Site = "";
			ArrayList<SiteVo> voSite = ConsultaSites("", site_descricao);
			for (int i=0;i<voSite.size();i++) {
				Id_Site = voSite.get(i).getId();
				break;
			}
			String Id_Colecao = "";
			ArrayList<ColecaoVo> voColecao = ConsultaColecoes("", colecao_descricao, Id_Site);
			for (int i=0;i<voColecao.size();i++) {
				Id_Colecao = voColecao.get(i).getId();
				break;
			}
			String Id_Grupo = "";
			ArrayList<GrupoVo> voGrupo = ConsultaGrupos("", grupo_descricao, Id_Site);
			for (int i=0;i<voGrupo.size();i++) {
				Id_Grupo = voGrupo.get(i).getId();
				break;
			}
			String insert_AssociacoesColecaoGrupo = "";
			if (!Id_Colecao.contentEquals("")&&!Id_Grupo.contentEquals("")) {
				insert_AssociacoesColecaoGrupo = "Insert into AssociacoesColecaoGrupo  (Id_Colecao, Id_Grupo) values (" + Id_Colecao + "," + Id_Grupo + ")";
			} else {
				LOGGER.error("Falha ao tentar criar uma associação na tabela de AssociacoesColecaoGrupo, Coleção e ou Grupo e ou Site não informados ou invalidos!");
				throw new Exception("Falha ao tentar criar uma associação na tabela de AssociacoesColecaoGrupo, Coleção e ou Grupo e ou Site não informados ou invalidos!");
			}
			Statement stInsert_Colecao = null;
			stInsert_Colecao = connection.createStatement();
			stInsert_Colecao.executeUpdate(insert_AssociacoesColecaoGrupo);
			stInsert_Colecao.close();
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um insert na tabela de Colecoes, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um insert na tabela de Colecoes!", e);
			}
			throw e;
		}
		
	}

	public String selectAssociacoesColecaoGrupo(String colecao_descricao, String grupo_descricao, String site_descricao) throws Exception {

		String retorno = null;
		Statement stConsulta = null;		
		AssociacaoColecaoGrupoVo vo;
		
		String Id_Site = "";
		if (!site_descricao.contentEquals("")) {
			ArrayList<SiteVo> voSite = ConsultaSites("", site_descricao);
			for (int i=0;i<voSite.size();i++) {
				Id_Site = voSite.get(i).getId();
				break;
			}
		}
		String Id_Colecao = "";
		if (!colecao_descricao.contentEquals("")) {
			ArrayList<ColecaoVo> voColecao = ConsultaColecoes("", colecao_descricao, Id_Site);
			for (int i=0;i<voColecao.size();i++) {
				Id_Colecao = voColecao.get(i).getId();
				break;
			}
		}
		String Id_Grupo = "";
		if (!grupo_descricao.contentEquals("")) {
			ArrayList<GrupoVo> voGrupo = ConsultaGrupos("", grupo_descricao, Id_Site);
			for (int i=0;i<voGrupo.size();i++) {
				Id_Grupo = voGrupo.get(i).getId();
				break;
			}
		}
		String consulta;
		if (!Id_Site.contentEquals("")&&
			(!Id_Colecao.contentEquals("")||
			!Id_Grupo.contentEquals("")))
		{
			if (!Id_Colecao.contentEquals("")&&
				!Id_Grupo.contentEquals("")) {
				consulta = "select a.Id, a.Id_Colecao, c.Descricao, a.Id_Grupo, g.Descricao, g.Id_Site, s.Descricao  from AssociacoesColecaoGrupo a, Colecoes c, Grupos g, Sites s ";
				consulta += "where a.Id_Colecao = " + Id_Colecao + " and a.Id_Grupo = " + Id_Grupo + " and g.Id_Site = " + Id_Site;
				consulta += " and g.Id = " + Id_Grupo + " and c.Id = " + Id_Colecao + " and s.Id = " + Id_Site;
			} else {
				if (!Id_Colecao.contentEquals("")) {
					consulta = "select a.Id, a.Id_Colecao, c.Descricao, a.Id_Grupo, g.Descricao, g.Id_Site, s.Descricao  from AssociacoesColecaoGrupo a, Colecoes c, Grupos g, Sites s ";
					consulta += "where a.Id_Colecao = " + Id_Colecao + " and g.Id_Site = " + Id_Site;
					consulta += " and c.Id = " + Id_Colecao + " and s.Id = " + Id_Site;
				} else { //!Id_Grupo.contentEquals("")
					consulta = "select a.Id, a.Id_Colecao, c.Descricao, a.Id_Grupo, g.Descricao, g.Id_Site, s.Descricao  from AssociacoesColecaoGrupo a, Colecoes c, Grupos g, Sites s ";
					consulta += "where a.Id_Grupo = " + Id_Grupo + " and g.Id_Site = " + Id_Site;
					consulta += " and g.Id = " + Id_Grupo + " and s.Id = " + Id_Site;
				}
			}
		} else if (Id_Site.contentEquals("")&&
				Id_Colecao.contentEquals("")&&
				Id_Grupo.contentEquals("")) {
				consulta = "select a.Id, a.Id_Colecao, c.Descricao, a.Id_Grupo, g.Descricao, s.Id, s.Descricao  from AssociacoesColecaoGrupo a, Colecoes c, Grupos g, Sites s ";
				consulta += "where a.Id_Colecao = c.Id and a.Id_Grupo = g.Id and c.Id_Site = s.Id ";
		} else {
			LOGGER.error("Falha ao tentar consultar uma associação na tabela de AssociacoesColecaoGrupo, Coleção e ou Grupo e ou Site não informados ou invalidos!");
			throw new Exception("Falha ao tentar consultar uma associação na tabela de AssociacoesColecaoGrupo, Coleção e ou Grupo e ou Site não informados ou invalidos!");
		}
		consulta += " order by a.Id";
		LOGGER.info("### Executando Consulta: " + consulta);
		stConsulta = connection.createStatement();
		stConsulta.setQueryTimeout(1);
		ResultSet rs = stConsulta.executeQuery(consulta);
		
		ArrayList<AssociacaoColecaoGrupoVo> ret = null;
		if (rs.isBeforeFirst()) {
			ret = new ArrayList<AssociacaoColecaoGrupoVo>();
			LOGGER.info("Varrendo ResultSet retornado...");
			for (; rs.next(); ) {
				vo = new AssociacaoColecaoGrupoVo();
				vo.setId(rs.getString(1)); //Id
				vo.setId_Colecao(rs.getString(2)); //Id_Colecao
				vo.setDescricao_Colecao(rs.getString(3)); //Descricao_Colecao
				vo.setId_Grupo(rs.getString(4)); //Id_Grupo
				vo.setDescricao_Grupo(rs.getString(5)); //Descricao_Grupo
				vo.setId_Site(rs.getString(6)); //Id_Site
				vo.setDescricao_Site(rs.getString(7)); //Descricao_Site
				ret.add(vo);
				LOGGER.info("Inserido Id=" + rs.getString(1) + " Id_Colecao=" + rs.getString(2) + " Descricao_Colecao=" + rs.getString(3) + " Id_Grupo=" + rs.getString(4) + " Descricao_Grupo=" + rs.getString(5) + " Id_Site=" + rs.getString(6) + " Descricao_Site=" + rs.getString(7) + " ao AssocicacaoColecaoGrupoVo");
			}
			if (ret!=null) {
				Gson gson = new Gson();
				retorno = gson.toJson(ret);
				retorno += "\n";
			}
			LOGGER.info("ResultSet retornado varrido!");
		} else {
			LOGGER.info("ResultSet retornado não retornou nenhuma linha!");
		}
		return retorno;
	}

	public void updateAssociacoesColecaoGrupo(String colecao_descricao, String grupo_descricao, String site_descricao,
			String grupo_descricao_to, String colecao_descricao_to, String site_descricao_to) throws Exception
	{
		String Id_Site = "";
		ArrayList<SiteVo> voSite = ConsultaSites("", site_descricao);
		for (int i=0;i<voSite.size();i++) {
			Id_Site = voSite.get(i).getId();
			break;
		}
		String Id_Colecao = "";
		ArrayList<ColecaoVo> voColecao = ConsultaColecoes("", colecao_descricao, Id_Site);
		for (int i=0;i<voColecao.size();i++) {
			Id_Colecao = voColecao.get(i).getId();
			break;
		}
		String Id_Grupo = "";
		ArrayList<GrupoVo> voGrupo = ConsultaGrupos("", grupo_descricao, Id_Site);
		for (int i=0;i<voGrupo.size();i++) {
			Id_Grupo = voGrupo.get(i).getId();
			break;
		}
		if (!Id_Site.contentEquals("")&&
			!Id_Colecao.contentEquals("")&&
			!Id_Grupo.contentEquals(""))
		{
			String Id_Siteto = "";
			ArrayList<SiteVo> voSiteto = ConsultaSites("", site_descricao);
			for (int i=0;i<voSiteto.size();i++) {
				Id_Siteto = voSiteto.get(i).getId();
				break;
			}
			String Id_Colecaoto = "";
			ArrayList<ColecaoVo> voColecaoto = ConsultaColecoes("", colecao_descricao, Id_Site);
			for (int i=0;i<voColecaoto.size();i++) {
				Id_Colecaoto = voColecaoto.get(i).getId();
				break;
			}
			String Id_Grupoto = "";
			ArrayList<GrupoVo> voGrupoto = ConsultaGrupos("", grupo_descricao, Id_Site);
			for (int i=0;i<voGrupoto.size();i++) {
				Id_Grupoto = voGrupoto.get(i).getId();
				break;
			}
			String update_AssociacoesColecaoGrupo = "";
			if (!Id_Siteto.contentEquals("")||
					!Id_Colecaoto.contentEquals("")||
					!Id_Grupoto.contentEquals(""))
			{
				update_AssociacoesColecaoGrupo = "Update AssociacoesColecaoGrupo set";
				if (Id_Colecao.equals("")) {
					update_AssociacoesColecaoGrupo += "Id_Colecao = " + Id_Colecaoto + ",";
				}
				if (Id_Grupo.equals("")) {
					update_AssociacoesColecaoGrupo += "Id_Grupo = " + Id_Grupoto + ",";
				//}
				//if (Id_Grupo.equals("")) {
				//	update_AssociacoesColecaoGrupo += "Id_Site = " + Id_Siteto;
				} else {
					if (update_AssociacoesColecaoGrupo.substring(update_AssociacoesColecaoGrupo.length() -1, 1) == ",") {
						update_AssociacoesColecaoGrupo = update_AssociacoesColecaoGrupo.substring(0, update_AssociacoesColecaoGrupo.length());
					}
				}
				update_AssociacoesColecaoGrupo += ") where";
				update_AssociacoesColecaoGrupo += " Id_Colecao = " + Id_Colecao;
				update_AssociacoesColecaoGrupo += " and Id_Grupo = " + Id_Grupo;
				//update_AssociacoesColecaoGrupo += " and Id_Site ="  + Id_Site;
				Statement stUpdate_AssociacoesColecaoGrupo = null;
				stUpdate_AssociacoesColecaoGrupo = connection.createStatement();
				stUpdate_AssociacoesColecaoGrupo.executeUpdate(update_AssociacoesColecaoGrupo);
				stUpdate_AssociacoesColecaoGrupo.close();
				
			} else {	
				LOGGER.error("Falha ao tentar alterar uma associação na tabela de AssociacoesColecaoGrupo, Coleção e ou Grupo e ou Site destinos não informados ou invalidos!");
				throw new Exception("Falha ao tentar alterar uma associação na tabela de AssociacoesColecaoGrupo, Coleção e ou Grupo e ou Site destinos não informados ou invalidos!");
			}
			
		} else {
			LOGGER.error("Falha ao tentar alterar uma associação na tabela de AssociacoesColecaoGrupo, Coleção e ou Grupo e ou Site não informados ou invalidos!");
			throw new Exception("Falha ao tentar alterar uma associação na tabela de AssociacoesColecaoGrupo, Coleção e ou Grupo e ou Site não informados ou invalidos!");
		}

	}

	public void deleteAssociacoesColecaoGrupo(String colecao_descricao, String grupo_descricao, String site_descricao) throws Exception {
		String Id_Site = "";
		ArrayList<SiteVo> voSite = ConsultaSites("", site_descricao);
		for (int i=0;i<voSite.size();i++) {
			Id_Site = voSite.get(i).getId();
			break;
		}
		String Id_Colecao = "";
		ArrayList<ColecaoVo> voColecao = ConsultaColecoes("", colecao_descricao, Id_Site);
		for (int i=0;i<voColecao.size();i++) {
			Id_Colecao = voColecao.get(i).getId();
			break;
		}
		String Id_Grupo = "";
		ArrayList<GrupoVo> voGrupo = ConsultaGrupos("", grupo_descricao, Id_Site);
		for (int i=0;i<voGrupo.size();i++) {
			Id_Grupo = voGrupo.get(i).getId();
			break;
		}
		if (!Id_Site.contentEquals("")&&
			!Id_Colecao.contentEquals("")&&
			!Id_Grupo.contentEquals("")) 
		{
			String delete_AssociacoesColecaoGrupo = "";
			delete_AssociacoesColecaoGrupo = "Delete from AssociacoesColecaoGrupo ";
			delete_AssociacoesColecaoGrupo += " where Id_Colecao = " + Id_Colecao;
			delete_AssociacoesColecaoGrupo += " and Id_Grupo = " + Id_Grupo;
			//delete_AssociacoesColecaoGrupo += " and Id_Site ="  + Id_Site;
			Statement stDelete_AssociacoesColecaoGrupo = null;
			stDelete_AssociacoesColecaoGrupo = connection.createStatement();
			int rowsaffected = stDelete_AssociacoesColecaoGrupo.executeUpdate(delete_AssociacoesColecaoGrupo);
			if (rowsaffected<=0) {
				LOGGER.error("Falha ao tentar excluir uma associação na tabela de AssociacoesColecaoGrupo, não econtrado nehum registro para excluir!");
				throw new Exception("Falha ao tentar excluir uma associação na tabela de AssociacoesColecaoGrupo, não econtrado nehum registro para excluir!");
			}
			stDelete_AssociacoesColecaoGrupo.close();
			
		} else {
			LOGGER.error("Falha ao tentar excluir uma associação na tabela de AssociacoesColecaoGrupo, Coleção e ou Grupo e ou Site não informados ou invalidos!");
			throw new Exception("Falha ao tentar excluir uma associação na tabela de AssociacoesColecaoGrupo, Coleção e ou Grupo e ou Site não informados ou invalidos!");
		}
		
	}

	public void cleanAssociacoesColecaoGrupo(String colecao_descricao, String grupo_descricao, String site_descricao) throws Exception {
		String Id_Site = "";
		ArrayList<SiteVo> voSite = ConsultaSites("", site_descricao);
		for (int i=0;i<voSite.size();i++) {
			Id_Site = voSite.get(i).getId();
			break;
		}
		String Id_Colecao = "";
		ArrayList<ColecaoVo> voColecao = ConsultaColecoes("", colecao_descricao, Id_Site);
		for (int i=0;i<voColecao.size();i++) {
			Id_Colecao = voColecao.get(i).getId();
			break;
		}
		String Id_Grupo = "";
		ArrayList<GrupoVo> voGrupo = ConsultaGrupos("", grupo_descricao, Id_Site);
		for (int i=0;i<voGrupo.size();i++) {
			Id_Grupo = voGrupo.get(i).getId();
			break;
		}
		if (!Id_Site.contentEquals("")&&
			!Id_Colecao.contentEquals("")&&
			!Id_Grupo.contentEquals("")) 
		{
			String clean_AssociacoesColecaoGrupo = "";
			clean_AssociacoesColecaoGrupo = "Delete from AssociacoesColecaoGrupo ";
			clean_AssociacoesColecaoGrupo += " where Id_Colecao = " + Id_Colecao;
			clean_AssociacoesColecaoGrupo += " and Id_Grupo = " + Id_Grupo;
			//clean_AssociacoesColecaoGrupo += " and Id_Site ="  + Id_Site;
			Statement stClean_AssociacoesColecaoGrupo = null;
			stClean_AssociacoesColecaoGrupo = connection.createStatement();
			int rowsaffected = stClean_AssociacoesColecaoGrupo.executeUpdate(clean_AssociacoesColecaoGrupo);
			if (rowsaffected<=0) {
				LOGGER.error("Falha ao tentar executar um clean em uma associação na tabela de AssociacoesColecaoGrupo, não econtrado nehum registro para excluir!");
				throw new Exception("Falha ao tentar executar um clean em uma associação na tabela de AssociacoesColecaoGrupo, não econtrado nehum registro para excluir!");
			}
			stClean_AssociacoesColecaoGrupo.close();
			
		} else {
			LOGGER.error("Falha ao tentar executar um clean em uma associação na tabela de AssociacoesColecaoGrupo, Coleção e ou Grupo e ou Site não informados ou invalidos!");
			throw new Exception("Falha ao tentar executar um clean em uma associação na tabela de AssociacoesColecaoGrupo, Coleção e ou Grupo e ou Site não informados ou invalidos!");
		}
		
	}

	public void insertAssociacoesColecaoColecao(String colecaoPai_descricao, String ColecaoFilho_descricao, String site_descricao) throws Exception {

		LOGGER.info("### Inicio do Insert do Colecao " + colecaoPai_descricao + "/" + ColecaoFilho_descricao);
		try {
			String Id_Site = "";
			ArrayList<SiteVo> voSite = ConsultaSites("", site_descricao);
			for (int i=0;i<voSite.size();i++) {
				Id_Site = voSite.get(i).getId();
				break;
			}
			String Id_ColecaoPai = "";
			ArrayList<ColecaoVo> voColecaoPai = ConsultaColecoes("", colecaoPai_descricao, Id_Site);
			for (int i=0;i<voColecaoPai.size();i++) {
				Id_ColecaoPai = voColecaoPai.get(i).getId();
				break;
			}
			String Id_ColecaoFilho = "";
			ArrayList<ColecaoVo> voColecaoFilho = ConsultaColecoes("", ColecaoFilho_descricao, Id_Site);
			for (int i=0;i<voColecaoFilho.size();i++) {
				Id_ColecaoFilho = voColecaoFilho.get(i).getId();
				break;
			}
			String insert_AssociacoesColecaoColecao = "";
			if (!Id_ColecaoPai.contentEquals("")&&!Id_ColecaoFilho.contentEquals("")) {
				insert_AssociacoesColecaoColecao = "Insert into AssociacoesColecaoColecao  (Id_ColecaoPai, Id_ColecaoFilho) values (" + Id_ColecaoPai + "," + Id_ColecaoFilho + ")";
			} else {
				LOGGER.error("Falha ao tentar criar uma associação na tabela de AssociacoesColecaoColecao, ColeçãoPai e ou ColeçãoFilho e ou Site não informados ou invalidos!");
				throw new Exception("Falha ao tentar criar uma associação na tabela de AssociacoesColecaoColecao, ColeçãoPai e ou ColeçãoFilho e ou Site não informados ou invalidos!");
			}
			Statement stInsert_Colecao = null;
			stInsert_Colecao = connection.createStatement();
			stInsert_Colecao.executeUpdate(insert_AssociacoesColecaoColecao);
			stInsert_Colecao.close();
		}catch (Exception e) {		
			if (e.getMessage().substring(0, 26).contentEquals("[SQLITE_CONSTRAINT_UNIQUE]")) {
				LOGGER.error("Falha ao tentar executar um insert na tabela de Colecoes, registro já existente!", e);
			} else {
				LOGGER.error("Erro não previsto ao tentar executar um insert na tabela de Colecoes!", e);
			}
			throw e;
		}
		
	}

	public String selectAssociacoesColecaoColecao(String colecaoPai_descricao, String ColecaoFilho_descricao, String site_descricao) throws Exception {

		String retorno = null;
		Statement stConsulta = null;		
		AssociacaoColecaoColecaoVo vo;
		
		String Id_Site = "";
		if (!site_descricao.contentEquals("")) {
			ArrayList<SiteVo> voSite = ConsultaSites("", site_descricao);
			for (int i=0;i<voSite.size();i++) {
				Id_Site = voSite.get(i).getId();
				break;
			}
		}
		String Id_ColecaoPai = "";
		if (!colecaoPai_descricao.contentEquals("")) {
			ArrayList<ColecaoVo> voColecaoPai = ConsultaColecoes("", colecaoPai_descricao, Id_Site);
			for (int i=0;i<voColecaoPai.size();i++) {
				Id_ColecaoPai = voColecaoPai.get(i).getId();
				break;
			}
		}
		String Id_ColecaoFilho = "";
		if (!ColecaoFilho_descricao.contentEquals("")) {
			ArrayList<ColecaoVo> voColecaoFilho = ConsultaColecoes("", ColecaoFilho_descricao, Id_Site);
			for (int i=0;i<voColecaoFilho.size();i++) {
				Id_ColecaoFilho = voColecaoFilho.get(i).getId();
				break;
			}
		}
		String consulta = "";
		if (!Id_Site.contentEquals("")&&
			(!Id_ColecaoPai.contentEquals("")||
			!Id_ColecaoFilho.contentEquals("")))
		{
			if (!Id_ColecaoPai.contentEquals("")&&
				!Id_ColecaoFilho.contentEquals("")) {
				consulta = "select a.Id, a.Id_ColecaoPai, p.Descricao, a.Id_ColecaoFilho, f.Descricao, s.Id, s.Descricao  from AssociacoesColecaoColecao a, Colecoes p, Colecoes f, Sites s ";
				consulta += "where Id_ColecaoPai = " + Id_ColecaoPai + " and Id_ColecaoFilho = " + Id_ColecaoFilho + " and p.Id_Site = " + Id_Site;
				consulta += " and p.Id = " + Id_ColecaoPai + " and f.Id = " + Id_ColecaoFilho + " and s.Id = " + Id_Site;
			} else {
				if (!Id_ColecaoPai.contentEquals("")) {
					consulta = "select a.Id, a.Id_ColecaoPai, p.Descricao, a.Id_ColecaoFilho, f.Descricao, s.Id, s.Descricao  from AssociacoesColecaoColecao a, Colecoes p, Colecoes f, Sites s ";
					consulta += "where Id_ColecaoPai = " + Id_ColecaoPai + " and p.Id_Site = " + Id_Site;
					consulta += " and p.Id = " + Id_ColecaoPai + " and s.Id = " + Id_Site;
				} else { //!Id_ColecaoFilho.contentEquals("")
					consulta = "select a.Id, a.Id_ColecaoPai, p.Descricao, a.Id_ColecaoFilho, f.Descricao, s.Id, s.Descricao  from AssociacoesColecaoColecao a, Colecoes p, Colecoes f, Sites s ";
					consulta += "where Id_ColecaoFilho = " + Id_ColecaoFilho + " and p.Id_Site = " + Id_Site;
					consulta += " and f.Id = " + Id_ColecaoFilho + " and s.Id = " + Id_Site;
				}
			}
		} else if (Id_Site.contentEquals("")&&
					Id_ColecaoPai.contentEquals("")&&
					Id_ColecaoFilho.contentEquals("")) {
			consulta = "select a.Id, a.Id_ColecaoPai, p.Descricao, a.Id_ColecaoFilho, f.Descricao, s.Id, s.Descricao  from AssociacoesColecaoColecao a, Colecoes p, Colecoes f, Sites s ";
			consulta += "where p.Id_Site = s.Id and f.Id_Site = s.Id ";
			consulta += "and a.Id_ColecaoPai = p.Id and a.Id_ColecaoFilho = f.Id";
		} else {
			LOGGER.error("Falha ao tentar consultar uma associação na tabela de AssociacoesColecaoColecao, ColeçãoPai e ou ColeçãoFilho e ou Site não informados ou invalidos!");
			throw new Exception("Falha ao tentar consultar uma associação na tabela de AssociacoesColecaoColecao, ColeçãoPai e ou ColeçãoFilho e ou Site não informados ou invalidos!");
		}
		consulta += " order by a.Id";
		LOGGER.info("### Executando Consulta: " + consulta);
		stConsulta = connection.createStatement();
		stConsulta.setQueryTimeout(1);
		ResultSet rs = stConsulta.executeQuery(consulta);
		ArrayList<AssociacaoColecaoColecaoVo> ret = null;
		if (rs.isBeforeFirst()) {
			ret = new ArrayList<AssociacaoColecaoColecaoVo>();
			LOGGER.info("Varrendo ResultSet retornado...");
			for (; rs.next(); ) {
				vo = new AssociacaoColecaoColecaoVo();
				vo.setId(rs.getString(1)); //Id
				vo.setId_ColecaoPai(rs.getString(2)); //Id_ColecaoPai
				vo.setDescricao_ColecaoPai(rs.getString(3)); //Descricao_Colecao
				vo.setId_ColecaoFilho(rs.getString(4)); //Id_ColecaoFilho
				vo.setDescricao_ColecaoFilho(rs.getString(5)); //Descricao_ColeçãoFilho
				vo.setId_Site(rs.getString(6)); //Id_Site
				vo.setDescricao_Site(rs.getString(7)); //Descricao_Site
				ret.add(vo);
				LOGGER.info("Inserido Id=" + rs.getString(1) + " Id_ColecaoPai=" + rs.getString(2) + " Descricao_Colecao=" + rs.getString(3) + " Id_ColecaoFilho=" + rs.getString(4) + " Descricao_ColeçãoFilho=" + rs.getString(5) + " Id_Site=" + rs.getString(6) + " Descricao_Site=" + rs.getString(7) + " ao AssocicacaoColecaoColecaoVo");
			}
			if (ret!=null) {
				Gson gson = new Gson();
				retorno = gson.toJson(ret);
				retorno += "\n";
			}
			LOGGER.info("ResultSet retornado varrido!");
		} else {
			LOGGER.info("ResultSet retornado não retornou nenhuma linha!");
		}
		return retorno;
	}

	public void updateAssociacoesColecaoColecao(String colecaoPai_descricao, String ColecaoFilho_descricao, String site_descricao,
			String ColecaoFilho_descricao_to, String colecaoPai_descricao_to, String site_descricao_to) throws Exception
	{
		String Id_Site = "";
		ArrayList<SiteVo> voSite = ConsultaSites("", site_descricao);
		for (int i=0;i<voSite.size();i++) {
			Id_Site = voSite.get(i).getId();
			break;
		}
		String Id_ColecaoPai = "";
		ArrayList<ColecaoVo> voColecao = ConsultaColecoes("", colecaoPai_descricao, Id_Site);
		for (int i=0;i<voColecao.size();i++) {
			Id_ColecaoPai = voColecao.get(i).getId();
			break;
		}
		String Id_ColecaoFilho = "";
		ArrayList<ColecaoVo> voColecaoFilho = ConsultaColecoes("", ColecaoFilho_descricao, Id_Site);
		for (int i=0;i<voColecaoFilho.size();i++) {
			Id_ColecaoFilho = voColecaoFilho.get(i).getId();
			break;
		}
		if (!Id_Site.contentEquals("")&&
			!Id_ColecaoPai.contentEquals("")&&
			!Id_ColecaoFilho.contentEquals(""))
		{
			String Id_Siteto = "";
			ArrayList<SiteVo> voSiteto = ConsultaSites("", site_descricao);
			for (int i=0;i<voSiteto.size();i++) {
				Id_Siteto = voSiteto.get(i).getId();
				break;
			}
			String Id_ColecaoPaito = "";
			ArrayList<ColecaoVo> voColecaoPaito = ConsultaColecoes("", colecaoPai_descricao, Id_Site);
			for (int i=0;i<voColecaoPaito.size();i++) {
				Id_ColecaoPaito = voColecaoPaito.get(i).getId();
				break;
			}
			String Id_ColecaoFilhoto = "";
			ArrayList<ColecaoVo> voColecaoFilhoto = ConsultaColecoes("", ColecaoFilho_descricao, Id_Site);
			for (int i=0;i<voColecaoFilhoto.size();i++) {
				Id_ColecaoFilhoto = voColecaoFilhoto.get(i).getId();
				break;
			}
			String update_AssociacoesColecaoColecao = "";
			if (!Id_Siteto.contentEquals("")||
					!Id_ColecaoPaito.contentEquals("")||
					!Id_ColecaoFilhoto.contentEquals(""))
			{
				update_AssociacoesColecaoColecao = "Update AssociacoesColecaoColecao set";
				if (Id_ColecaoPai.equals("")) {
					update_AssociacoesColecaoColecao += "Id_ColecaoPai = " + Id_ColecaoPai + ",";
				}
				if (Id_ColecaoFilho.equals("")) {
					update_AssociacoesColecaoColecao += "Id_ColecaoFilho = " + Id_ColecaoFilho + ",";
				//}
				//if (Id_ColecaoFilho.equals("")) {
				//	update_AssociacoesColecaoColecao += "Id_Site = " + Id_Site;
				} else {
					if (update_AssociacoesColecaoColecao.substring(update_AssociacoesColecaoColecao.length() -1, 1) == ",") {
						update_AssociacoesColecaoColecao = update_AssociacoesColecaoColecao.substring(0, update_AssociacoesColecaoColecao.length());
					}
				}
				update_AssociacoesColecaoColecao += ") where";
				update_AssociacoesColecaoColecao += " Id_ColecaoPai = " + Id_ColecaoPai;
				update_AssociacoesColecaoColecao += " and Id_ColecaoFilho = " + Id_ColecaoFilho;
				//update_AssociacoesColecaoColecao += " and Id_Site ="  + Id_Site;
				Statement stUpdate_AssociacoesColecaoColecao = null;
				stUpdate_AssociacoesColecaoColecao = connection.createStatement();
				stUpdate_AssociacoesColecaoColecao.executeUpdate(update_AssociacoesColecaoColecao);
				stUpdate_AssociacoesColecaoColecao.close();
				
			} else {	
				LOGGER.error("Falha ao tentar alterar uma associação na tabela de AssociacoesColecaoColecao, ColeçãoPai e ou ColeçãoFilho e ou Site destinos não informados ou invalidos!");
				throw new Exception("Falha ao tentar alterar uma associação na tabela de AssociacoesColecaoColecao, ColeçãoPai e ou ColeçãoFilho e ou Site destinos não informados ou invalidos!");
			}
			
		} else {
			LOGGER.error("Falha ao tentar alterar uma associação na tabela de AssociacoesColecaoColecao, ColeçãoPai e ou ColeçãoFilho e ou Site não informados ou invalidos!");
			throw new Exception("Falha ao tentar alterar uma associação na tabela de AssociacoesColecaoColecao, ColeçãoPai e ou ColeçãoFilho e ou Site não informados ou invalidos!");
		}

	}

	public void deleteAssociacoesColecaoColecao(String colecaoPai_descricao, String ColecaoFilho_descricao, String site_descricao) throws Exception {
		String Id_Site = "";
		ArrayList<SiteVo> voSite = ConsultaSites("", site_descricao);
		for (int i=0;i<voSite.size();i++) {
			Id_Site = voSite.get(i).getId();
			break;
		}
		String Id_ColecaoPai = "";
		ArrayList<ColecaoVo> voColecaoPai = ConsultaColecoes("", colecaoPai_descricao, Id_Site);
		for (int i=0;i<voColecaoPai.size();i++) {
			Id_ColecaoPai = voColecaoPai.get(i).getId();
			break;
		}
		String Id_ColecaoFilho = "";
		ArrayList<ColecaoVo> voColecaoFilho = ConsultaColecoes("", ColecaoFilho_descricao, Id_Site);
		for (int i=0;i<voColecaoFilho.size();i++) {
			Id_ColecaoFilho = voColecaoFilho.get(i).getId();
			break;
		}
		if (!Id_Site.contentEquals("")&&
			!Id_ColecaoPai.contentEquals("")&&
			!Id_ColecaoFilho.contentEquals("")) 
		{
			String delete_AssociacoesColecaoColecao = "";
			delete_AssociacoesColecaoColecao = "Delete from AssociacoesColecaoColecao ";
			delete_AssociacoesColecaoColecao += " where Id_ColecaoPai = " + Id_ColecaoPai;
			delete_AssociacoesColecaoColecao += " and Id_ColecaoFilho = " + Id_ColecaoFilho;
			//delete_AssociacoesColecaoColecao += " and Id_Site ="  + Id_Site;
			Statement stDelete_AssociacoesColecaoColecao = null;
			stDelete_AssociacoesColecaoColecao = connection.createStatement();
			int rowsaffected = stDelete_AssociacoesColecaoColecao.executeUpdate(delete_AssociacoesColecaoColecao);
			if (rowsaffected<=0) {
				LOGGER.error("Falha ao tentar excluir uma associação na tabela de AssociacoesColecaoColecao, não econtrado nehum registro para excluir!");
				throw new Exception("Falha ao tentar excluir uma associação na tabela de AssociacoesColecaoColecao, não econtrado nehum registro para excluir!");
			}
			stDelete_AssociacoesColecaoColecao.close();
			
		} else {
			LOGGER.error("Falha ao tentar excluir uma associação na tabela de AssociacoesColecaoColecao, ColeçãoPai e ou ColeçãoFilho e ou Site não informados ou invalidos!");
			throw new Exception("Falha ao tentar excluir uma associação na tabela de AssociacoesColecaoColecao, ColeçãoPai e ou ColeçãoFilho e ou Site não informados ou invalidos!");
		}
		
	}

	public void cleanAssociacoesColecaoColecao(String colecaoPai_descricao, String ColecaoFilho_descricao, String site_descricao) throws Exception {
		String Id_Site = "";
		ArrayList<SiteVo> voSite = ConsultaSites("", site_descricao);
		for (int i=0;i<voSite.size();i++) {
			Id_Site = voSite.get(i).getId();
			break;
		}
		String Id_ColecaoPai = "";
		ArrayList<ColecaoVo> voColecaoPai = ConsultaColecoes("", colecaoPai_descricao, Id_Site);
		for (int i=0;i<voColecaoPai.size();i++) {
			Id_ColecaoPai = voColecaoPai.get(i).getId();
			break;
		}
		String Id_ColecaoFilho = "";
		ArrayList<ColecaoVo> voColecaoFilho = ConsultaColecoes("", ColecaoFilho_descricao, Id_Site);
		for (int i=0;i<voColecaoFilho.size();i++) {
			Id_ColecaoFilho = voColecaoFilho.get(i).getId();
			break;
		}
		if (!Id_Site.contentEquals("")&&
			!Id_ColecaoPai.contentEquals("")&&
			!Id_ColecaoFilho.contentEquals("")) 
		{
			String clean_AssociacoesColecaoColecao = "";
			clean_AssociacoesColecaoColecao = "Delete from AssociacoesColecaoColecao ";
			clean_AssociacoesColecaoColecao += " where Id_ColecaoPai = " + Id_ColecaoPai;
			clean_AssociacoesColecaoColecao += " and Id_ColecaoFilho = " + Id_ColecaoFilho;
			//clean_AssociacoesColecaoColecao += " and Id_Site ="  + Id_Site;
			Statement stClean_AssociacoesColecaoColecao = null;
			stClean_AssociacoesColecaoColecao = connection.createStatement();
			int rowsaffected = stClean_AssociacoesColecaoColecao.executeUpdate(clean_AssociacoesColecaoColecao);
			if (rowsaffected<=0) {
				LOGGER.error("Falha ao tentar executar um clean em uma associação na tabela de AssociacoesColecaoColecao, não econtrado nehum registro para excluir!");
				throw new Exception("Falha ao tentar executar um clean em uma associação na tabela de AssociacoesColecaoColecao, não econtrado nehum registro para excluir!");
			}
			stClean_AssociacoesColecaoColecao.close();
			
		} else {
			LOGGER.error("Falha ao tentar executar um clean em uma associação na tabela de AssociacoesColecaoColecao, ColeçãoPai e ou ColeçãoFilho e ou Site não informados ou invalidos!");
			throw new Exception("Falha ao tentar executar um clean em uma associação na tabela de AssociacoesColecaoColecao, ColeçãoPai e ou ColeçãoFilho e ou Site não informados ou invalidos!");
		}
		
	}

}
