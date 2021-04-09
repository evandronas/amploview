package com.amplo.view;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;

/**
 * @author Evandro
 */
@RestController
public class ViewAmplo {
	
	public AmploViewDao dao;
    final Logger LOGGER = LogManager.getLogger(ViewAmplo.class);
	
	ViewAmplo() throws ClassNotFoundException, NamingException, SQLException
	{
        dao = new AmploViewDao("amploview");
        boolean statusAutocommit = dao.connection.getAutoCommit();
        dao.connection.setAutoCommit(false);
	}

	String MenuAmploView(String Gateway, String Porta, String DoisPontos)
	{
		String out = "";
        out += "<!-- Aqui inicia submenuprincipal -->";
        out += "<ul>";
        out += "<li><a href=\"" + Gateway + DoisPontos + Porta + "/amploview?amploview=Serviços&gateway=" + Gateway + "&porta=" + Porta + "\">Serviços</a>";
        out += "</li>";
        out += "<li><a href=\"" + Gateway + DoisPontos + Porta + "/amploview?amploview=Projetos&gateway=" + Gateway + "&porta=" + Porta + "\">Projetos</a>";
        out += "</li>";
        out += "<li><a href=\"" + Gateway + DoisPontos + Porta + "/amploview?amploview=Clientes&gateway=" + Gateway + "&porta=" + Porta + "\">Clientes</a>";
        out += "</li>";
        out += "<li><a href=\"" + Gateway + DoisPontos + Porta + "/amploview?amploview=Sobre nós&gateway=" + Gateway + "&porta=" + Porta + "\">Sobre nós</a>";
        out += "</li>";
        out += "<li><a href=\"" + Gateway + DoisPontos + Porta + "/amploview?amploview=Fale conosco&gateway=" + Gateway + "&porta=" + Porta + "\">Fale conosco</a>";
        out += "</li>";
        out += "</ul>";
        out += "<!-- Aqui termina submenuprincipal -->";
        return out;
	}
	
	String PrincipalAmploView()
	{
		String out = "";
		out += "<div>";
		out += "<p align=\"center\">";
		out += "<h1>AmploView</h1><br>";
		out += "APLICANDO NOVAS TECNOLOGIAS<br>";
		out += "OTIMIZANDO CUSTOS<br>";
		out += "</p>";
		out += "<p align=\"center\"><img src=\"/images/fotoglobo.jpg\" style=\"width:408px;height:332px;\"></p>";
		out += "<p align=\"center\">";
		out += "</p>";
		out += "</div>";
		return out;
	}
	
	String ServicosAmploView()
	{
		String out = "";
		out += "<section id=\"servicos\">";
		out += "<div>";
		out += "<p align=\"center\">";
		out += "<frame>";
		out += "<h4>Serviços</h4>";
		out += "Pacote de sessão de fotos 360º com hospedagem.<br>Confecção de tour virtual através de fotos 360º.<br>Hospedagem adicional de acervo de fotos já disponíveis no sistema.<br>Maiores detalhes, basta entrar em contato para saber como podemos te ajudar.<br>";
		out += "<br>";
		out += "</frame>";
		out += "</p>";
		out += "</div>";
		out += "<div>";
		out += "<p align=\"center\">";
		out += "<h1>Pacote de 10 fotos 360º + Hospedagem</h1>";
		out += "</p>";
		out += "<p align=\"center\"><img src=\"/images/Servicos01.jpg\" style=\"width:408px;height:332px;\"></p>";
		out += "</div>";
		out += "<div>";
		out += "<p align=\"center\">";
		out += "<h1>Pacote de 10 fotos 360º + Hospedagem com navegação no ambiente</h1>";
		out += "</p>";
		out += "<p align=\"center\"><img src=\"/images/Servicos02.jpg\" style=\"width:408px;height:332px;\"></p>";
		out += "</div>";
		out += "<div>";
		out += "<p align=\"center\">";
		out += "<h1>Renovação da hospedagem por mais 90 dias</h1>";
		out += "</p>";
		out += "<p align=\"center\"><img src=\"/images/Servicos03.jpg\" style=\"width:408px;height:332px;\"></p>";
		out += "</div>";
		out += "</section>";
		return out;
	}
	
	String ProjetosAmploView()
	{
		String out = "";
		out += "<section id=\"projetos\">";
		out += "<div>";
		out += "<p align=\"center\">";
		out += "<frame>";
		out += "<h4>Projetos</h4>";
		out += "Projetos de imagens em 360° voltados para sites especializados.<br>";
		out += "<br>";
		out += "</frame>";
		out += "</p>";
		out += "</div>";
		out += "</section>";
		return out;
	}
	
	String ClientesAmploView()
	{
		String out = "";
		out += "<section id=\"clientes\">";
		out += "<div>";
		out += "<p align=\"center\">";
		out += "<frame>";
		out += "<h4>Clientes</h4>";
		out += "Imobiliárias, hotéis, pousadas,<br>espaços para eventos e ou trabalho compartilhado,<br>bares, restaurantes, escolas e etc.<br>";
		out += "<br>";
		out += "</frame>";
		out += "</p>";
		out += "</div>";
		out += "</section>";
		return out;
	}

	String SobreNosAmploView()
	{
		String out = "";
		out += "<section id=\"sobrenos\">";
		out += "<div>";
		out += "<p align=\"center\">";
		out += "<frame>";
		out += "<h4>SOBRE NÓS</h4>";
		out += "Atuamos de forma diferenciada na divulgação de seu negócio<br>através de tecnologias inovadoras e voltadas para tal finalidade.<br><br>";
		out += "Temos como missão prover soluções diferenciadas que agreguem<br>maior valor nos negócios de nossos clientes por meio de tecnologias avançadas.<br><br>";
		out += "Nossa visão é em um breve futuro se tornar referência em projetos de divulgação<br>através de meios tecnológicos especializados.<br><br>";
		out += "Nossos valores são além de outros, a busca excessiva na excelência em nossas atividades<br>com empatia nas relações profissionais junto aos nossos clientes, fornecedores e colaboradores.<br>";
		out += "<br>";
		out += "</frame>";
		out += "</p>";
		out += "</div>";
		out += "</section>";
		return out;
	}
		
	String FaleConoscoAmploView()
	{
		String out = "";
		out += "<section id=\"faleconosco\">";
		out += "<div>";
		out += "<p align=\"center\">";
		out += "<frame>";
		out += "<h4>Fale conosco</h4>";
		out += "Rio de Janeiro - RJ, Brasil<br>";
		out += "amploview@gmail.com<br>";
		out += "Cel.: (21) 96807-9598<br>";
		out += "Cel.: (21) 99643-0379<br>";
		out += "<br>";
		out += "</frame>";
		out += "<p align=\"center\"><img src=\"/images/mapafaleconosco.jpg\" style=\"width:408px;height:332px;\"></p>";
		out += "<p align=\"center\">";
		out += "</p>";
		out += "</div>";
		out += "</section>";
		return out;
	}
	
	@RequestMapping(value="/amploview")
	@ResponseBody
	public String method_principal(HttpServletRequest request) throws Exception
	{
	      String out = "";
	      out += "<!DOCTYPE html>\n";
	      out += "<style>\n";
	      out += "table, th, td {\n";
	      out += "    border: 1px solid black;\n";
	      out += "}\n";
	      out += "</style>\n";
	      out += "<html>\n";
	      out += "<head>\n";
	      out += "<title> </title>\n";
	      out += "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />\n";
		  out += "<meta name=\"description\" content=\"\" />\n";
		  out += "<meta name=\"keywords\" content=\"\" />\n";
		  out += "<!--[if lte IE 8]><script src=\"css/ie/html5shiv.js\"></script><![endif]-->\n";
		  out += "<script src=\"js/jquery.min.js\"></script>\n";
		  out += "<script src=\"js/jquery.dropotron.min.js\"></script>\n";
		  out += "<script src=\"js/jquery.scrollgress.min.js\"></script>\n";
		  out += "<script src=\"js/jquery.scrolly.min.js\"></script>\n";
		  out += "<script src=\"js/jquery.slidertron.min.js\"></script>\n";
		  out += "<script src=\"js/skel.min.js\"></script>\n";
		  out += "<script src=\"js/skel-layers.min.js\"></script>\n";
		  out += "<script src=\"js/init.js\"></script>\n";
		  out += "<noscript>\n";
		  out += "<link rel=\"stylesheet\" href=\"css/skel.css\" />\n";
		  out += "<link rel=\"stylesheet\" href=\"css/style.css\" />\n";
		  out += "<link rel=\"stylesheet\" href=\"css/style-xlarge.css\" />\n";
		  out += "</noscript>\n";
		  out += "<!--[if lte IE 9]><link rel=\"stylesheet\" href=\"css/ie/v9.css\" /><![endif]-->\n";
		  out += "<!--[if lte IE 8]><link rel=\"stylesheet\" href=\"css/ie/v8.css\" /><![endif]-->\n";
		  out += "</head>\n";
		  out += "<body class=\"landing\">\n";
		  out += "<!-- Header -->\n";
		  out += "<header id=\"header\" class=\"alt skel-layers-fixed\">\n";
		  out += "<div>";
		  out += "<p align=\"left\"><img src=\"/images/logodeitada.jpg\" style=\"width:204px;height:43px;\"></p>";
		  out += "</div>";
		  out += "<nav id=\"nav\">\n";
		  out += "<ul>\n";
			ArrayList<SiteVo> voSite = null;
			ArrayList<GrupoVo> voGrupo = null;
			ArrayList<ElementoVo> voElemento = null;
			ArrayList<AtributoVo> voAtributo = null;
			ArrayList<ValorVo> voValorDescricao = null;
			ArrayList<ValorVo> voValorImagem = null;
			try {
				voSite = dao.ConsultaSites("", "");
				voGrupo = dao.ConsultaGrupos("", "", "");
			} catch (Exception e) {
				LOGGER.error("Erro na Carga dos Valores!");
				throw new Exception("Erro na Carga dos Valores!");
			}
			String Id_Site = "", Descricao_Site = "";
			String Id_Grupo = "", Descricao_Grupo = "", Id_Site_Grupo = "";
			String Id_Elemento = "", Descricao_Elemento = "";
			String Id_Atributo = "";
			String Valor_ValorDescricao = "", Valor_ValorImagem = "";
			String Gateway = (String) request.getParameter("gateway");
			String URLFull = (String) request.getRequestURL().toString(); //Pega a URL full para codificar pegar subdominio/gateway/porta
			if (Gateway==null) {
			  Gateway = request.getLocalAddr();
			} else if (Gateway.contentEquals("")) {
			  Gateway = request.getLocalAddr();
			}
			if (!Gateway.substring(0, 4).toUpperCase().contentEquals("HTTP")) {
				Gateway = "http://" + Gateway;
			}
			String Porta = (String) request.getParameter("porta");
			if (Porta==null) {
			  Porta = "";
			}
			String DoisPontos = "";
			if (Porta.length()>1) {
				if (!Porta.substring(0, 1).contentEquals(":")) {
					DoisPontos = ":";
				}
			}
			out += "<li>\n";
			out += "<!-- Aqui inicia submenu amploview -->";
	        out += "<a href=\"" + Gateway + DoisPontos + Porta + "/amploview?gateway=" + Gateway + "&porta=" + Porta + "\" class=\"icon fa-angle-down\">AmploView</a>\n";
			out += "<ul><li><a href=\"\">Opções</a>\n";
			out += MenuAmploView(Gateway, Porta, DoisPontos); //Menu AmploView
			out += "</li>";
			out += "</ul>";
			out += "<!-- Aqui termina submenu amploview -->";
			for (int i=0;i<voSite.size();i++) {
				Id_Site = voSite.get(i).getId();
				Descricao_Site = voSite.get(i).getDescricao();
				out += "\t<li><a href=\"#banner" + i + "\">";
				out += Descricao_Site;
				out += "</a>\n";
				out += "\t\t<ul>\n";
				for (int j=0;j<voGrupo.size();j++) {
					Id_Site_Grupo = voGrupo.get(j).getId_Site();
					if (Id_Site_Grupo.contentEquals(Id_Site)) {
						Id_Grupo = voGrupo.get(j).getId();
						Descricao_Grupo = voGrupo.get(j).getDescricao();
						out += "\t\t\t<li><a href=\"" + Gateway + DoisPontos + Porta + "/amploview?descricao_grupo=" + Descricao_Grupo + "&descricao_site=" + Descricao_Site + "&gateway=" + Gateway + "&porta=" + Porta + "\">";
						out += Descricao_Grupo;
						out += "</a>\n";
						out += "\t\t\t</li>\n";
					}
				}
				out += "</li>\n";
				out += "\t\t</ul>\n";
			}
		  out += "\t</ul>\n";
		  out += "</ul>\n";
		  out += "<ul>\n";
		  out += "</nav>\n";
		  out += "</header>\n";
		  out += "<!-- Banner -->\n";
		  out += "<section id=\"banner\">\n";
		  out += "<div class=\"inner\">\n";
		  Descricao_Grupo = (String) request.getParameter("descricao_grupo");
		  Descricao_Site = (String) request.getParameter("descricao_site");
		  if (Descricao_Grupo!=null && Descricao_Site!=null) {
				voSite = dao.ConsultaSites("", Descricao_Site);
				Id_Site = voSite.get(0).getId();
				voGrupo = dao.ConsultaGrupos("", Descricao_Grupo, Id_Site);
				Id_Grupo = voGrupo.get(0).getId();
				voElemento = dao.ConsultaElementos("", "", Id_Grupo, Id_Site);
				Id_Elemento = voElemento.get(0).getId();
				Descricao_Elemento = voElemento.get(0).getDescricao();
				voAtributo = dao.ConsultaAtributos("", "Descricao", Id_Elemento);
				Id_Atributo = voAtributo.get(0).getId();
				voValorDescricao = dao.ConsultaValores("", "", Id_Atributo);
				voAtributo = dao.ConsultaAtributos("", "Imagem", Id_Elemento);
				Id_Atributo = voAtributo.get(0).getId();
				voValorImagem = dao.ConsultaValores("", "", Id_Atributo);
				String Title = ""; 
				String Author = "AmploView";
				String Imagem = "";
				for (int m=0;m<voValorImagem.size();m++) {
					Valor_ValorImagem = voValorImagem.get(m).getValor();
					Valor_ValorDescricao = voValorDescricao.get(m).getValor();
					Title = Descricao_Site + " " + Descricao_Grupo + " " + Descricao_Elemento + " " + Valor_ValorDescricao;
					Imagem = Valor_ValorImagem.substring(4);
					//out += "<div><iframe id=\"iframe\" width=\"480\" height=\"390\" allowfullscreen style=\"border-style:none;\" src=\"/pannellum-2.5.6/src/standalone/pannellum.htm#panorama=" + Gateway + DoisPontos + Porta + Imagem + "&title=" + Title + "&author=" + Author + "&autoLoad=true\"></iframe></div>\n";
					//out += "<div><iframe id=\"iframe\" width=\"408\" height=\"332\" allowfullscreen style=\"border-style:none;\" src=\"/pannellum-2.5.6/src/standalone/pannellum.htm#panorama=" + Gateway + DoisPontos + Porta + Imagem + "&title=" + Title + "&author=" + Author + "&autoLoad=true\"></iframe></div>\n";
					out += "<div><iframe id=\"iframe\" width=\"408\" height=\"332\" allowfullscreen style=\"border-style:none;\" src=\"/pannellum-2.5.6/src/standalone/pannellum.htm#panorama=" + Imagem + "&title=" + Title + "&author=" + Author + "&autoLoad=true\"></iframe></div>\n";
				}
		  } else {
			  String amploview = (String) request.getParameter("amploview");
			  if (amploview == null) {
				  amploview = "";
			  }
	          out += "<!-- Aqui inicia Banner amploview -->";
	          //out += "<div class=\"inner\">";
			  if (amploview.contentEquals("")) {
		          out += "<!-- Aqui inicia PrincipalAmploView -->";
		          out += PrincipalAmploView();
		          out += "<!-- Aqui termina PrincipalAmploView -->";
			  } else {
		          //out += "<div>";
		          if (amploview.contentEquals("Serviços")) {
			          out += "<!-- Aqui inicia ServicosAmploView -->";
			          out += ServicosAmploView();
			          out += "<!-- Aqui termina ServicosAmploView -->";
		          } else if (amploview.contentEquals("Projetos")) {
			          out += "<!-- Aqui inicia ProjetosAmploView -->";
			          out += ProjetosAmploView();
			          out += "<!-- Aqui termina ProjetosAmploView -->";
		          } else if (amploview.contentEquals("Clientes")) {
			          out += "<!-- Aqui inicia ClientesAmploView -->";
			          out += ClientesAmploView();
			          out += "<!-- Aqui termina ClientesAmploView -->";
		          } else if (amploview.contentEquals("Sobre nós")) {
			          out += "<!-- Aqui inicia SobreNosAmploView -->";
			          out += SobreNosAmploView();
			          out += "<!-- Aqui termina SobreNosAmploView -->";
		          } else if (amploview.contentEquals("Fale conosco")) {
			          out += "<!-- Aqui inicia FaleConoscoAmploView -->";
			          out += FaleConoscoAmploView();
			          out += "<!-- Aqui termina FaleConoscoAmploView -->";
		          }
		          //out += "</div>";
			  }
	          //out += "</div>";
	          out += "<!-- Aqui termina Banner amploview -->";
		  }
          out += "</div>\n";
          out += "<div>\n";
		  out += "<p align=\"right\"><img src=\"/images/logodeitada.jpg\" style=\"width:204px;height:43px;\"></p>";
          out += "</div>\n";
          out += "</section>\n";
          out += "</body>\n";
	      out += "</html>\n";
	      
	      System.out.println(out);

	      return out;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value={"/site/{site_descricao}/{operacao}", 
							"/site/{operacao}", 
							"/site/{site_descricao}/{operacao}/{site_descricao_to}"})
	@ResponseBody
	public String method_site(@PathVariable(required = false) String site_descricao, 
							@PathVariable("operacao") String operacao, 
							@PathVariable(required = false) String site_descricao_to, 
							HttpServletRequest request) throws Exception
	{
		RetornoVo voRetorno = null;
		voRetorno = new RetornoVo();
		Gson gson;
		String retorno = null;
        //AmploViewDao dao = new AmploViewDao("/mnt/amploviewDATA/amploview.db");
		try {
			if (operacao.toUpperCase().contentEquals("INSERT")) {
				if (site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um INSERT na tabela de Sites, site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um INSERT na tabela de Sites, site_descricao não informado(s)!");
				}
		        dao.insertSite(site_descricao);
				voRetorno.setJsonOuTxtComplemento("Insert de Site '" + site_descricao + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("SELECT")) {
				if (site_descricao==null) {
					site_descricao = "";
				}
				voRetorno.setJsonOuTxtComplemento(dao.selectSite(site_descricao));
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("UPDATE")) {
				if (site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um UPDATE na tabela de Sites, site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um UPDATE na tabela de Sites, site_descricao não informado(s)!");
				}
				if (((site_descricao_to!=null)&&(!site_descricao.contentEquals(site_descricao_to)))) {
				    dao.updateSite(site_descricao, site_descricao_to);
					voRetorno.setJsonOuTxtComplemento("Update de Site '" + site_descricao + "' para '" + site_descricao_to + "' efetuado com sucesso!");
					voRetorno.setDescricao("Sucesso");
					voRetorno.setCode("200");
					gson = new Gson();
					retorno = gson.toJson(voRetorno);
					retorno += "\n";
				} else {
					dao.connection.rollback();
					LOGGER.error("Falha ao tentar executar um UPDATE na tabela de Sites, tentativa de UPDATE para os mesmos valores existentes!");
					throw new Exception("Falha ao tentar executar um UPDATE na tabela de Sites, tentativa de UPDATE para os mesmos valores existentes!");
				}
			} else if (operacao.toUpperCase().contentEquals("DELETE")) {
				if (site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um DELETE na tabela de Sites, site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um DELETE na tabela de Sites, site_descricao não informado(s)!");
				}
		        dao.deleteSite(site_descricao);
				voRetorno.setJsonOuTxtComplemento("Delete de Site '" + site_descricao + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("CLEAN")) {
				if (site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um CLEAN na tabela de Sites, site_descricao não informado!");
					throw new Exception("Erro ao tentar executar um CLEAN na tabela de Sites, site_descricao não informado!");
				}
				if (site_descricao==null) {
					site_descricao="";
				}
		        dao.cleanSite(site_descricao);
				voRetorno.setJsonOuTxtComplemento("Clean de Site '" + site_descricao + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("HELP")) {
				String [] methodMapping = new Object(){}.getClass().getEnclosingMethod().getAnnotation(RequestMapping.class).value();
				String pattern = "";
				for(int i=0;i<methodMapping.length;i++) {
					pattern += "curl localhost:8080" +methodMapping[i] + "; ";
				}
				pattern = pattern.substring(0, pattern.length() - 2);
				voRetorno.setJsonOuTxtComplemento("Sintax(es): " + pattern);
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else {
				dao.connection.rollback();
				LOGGER.error("Operacao '" + operacao + "' Inválida " + "Operações válidas são INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP, para maiores detalhes execute curl localhost:8080/site/help");
				throw new Exception("Operacao '" + operacao + "' Inválida " + "Operações válidas são INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP, para maiores detalhes execute curl localhost:8080/site/help");
			}
			dao.connection.commit();
		}catch (Exception e) {		
			dao.connection.rollback();
			LOGGER.error("Erro ao tentar executar um INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP na tabela de Sites!", e);
			throw e;
		}
        //dao.Finalizar();
		return retorno;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value={"/grupo/{grupo_descricao}/{site_descricao}/{operacao}", 
							"/grupo/{operacao}", 
							"/grupo/grupo@{grupo_descricao}/{operacao}", 
							"/grupo/site@{site_descricao}/{operacao}", 
							"/grupo/{grupo_descricao}/{site_descricao}/{operacao}/{grupo_descricao_to}/{site_descricao_to}"})
	@ResponseBody
	public String method_grupo(@PathVariable(required = false) String grupo_descricao, 
								@PathVariable(required = false) String site_descricao, 
								@PathVariable("operacao") String operacao, 
								@PathVariable(required = false) String grupo_descricao_to, 
								@PathVariable(required = false) String site_descricao_to, 
								HttpServletRequest request) throws Exception
	{
		RetornoVo voRetorno = null;
		voRetorno = new RetornoVo();
		Gson gson;
		String retorno = null;
        //AmploViewDao dao = new AmploViewDao("/mnt/amploviewDATA/amploview.db");
		try {
			if (operacao.toUpperCase().contentEquals("INSERT")) {
				if (grupo_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um INSERT na tabela de Grupos, grupo_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um INSERT na tabela de Grupos, grupo_descricao e ou site_descricao não informado(s)!");
				}
		        dao.insertGrupo(grupo_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Insert de Grupo '" + grupo_descricao + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("SELECT")) {
				if (grupo_descricao==null) {
					grupo_descricao = "";
				}
				if (site_descricao==null) {
					site_descricao = "";
				}
				voRetorno.setCode("200");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setJsonOuTxtComplemento(dao.selectGrupo(grupo_descricao, site_descricao));
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("UPDATE")) {
				if (grupo_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um UPDATE na tabela de Grupos, grupo_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um UPDATE na tabela de Grupos, grupo_descricao e ou site_descricao não informado(s)!");
				}
				if (((site_descricao_to!=null)&&(!site_descricao.contentEquals(site_descricao_to))) ||
                    (((grupo_descricao_to!=null)&&(!grupo_descricao.contentEquals(grupo_descricao_to))) )) {
				    dao.updateGrupo(grupo_descricao, site_descricao, grupo_descricao_to);
					if ((site_descricao_to!=null)&&(!site_descricao.contentEquals(site_descricao_to))) {
						dao.updateSite(site_descricao, site_descricao_to);
					}
					voRetorno.setJsonOuTxtComplemento("Update de Grupo '" + grupo_descricao + "' para '" + grupo_descricao_to + "' efetuado com sucesso!");
					voRetorno.setDescricao("Sucesso");
					voRetorno.setCode("200");
					gson = new Gson();
					retorno = gson.toJson(voRetorno);
					retorno += "\n";
				} else {
					dao.connection.rollback();
					LOGGER.error("Falha ao tentar executar um UPDATE na tabela de Grupos, tentativa de UPDATE para os mesmos valores existentes!");
					throw new Exception("Falha ao tentar executar um UPDATE na tabela de Grupos, tentativa de UPDATE para os mesmos valores existentes!");
				}
			} else if (operacao.toUpperCase().contentEquals("DELETE")) {
				if (grupo_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um DELETE na tabela de Grupos, grupo_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um DELETE na tabela de Grupos, grupo_descricao e ou site_descricao não informado(s)!");
				}
		        dao.deleteGrupo(grupo_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Delete de Grupo efetuado '" + grupo_descricao + "' com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("CLEAN")) {
				if (site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um CLEAN na tabela de Grupos, site_descricao não informado!");
					throw new Exception("Erro ao tentar executar um CLEAN na tabela de Grupos, site_descricao não informado!");
				}
				if (grupo_descricao==null) {
					grupo_descricao="";
				}
				if (site_descricao==null) {
					site_descricao="";
				}
		        dao.cleanGrupo(grupo_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Clean de Grupo '" + grupo_descricao + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("HELP")) {
				String [] methodMapping = new Object(){}.getClass().getEnclosingMethod().getAnnotation(RequestMapping.class).value();
				String pattern = "";
				for(int i=0;i<methodMapping.length;i++) {
					pattern += "curl localhost:8080" +methodMapping[i] + "; ";
				}
				pattern = pattern.substring(0, pattern.length() - 2);
				voRetorno.setJsonOuTxtComplemento("Sintax(es): " + pattern);
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else {
				dao.connection.rollback();
				LOGGER.error("Operacao '" + operacao + "' Inválida " + "Operações válidas são INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP, para maiores detalhes execute curl localhost:8080/grupo/help");
				throw new Exception("Operacao '" + operacao + "' Inválida " + "Operações válidas são INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP, para maiores detalhes execute curl localhost:8080/grupo/help");
			}
			dao.connection.commit();
		}catch (Exception e) {		
			dao.connection.rollback();
			LOGGER.error("Erro ao tentar executar um INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP na tabela de Grupos!", e);
			throw e;
		}
        //dao.Finalizar();
		return retorno;
	}

	@RequestMapping(value={"/elemento/{elemento_descricao}/{grupo_descricao}/{site_descricao}/{operacao}", 
							"/elemento/{operacao}", 
							"/elemento/elemento@{elemento_descricao}/{operacao}", 
							"/elemento/grupo@{grupo_descricao}/{operacao}", 
							"/elemento/site@{site_descricao}/{operacao}", 
							"/elemento/grupo@{grupo_descricao}/site@{site_descricao}/{operacao}", 
							"/elemento/{grupo_descricao}/{site_descricao}/{operacao}", 
							"/elemento/elemento@{elemento_descricao}/grupo@{grupo_descricao}/site@{site_descricao}/{operacao}", 
							"/elemento/{elemento_descricao}/{grupo_descricao}/{site_descricao}/{operacao}/{elemento_descricao_to}/{grupo_descricao_to}/{site_descricao_to}", 
							"/elemento/{elemento_descricao}/{grupo_descricao}/{site_descricao}/{operacao}/{elemento_descricao_to}"})
	@ResponseBody
	public String method_elemento(@PathVariable(required = false) String elemento_descricao, 
									@PathVariable(required = false) String grupo_descricao, 
									@PathVariable(required = false) String site_descricao, 
									@PathVariable("operacao") String operacao, 
									@PathVariable(required = false) String elemento_descricao_to, 
									@PathVariable(required = false) String grupo_descricao_to, 
									@PathVariable(required = false) String site_descricao_to, 
									HttpServletRequest request) throws Exception
	{
		RetornoVo voRetorno = null;
		voRetorno = new RetornoVo();
		Gson gson;
		String retorno = null;
        //AmploViewDao dao = new AmploViewDao("/mnt/amploviewDATA/amploview.db");
		try {
			if (operacao.toUpperCase().contentEquals("INSERT")) {
				if (elemento_descricao==null||grupo_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um INSERT na tabela de atributos, atributo_nome e ou elemento_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um INSERT na tabela de atributos, atributo_nome e ou elemento_descricao e ou site_descricao não informado(s)!");
				}
		        dao.insertElemento(elemento_descricao, grupo_descricao, site_descricao);
		        dao.insertAtributo("Descricao",elemento_descricao, grupo_descricao, site_descricao);
		        dao.insertAtributo("Imagem",elemento_descricao, grupo_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Insert de Elemento '" + elemento_descricao + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("SELECT")) {
				if (elemento_descricao==null) {
					elemento_descricao = "";
				}
				if (grupo_descricao==null) {
					grupo_descricao = "";
				}
				if (site_descricao==null) {
					site_descricao = "";
				}
				voRetorno.setCode("200");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setJsonOuTxtComplemento(dao.selectElemento(elemento_descricao, grupo_descricao, site_descricao));
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("UPDATE")) {
				if (elemento_descricao==null||grupo_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um UPDATE na tabela de elementos, elemento_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um UPDATE na tabela de elementos, elemento_descricao e ou site_descricao não informado(s)!");
				}
				if ((((elemento_descricao_to!=null)&&(!elemento_descricao.contentEquals(elemento_descricao_to))) ) ||
	                    (((grupo_descricao_to!=null)&&(!grupo_descricao.contentEquals(grupo_descricao_to))) ) ||
	                    (((site_descricao_to!=null)&&(!grupo_descricao.contentEquals(site_descricao_to))) )) {
				    dao.updateElemento(elemento_descricao, grupo_descricao, site_descricao, elemento_descricao_to);
				    if ((grupo_descricao!=null)&&(!grupo_descricao.contentEquals(grupo_descricao_to))) {
						dao.updateGrupo(grupo_descricao, site_descricao, grupo_descricao_to);
					}
					if ((site_descricao!=null)&&(!site_descricao.contentEquals(site_descricao_to))) {
						dao.updateSite(site_descricao, site_descricao_to);
					}
					voRetorno.setJsonOuTxtComplemento("Update de Elemento '" + elemento_descricao + "' para '" + elemento_descricao_to + "' efetuado com sucesso!");
					voRetorno.setDescricao("Sucesso");
					voRetorno.setCode("200");
					gson = new Gson();
					retorno = gson.toJson(voRetorno);
					retorno += "\n";
				} else {
					dao.connection.rollback();
					LOGGER.error("Falha ao tentar executar um UPDATE na tabela de elementos, tentativa de UPDATE para os mesmos valores existentes!");
					throw new Exception("Falha ao tentar executar um UPDATE na tabela de elementos, tentativa de UPDATE para os mesmos valores existentes!");
				}
			} else if (operacao.toUpperCase().contentEquals("DELETE")) {
				if (elemento_descricao==null||grupo_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um DELETE na tabela de elementos, elemento_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um DELETE na tabela de elementos, elemento_descricao e ou site_descricao não informado(s)!");
				}
		        dao.deleteElemento(elemento_descricao, grupo_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Delete de Elemento '" + elemento_descricao + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("CLEAN")) {
				if (site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um CLEAN na tabela de elementos, site_descricao não informado!");
					throw new Exception("Erro ao tentar executar um CLEAN na tabela de elementos, site_descricao não informado!");
				}
				if (grupo_descricao==null) {
					grupo_descricao="";
				}
				if (elemento_descricao==null) {
					elemento_descricao="";
				}
		        dao.cleanElemento(elemento_descricao, grupo_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Clean de Elemento '" + elemento_descricao + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("HELP")) {
				String [] methodMapping = new Object(){}.getClass().getEnclosingMethod().getAnnotation(RequestMapping.class).value();
				String pattern = "";
				for(int i=0;i<methodMapping.length;i++) {
					pattern += "curl localhost:8080" +methodMapping[i] + "; ";
				}
				pattern = pattern.substring(0, pattern.length() - 2);
				voRetorno.setJsonOuTxtComplemento("Sintax(es): " + pattern);
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else {
				dao.connection.rollback();
				LOGGER.error("Operacao '" + operacao + "' Inválida " + "Operações válidas são INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP, para maiores detalhes execute curl localhost:8080/elemento/help");
				throw new Exception("Operacao '" + operacao + "' Inválida " + "Operações válidas são INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP, para maiores detalhes execute curl localhost:8080/elemento/help");
			}
			dao.connection.commit();
		}catch (Exception e) {		
			dao.connection.rollback();
			LOGGER.error("Erro ao tentar executar um INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP na tabela de Elementos!", e);
			throw e;
		}
       //dao.Finalizar();
		return retorno;
	}

	@RequestMapping(value={"/atributo/{atributo_nome}/{elemento_descricao}/{grupo_descricao}/{site_descricao}/{operacao}", 
							"/atributo/{operacao}", 
							"/atributo/atributo@{atributo_nome}/{operacao}", 
							"/atributo/elemento@{elemento_descricao}/{operacao}", 
							"/atributo/grupo@{grupo_descricao}/{operacao}", 
							"/atributo/site@{site_descricao}/{operacao}", 
							"/atributo/atributo@{atributo_nome}/elemento@{elemento_descricao}/grupo@{grupo_descricao}/site@{site_descricao}/{operacao}", 
							"/atributo/elemento@{elemento_descricao}/grupo@{grupo_descricao}/site@{site_descricao}/{operacao}", 
							"/atributo/{elemento_descricao}/{grupo_descricao}/{site_descricao}/{operacao}", 
							"/atributo/{atributo_nome}/{elemento_descricao}/{grupo_descricao}/{site_descricao}/{operacao}/{atributo_nome_to}/{elemento_descricao_to}/{grupo_descricao_to}/{site_descricao_to}", 
							"/atributo/{atributo_nome}/{elemento_descricao}/{grupo_descricao}/{site_descricao}/{operacao}/{atributo_nome_to}"})
	@ResponseBody
	public String method_atributo(@PathVariable(required = false) String atributo_nome, 
									@PathVariable(required = false) String elemento_descricao, 
									@PathVariable(required = false) String grupo_descricao, 
									@PathVariable(required = false) String site_descricao, 
									@PathVariable("operacao") String operacao, 
									@PathVariable(required = false) String atributo_nome_to, 
									@PathVariable(required = false) String elemento_descricao_to,  
									@PathVariable(required = false) String grupo_descricao_to, 
									@PathVariable(required = false) String site_descricao_to, 
									HttpServletRequest request) throws Exception
	{
		RetornoVo voRetorno = null;
		voRetorno = new RetornoVo();
		Gson gson;
		String retorno = null;
        //AmploViewDao dao = new AmploViewDao("/mnt/amploviewDATA/amploview.db");
		if (atributo_nome!=null) {
			if (atributo_nome.toUpperCase().contentEquals("IMAGEM")||atributo_nome.toUpperCase().contentEquals("DESCRICAO")) {
				LOGGER.error("Operacao '" + operacao + "' Inválida " + " para os atrbutos IMAGEM e DESCRICAO.");
				throw new Exception("Operacao '" + operacao + "' Inválida " + " para os atrbutos IMAGEM e DESCRICAO.");
			}
		}
		try {
			if (operacao.toUpperCase().contentEquals("INSERT")) {
				if (atributo_nome==null||elemento_descricao==null||grupo_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um INSERT na tabela de atributos, atributo_nome e ou elemento_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um INSERT na tabela de atributos, atributo_nome e ou elemento_descricao e ou site_descricao não informado(s)!");
				}
		        dao.insertAtributo(atributo_nome, elemento_descricao, grupo_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Insert de Atributo '" + atributo_nome + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("SELECT")) {
				if (atributo_nome==null) {
					atributo_nome = "";
				}
				if (elemento_descricao==null) {
					elemento_descricao = "";
				}
				if (grupo_descricao==null) {
					grupo_descricao = "";
				}
				if (site_descricao==null) {
					site_descricao = "";
				}
				voRetorno.setCode("200");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setJsonOuTxtComplemento(dao.selectAtributo(atributo_nome, elemento_descricao, grupo_descricao, site_descricao));
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("UPDATE")) {
				if (atributo_nome.toUpperCase().contentEquals("IMAGEM")||atributo_nome.toUpperCase().contentEquals("DESCRICAO")) {
					dao.connection.rollback();
					LOGGER.error("Operacao '" + operacao + "' Inválida " + " para os atrbutos IMAGEM e DESCRICAO.");
					throw new Exception("Operacao '" + operacao + "' Inválida " + " para os atrbutos IMAGEM e DESCRICAO.");
				}
				if (atributo_nome==null||elemento_descricao==null||grupo_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um UPDATE na tabela de atributos, atributo_nome e ou elemento_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um UPDATE na tabela de atributos, atributo_nome e ou elemento_descricao e ou site_descricao não informado(s)!");
				}
				if ((((atributo_nome_to!=null)&&(!atributo_nome.contentEquals(atributo_nome_to))) ) ||
	                    (((elemento_descricao_to!=null)&&(!elemento_descricao.contentEquals(elemento_descricao_to))) ) ||
	                    (((grupo_descricao_to!=null)&&(!grupo_descricao.contentEquals(grupo_descricao_to))) ) ||
	                    (((site_descricao_to!=null)&&(!grupo_descricao.contentEquals(site_descricao_to))) )) {
				    dao.updateAtributo(atributo_nome, elemento_descricao, grupo_descricao, site_descricao, atributo_nome_to);
					if ((elemento_descricao!=null)&&(!elemento_descricao.contentEquals(elemento_descricao_to))) {
						dao.updateElemento(elemento_descricao, grupo_descricao, site_descricao, elemento_descricao_to);
					}
					if ((grupo_descricao!=null)&&(!grupo_descricao.contentEquals(grupo_descricao_to))) {
						dao.updateGrupo(grupo_descricao, site_descricao, grupo_descricao_to);
					}
					if ((site_descricao!=null)&&(!site_descricao.contentEquals(site_descricao_to))) {
						dao.updateSite(site_descricao, site_descricao_to);
					}
					voRetorno.setJsonOuTxtComplemento("Update de Atributo '" + atributo_nome + "' para '" + atributo_nome_to + "' efetuado com sucesso!");
					voRetorno.setDescricao("Sucesso");
					voRetorno.setCode("200");
					gson = new Gson();
					retorno = gson.toJson(voRetorno);
					retorno += "\n";
				} else {
					dao.connection.rollback();
					LOGGER.error("Falha ao tentar executar um UPDATE na tabela de atributos, tentativa de UPDATE para os mesmos valores existentes!");
					throw new Exception("Falha ao tentar executar um UPDATE na tabela de atributos, tentativa de UPDATE para os mesmos valores existentes!");
				}
			} else if (operacao.toUpperCase().contentEquals("DELETE")) {
				if (atributo_nome==null||elemento_descricao==null||grupo_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um DELETE na tabela de atributos, atributo_nome e ou elemento_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um DELETE na tabela de atributos, atributo_nome e ou elemento_descricao e ou site_descricao não informado(s)!");
				}
		        dao.deleteAtributo(atributo_nome, elemento_descricao, grupo_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Delete de Atributo '" + atributo_nome + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("CLEAN")) {
				if (site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um CLEAN na tabela de atributos, site_descricao não informado!");
					throw new Exception("Erro ao tentar executar um CLEAN na tabela de atributos, site_descricao não informado!");
				}
				if (grupo_descricao==null) {
					grupo_descricao="";
				}
				if (elemento_descricao==null) {
					elemento_descricao="";
				}
				if (atributo_nome==null) {
					atributo_nome="";
				}
		        dao.cleanAtributo(atributo_nome, elemento_descricao, grupo_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Clean de Atributo '" + atributo_nome + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("HELP")) {
				String [] methodMapping = new Object(){}.getClass().getEnclosingMethod().getAnnotation(RequestMapping.class).value();
				String pattern = "";
				for(int i=0;i<methodMapping.length;i++) {
					pattern += "curl localhost:8080" +methodMapping[i] + "; ";
				}
				pattern = pattern.substring(0, pattern.length() - 2);
				voRetorno.setJsonOuTxtComplemento("Sintax(es): " + pattern);
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else {
				dao.connection.rollback();
				LOGGER.error("Operacao '" + operacao + "' Inválida " + "Operações válidas são INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP, para maiores detalhes execute curl localhost:8080/atributo/help");
				throw new Exception("Operacao '" + operacao + "' Inválida " + "Operações válidas são INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP, para maiores detalhes execute curl localhost:8080/atributo/help");
			}
			dao.connection.commit();
		}catch (Exception e) {		
			dao.connection.rollback();
			LOGGER.error("Erro ao tentar executar um INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP na tabela de Atributos!", e);
			throw e;
		}
        //dao.Finalizar();
		return retorno;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value={"/valor/{valor_valor}/{atributo_nome}/{elemento_descricao}/{grupo_descricao}/{site_descricao}/{operacao}", 
							"/valor/{operacao}",
							"/valor/valor@{valor_valor}/{operacao}", 
							"/valor/atributo@{atributo_nome}/{operacao}", 
							"/valor/elemento@{elemento_descricao}/{operacao}", 
							"/valor/grupo@{grupo_descricao}/{operacao}", 
							"/valor/site@{site_descricao}/{operacao}", 
							"/valor/valor@{valor_valor}/atributo@{atributo_nome}/elemento@{elemento_descricao}/grupo@{grupo_descricao}/site@{site_descricao}/{operacao}", 
							"/valor/atributo@{atributo_nome}/elemento@{elemento_descricao}/grupo@{grupo_descricao}/site@{site_descricao}/{operacao}", 
							"/valor/{atributo_nome}/{elemento_descricao}/{grupo_descricao}/{site_descricao}/{operacao}", 
							"/valor/{valor_valor}/{atributo_nome}/{elemento_descricao}/{grupo_descricao}/{site_descricao}/{operacao}/{valor_valor_to}/{atributo_nome_to}/{elemento_descricao_to}/{grupo_descricao_to}/{site_descricao_to}", 
							"/valor/{valor_valor}/{atributo_nome}/{elemento_descricao}/{grupo_descricao}/{site_descricao}/{operacao}/{valor_valor_to}"})
	@ResponseBody
	public String method_valor(@PathVariable(required = false) String valor_valor, 
								@PathVariable(required = false) String atributo_nome, 
								@PathVariable(required = false) String elemento_descricao, 
								@PathVariable(required = false) String grupo_descricao, 
								@PathVariable(required = false) String site_descricao, 
								@PathVariable("operacao") String operacao, 
								@PathVariable(required = false) String valor_valor_to, 
								@PathVariable(required = false) String atributo_nome_to, 
								@PathVariable(required = false) String elemento_descricao_to, 
								@PathVariable(required = false) String grupo_descricao_to, 
								@PathVariable(required = false) String site_descricao_to, 
								HttpServletRequest request) throws Exception
	{
		RetornoVo voRetorno = null;
		voRetorno = new RetornoVo();
		Gson gson;
		String retorno = null;
        //AmploViewDao dao = new AmploViewDao("/mnt/amploviewDATA/amploview.db");
		if (valor_valor!=null) {
			if (atributo_nome.contentEquals("Imagem")) {
				//Caminho imagem incluido com paramentro GET para passar character '/'
				String path_imagem = request.getParameter("path_imagem");
				if (path_imagem != null) {
					if (!path_imagem.contentEquals("")) {
						valor_valor = path_imagem + "/" + valor_valor;
					}
				}
			}
		}
		try {
			if (operacao.toUpperCase().contentEquals("INSERT")) {
				if (valor_valor==null||atributo_nome==null||elemento_descricao==null||grupo_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um UPDATE na tabela de valores, valor_valor e ou atributo_nome e ou elemento_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um UPDATE na tabela de valores, valor_valor e ou atributo_nome e ou elemento_descricao e ou site_descricao não informado(s)!");
				}
		        dao.insertValor(valor_valor, atributo_nome, elemento_descricao, grupo_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Insert de Valor '" + valor_valor + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("SELECT")) {
				if (valor_valor==null) {
					valor_valor = "";
				}
				if (atributo_nome==null) {
					atributo_nome = "";
				}
				if (elemento_descricao==null) {
					elemento_descricao = "";
				}
				if (grupo_descricao==null) {
					grupo_descricao = "";
				}
				if (site_descricao==null) {
					site_descricao = "";
				}
				voRetorno.setCode("200");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setJsonOuTxtComplemento(dao.selectValor(valor_valor, atributo_nome, elemento_descricao, grupo_descricao, site_descricao));
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("UPDATE")) {
				if (valor_valor==null||atributo_nome==null||elemento_descricao==null||grupo_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um UPDATE na tabela de valores, valor_valor e ou atributo_nome e ou elemento_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um UPDATE na tabela de valores, valor_valor e ou atributo_nome e ou elemento_descricao e ou site_descricao não informado(s)!");
				}
				if (((valor_valor_to!=null)&&(!valor_valor.contentEquals(valor_valor_to))) ||
	                    (((atributo_nome_to!=null)&&(!atributo_nome.contentEquals(atributo_nome_to))) ) ||
	                    (((elemento_descricao_to!=null)&&(!elemento_descricao.contentEquals(elemento_descricao_to))) ) ||
	                    (((grupo_descricao_to!=null)&&(!grupo_descricao.contentEquals(grupo_descricao_to))) ) ||
	                    (((site_descricao_to!=null)&&(!grupo_descricao.contentEquals(site_descricao_to))) )) {
				    dao.updateValor(valor_valor, atributo_nome, elemento_descricao, grupo_descricao, site_descricao, valor_valor_to);
					if ((atributo_nome!=null)&&(!atributo_nome.contentEquals(atributo_nome_to))) {
						dao.updateAtributo(atributo_nome, elemento_descricao, grupo_descricao, site_descricao, atributo_nome_to);
					}
					if ((elemento_descricao!=null)&&(!elemento_descricao.contentEquals(elemento_descricao_to))) {
						dao.updateElemento(elemento_descricao, grupo_descricao, site_descricao, elemento_descricao_to);
					}
					if ((grupo_descricao!=null)&&(!grupo_descricao.contentEquals(grupo_descricao_to))) {
						dao.updateGrupo(grupo_descricao, site_descricao, grupo_descricao_to);
					}
					if ((site_descricao!=null)&&(!site_descricao.contentEquals(site_descricao_to))) {
						dao.updateSite(site_descricao, site_descricao_to);
					}
					voRetorno.setJsonOuTxtComplemento("Update de Valor '" + valor_valor + "' para '" + valor_valor_to + "' efetuado com sucesso!");
					voRetorno.setDescricao("Sucesso");
					voRetorno.setCode("200");
					gson = new Gson();
					retorno = gson.toJson(voRetorno);
					retorno += "\n";
				} else {
					dao.connection.rollback();
					LOGGER.error("Falha ao tentar executar um UPDATE na tabela de valores, tentativa de UPDATE para os mesmos valores existentes!");
					throw new Exception("Falha ao tentar executar um UPDATE na tabela de valores, tentativa de UPDATE para os mesmos valores existentes!");
				}
			} else if (operacao.toUpperCase().contentEquals("DELETE")) {
				if (valor_valor==null||atributo_nome==null||elemento_descricao==null||grupo_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um DELETE na tabela de valores, valor_valor e ou atributo_nome e ou elemento_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um DELETE na tabela de valores, valor_valor e ou atributo_nome e ou elemento_descricao e ou site_descricao não informado(s)!");
				}
		        dao.deleteValor(valor_valor, atributo_nome, elemento_descricao, grupo_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Delete de Valor '" + valor_valor + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("CLEAN")) {
				if (site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um CLEAN na tabela de Valores, site_descricao não informado!");
					throw new Exception("Erro ao tentar executar um CLEAN na tabela de Valores, site_descricao não informado!");
				}
				if (grupo_descricao==null) {
					grupo_descricao="";
				}
				if (elemento_descricao==null) {
					elemento_descricao="";
				}
				if (atributo_nome==null) {
					atributo_nome="";
				}
				if (valor_valor==null) {
					valor_valor="";
				}
		        dao.cleanValor(valor_valor, atributo_nome, elemento_descricao, grupo_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Clean de Valor '" + valor_valor + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("HELP")) {
				String [] methodMapping = new Object(){}.getClass().getEnclosingMethod().getAnnotation(RequestMapping.class).value();
				String pattern = "";
				for(int i=0;i<methodMapping.length;i++) {
					pattern += "curl localhost:8080" +methodMapping[i] + "; ";
				}
				pattern = pattern.substring(0, pattern.length() - 2);
				voRetorno.setJsonOuTxtComplemento("Sintax(es): " + pattern);
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else {
				dao.connection.rollback();
				LOGGER.error("Operacao '" + operacao + "' Inválida " + "Operações válidas são INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP, para maiores detalhes execute curl localhost:8080/valor/help");
				throw new Exception("Operacao '" + operacao + "' Inválida " + "Operações válidas são INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP, para maiores detalhes execute curl localhost:8080/valor/help");
			}
			dao.connection.commit();
		}catch (Exception e) {		
			dao.connection.rollback();
			LOGGER.error("Erro ao tentar executar um INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP na tabela de Valores!", e);
			throw e;
		}
        //dao.Finalizar();
		return retorno;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value={"/colecao/{colecao_descricao}/{site_descricao}/{operacao}", 
							"/colecao/{operacao}", 
							"/colecao/colecao@{colecao_descricao}/{operacao}", 
							"/colecao/site@{site_descricao}/{operacao}", 
							"/colecao/{colecao_descricao}/{site_descricao}/{operacao}/{colecao_descricao_to}/{site_descricao_to}"})
	//-------------------------------------------------------------------------
	//Observação: coleções são obrigatóriamente pertencentes a um site somente
	//-------------------------------------------------------------------------
	@ResponseBody
	public String method_colecao(@PathVariable(required = false) String colecao_descricao, 
			@PathVariable(required = false) String site_descricao, 
			@PathVariable("operacao") String operacao, 
			@PathVariable(required = false) String colecao_descricao_to, 
			@PathVariable(required = false) String site_descricao_to, 
			HttpServletRequest request) throws Exception
	{
		RetornoVo voRetorno = null;
		voRetorno = new RetornoVo();
		Gson gson;
		String retorno = null;
		//AmploViewDao dao = new AmploViewDao("/mnt/amploviewDATA/amploview.db");
		try {
			if (operacao.toUpperCase().contentEquals("INSERT")) {
				if (colecao_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um INSERT na tabela de Colecoes, colecao_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um INSERT na tabela de Colecoes, colecao_descricao e ou site_descricao não informado(s)!");
				}
				dao.insertColecao(colecao_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Insert de colecao '" + colecao_descricao+"/"+site_descricao + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("SELECT")) {
				if (colecao_descricao==null) {
					colecao_descricao = "";
				}
				if (site_descricao==null) {
					site_descricao = "";
				}
				voRetorno.setJsonOuTxtComplemento(dao.selectColecao(colecao_descricao, site_descricao));
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("UPDATE")) {
				if (colecao_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um UPDATE na tabela de Colecoes, colecao_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um UPDATE na tabela de Colecoes, colecao_descricao e ou site_descricao não informado(s)!");
				}
				if (((site_descricao_to!=null)&&(!site_descricao.contentEquals(site_descricao_to))) ||
                    (((colecao_descricao_to!=null)&&(!colecao_descricao.contentEquals(colecao_descricao_to))) )) {
					dao.updateColecao(colecao_descricao, site_descricao, colecao_descricao_to, site_descricao_to);
					voRetorno.setJsonOuTxtComplemento("Update de colecao '" + colecao_descricao+"/"+site_descricao + "' para '" + colecao_descricao_to+"/"+site_descricao_to + "' efetuado com sucesso!");
					voRetorno.setDescricao("Sucesso");
					voRetorno.setCode("200");
					gson = new Gson();
					retorno = gson.toJson(voRetorno);
					retorno += "\n";
				} else {
					dao.connection.rollback();
					LOGGER.error("Falha ao tentar executar um UPDATE na tabela de Colecoes, tentativa de UPDATE para os mesmos valores existentes!");
					throw new Exception("Falha ao tentar executar um UPDATE na tabela de Colecoes, tentativa de UPDATE para os mesmos valores existentes!");
				}
			} else if (operacao.toUpperCase().contentEquals("DELETE")) {
				if (colecao_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um DELETE na tabela de Colecoes, colecao_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um DELETE na tabela de Colecoes, colecao_descricao e ou site_descricao não informado(s)!");
				}
				dao.deleteColecao(colecao_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Delete de colecao '" + colecao_descricao+"/"+"/"+site_descricao + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("CLEAN")) {
				if (site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um CLEAN na tabela de Colecoes, site_descricao não informado!");
					throw new Exception("Erro ao tentar executar um CLEAN na tabela de Colecoes, site_descricao não informado!");
				}
				if (colecao_descricao==null) {
					colecao_descricao="";
				}
				if (site_descricao==null) {
					site_descricao="";
				}
				dao.cleanColecao(colecao_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Clean de colecao '" + colecao_descricao+"/"+"/"+site_descricao + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("HELP")) {
				String [] methodMapping = new Object(){}.getClass().getEnclosingMethod().getAnnotation(RequestMapping.class).value();
				String pattern = "";
				for(int i=0;i<methodMapping.length;i++) {
					pattern += "curl localhost:8080" +methodMapping[i] + "; ";
				}
				pattern = pattern.substring(0, pattern.length() - 2);
				voRetorno.setJsonOuTxtComplemento("Sintax(es): " + pattern);
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else {
				dao.connection.rollback();
				LOGGER.error("Operacao '" + operacao + "' Inválida " + "Operações válidas são INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP, para maiores detalhes execute curl localhost:8080/colecao/help");
				throw new Exception("Operacao '" + operacao + "' Inválida " + "Operações válidas são INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP, para maiores detalhes execute curl localhost:8080/colecao/help");
			}
			dao.connection.commit();
		}catch (Exception e) {		
			dao.connection.rollback();
			LOGGER.error("Erro ao tentar executar um INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP na tabela de colecaos!", e);
			throw e;
		}
		//dao.Finalizar();
		return retorno;
	}
	
	@RequestMapping(value={"/associacao/grupo/{colecao_descricao}/{grupo_descricao}/{site_descricao}/{operacao}", 
							"/associacao/grupo/{operacao}", 
							"/associacao/grupo/colecao@{colecao_descricao}/{operacao}", 
							"/associacao/grupo/grupo@{grupo_descricao}/{operacao}", 
							"/associacao/grupo/site@{site_descricao}/{operacao}", 
							"/associacao/grupo/colecao@{colecao_descricao}/grupo@{grupo_descricao}/site@{site_descricao}/{operacao}/",
							"/associacao/grupo/{colecao_descricao}/{grupo_descricao}/{site_descricao}/{operacao}/{colecao_descricao_to}/{grupo_descricao_to}/{site_descricao_to}"})

	//-------------------------------------------------------------------------------------------------
	//Observação: associações de coleção/grupo são feitas somente para itens pertencentes ao mesmo site
	//-------------------------------------------------------------------------------------------------
	@ResponseBody
	public String method_associacao_colecao_grupo(@PathVariable(required = false) String colecao_descricao, 
			@PathVariable(required = false) String grupo_descricao, 
			@PathVariable(required = false) String site_descricao, 
			@PathVariable("operacao") String operacao, 
			@PathVariable(required = false) String colecao_descricao_to, 
			@PathVariable(required = false) String grupo_descricao_to, 
			@PathVariable(required = false) String site_descricao_to, 
			HttpServletRequest request) throws Exception
	{
		RetornoVo voRetorno = null;
		voRetorno = new RetornoVo();
		Gson gson;
		String retorno = null;
		//AmploViewDao dao = new AmploViewDao("/mnt/amploviewDATA/amploview.db");
		try {
			if (operacao.toUpperCase().contentEquals("INSERT")) {
				if (colecao_descricao==null||grupo_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um INSERT na tabela de AssociacoesColecaoGrupo, colecao_descricao e ou grupo_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um INSERT na tabela de AssociacoesColecaoGrupo, colecao_descricao e ou grupo_descricao e ou site_descricao não informado(s)!");
				}
				dao.insertAssociacoesColecaoGrupo(colecao_descricao, grupo_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Insert de AssociacoesColecaoGrupo '" + colecao_descricao+"/"+grupo_descricao+"/"+site_descricao + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("SELECT")) {
				if (colecao_descricao==null) {
					colecao_descricao="";
				}
				if (grupo_descricao==null) {
					grupo_descricao="";
				}
				if (site_descricao==null) {
					site_descricao="";
				}
				voRetorno.setCode("200");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setJsonOuTxtComplemento(dao.selectAssociacoesColecaoGrupo(colecao_descricao, grupo_descricao, site_descricao));
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("UPDATE")) {
				if (colecao_descricao==null||grupo_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um UPDATE na tabela de AssociacoesColecaoGrupo, colecao_descricao e ou grupo_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um UPDATE na tabela de AssociacoesColecaoGrupo, colecao_descricao e ou grupo_descricao e ou site_descricao não informado(s)!");
				}
				if (((colecao_descricao_to!=null)&&(!colecao_descricao.contentEquals(colecao_descricao_to))) ||
                    (((grupo_descricao_to!=null)&&(!grupo_descricao.contentEquals(grupo_descricao_to))) ) ||
                    (((site_descricao_to!=null)&&(!site_descricao.contentEquals(site_descricao_to))) )) {
					dao.updateAssociacoesColecaoGrupo(colecao_descricao, grupo_descricao, site_descricao, grupo_descricao_to, colecao_descricao_to, site_descricao_to);
					voRetorno.setJsonOuTxtComplemento("Update de AssociacoesColecaoGrupo '" + colecao_descricao+"/"+grupo_descricao+"/"+site_descricao + "' para '" + colecao_descricao_to+"/"+grupo_descricao_to+"/"+site_descricao_to + "' efetuado com sucesso!");
					voRetorno.setDescricao("Sucesso");
					voRetorno.setCode("200");
					gson = new Gson();
					retorno = gson.toJson(voRetorno);
					retorno += "\n";
				} else {
					dao.connection.rollback();
					LOGGER.error("Falha ao tentar executar um UPDATE na tabela de AssociacoesColecaoGrupo, tentativa de UPDATE para os mesmos valores existentes!");
					throw new Exception("Falha ao tentar executar um UPDATE na tabela de AssociacoesColecaoGrupo, tentativa de UPDATE para os mesmos valores existentes!");
				}
			} else if (operacao.toUpperCase().contentEquals("DELETE")) {
				if (colecao_descricao==null||grupo_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um DELETE na tabela de AssociacoesColecaoGrupo, colecao_descricao e ou grupo_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um DELETE na tabela de AssociacoesColecaoGrupo, colecao_descricao e ou grupo_descricao e ou site_descricao não informado(s)!");
				}
				dao.deleteAssociacoesColecaoGrupo(colecao_descricao, grupo_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Delete de AssociacoesColecaoGrupo '" + colecao_descricao+"/"+grupo_descricao+"/"+site_descricao + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("CLEAN")) {
				if (site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um CLEAN na tabela de AssociacoesColecaoGrupo, site_descricao não informado!");
					throw new Exception("Erro ao tentar executar um CLEAN na tabela de AssociacoesColecaoGrupo, site_descricao não informado!");
				}
				if (colecao_descricao==null) {
					colecao_descricao="";
				}
				if (grupo_descricao==null) {
					grupo_descricao="";
				}
				dao.cleanAssociacoesColecaoGrupo(colecao_descricao, grupo_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Clean de AssociacoesColecaoGrupo '" + colecao_descricao+"/"+grupo_descricao+"/"+site_descricao + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("HELP")) {
				String [] methodMapping = new Object(){}.getClass().getEnclosingMethod().getAnnotation(RequestMapping.class).value();
				String pattern = "";
				for(int i=0;i<methodMapping.length;i++) {
					pattern += "curl localhost:8080" +methodMapping[i] + "; ";
				}
				pattern = pattern.substring(0, pattern.length() - 2);
				voRetorno.setJsonOuTxtComplemento("Sintax(es): " + pattern);
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else {
				dao.connection.rollback();
				LOGGER.error("Operacao '" + operacao + "' Inválida " + "Operações válidas são INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP, para maiores detalhes execute curl localhost:8080/associacao/grupo/help");
				throw new Exception("Operacao '" + operacao + "' Inválida " + "Operações válidas são INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP, para maiores detalhes execute curl localhost:8080/associacao/grupo/help");
			}
			dao.connection.commit();
		}catch (Exception e) {		
			dao.connection.rollback();
			LOGGER.error("Erro ao tentar executar um INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP na tabela de AssociacoesColecaoGrupo!", e);
			throw e;
		}
		//dao.Finalizar();
		return retorno;
	}

	@RequestMapping(value={"/associacao/colecao/{colecao_pai_descricao}/{colecao_filho_descricao}/{site_descricao}/{operacao}", 
							"/associacao/colecao/{operacao}", 
							"/associacao/colecao/pai@{colecao_pai_descricao}/{operacao}", 
							"/associacao/colecao/filho@{colecao_filho_descricao}/{operacao}", 
							"/associacao/colecao/site@{site_descricao}/{operacao}", 
							"/associacao/colecao/pai@{colecao_pai_descricao}/filho@{colecao_filho_descricao}/site@{site_descricao}/{operacao}/",
							"/associacao/colecao/{colecao_pai_descricao}/{colecao_filho_descricao}/{site_descricao}/{operacao}/{colecao_pai_descricao_to}/{colecao_filho_descricao_to}/{site_descricao_to}"})
	//---------------------------------------------------------------------------------------------------
	//Observação: associações de coleção/coleção são feitas somente para itens pertencentes ao mesmo site
	//---------------------------------------------------------------------------------------------------
	@ResponseBody
	public String method_associacao_colecao_colecao(@PathVariable(required = false) String colecao_pai_descricao, 
			@PathVariable(required = false) String colecao_filho_descricao, 
			@PathVariable(required = false) String site_descricao, 
			@PathVariable("operacao") String operacao, 
			@PathVariable(required = false) String colecao_pai_descricao_to, 
			@PathVariable(required = false) String colecao_filho_descricao_to, 
			@PathVariable(required = false) String site_descricao_to, 
			HttpServletRequest request) throws Exception
	{
		RetornoVo voRetorno = null;
		voRetorno = new RetornoVo();
		Gson gson;
		String retorno = null;
		//AmploViewDao dao = new AmploViewDao("/mnt/amploviewDATA/amploview.db");
		try {
			if (operacao.toUpperCase().contentEquals("INSERT")) {
				if (colecao_pai_descricao==null||colecao_filho_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um INSERT na tabela de AssociacoesColecaoColecao, colecao_pai_descricao e ou colecao_filho_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um INSERT na tabela de AssociacoesColecaoColecao, colecao_pai_descricao e ou colecao_filho_descricao e ou site_descricao não informado(s)!");
				}
				dao.insertAssociacoesColecaoColecao(colecao_pai_descricao, colecao_filho_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Insert de AssociacoesColecaoColecao '" + colecao_pai_descricao+"'/'"+colecao_filho_descricao+"/"+site_descricao + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("SELECT")) {
				if (colecao_pai_descricao==null) {
					colecao_pai_descricao="";
				}
				if (colecao_filho_descricao==null) {
					colecao_filho_descricao="";
				}
				if (site_descricao==null) {
					site_descricao="";
				}
				voRetorno.setCode("200");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setJsonOuTxtComplemento(dao.selectAssociacoesColecaoColecao(colecao_pai_descricao, colecao_filho_descricao, site_descricao));
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("UPDATE")) {
				if (colecao_pai_descricao==null||colecao_filho_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um UPDATE na tabela de AssociacoesColecaoColecao, colecao_pai_descricao e ou colecao_filho_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um UPDATE na tabela de AssociacoesColecaoColecao, colecao_pai_descricao e ou colecao_filho_descricao e ou site_descricao não informado(s)!");
				}
				if (((colecao_pai_descricao_to!=null)&&(!colecao_pai_descricao.contentEquals(colecao_pai_descricao_to))) ||
                    (((colecao_filho_descricao_to!=null)&&(!colecao_filho_descricao.contentEquals(colecao_filho_descricao_to))) ) ||
                    (((site_descricao_to!=null)&&(!site_descricao.contentEquals(site_descricao_to))) )) {
					dao.updateAssociacoesColecaoColecao(colecao_pai_descricao, colecao_filho_descricao, site_descricao, colecao_filho_descricao_to, colecao_pai_descricao_to, site_descricao_to);
					voRetorno.setJsonOuTxtComplemento("Update de AssociacoesColecaoColecao '" + colecao_pai_descricao+"'/'"+colecao_filho_descricao+"/"+site_descricao + "' para '" + colecao_pai_descricao_to+"'/'"+colecao_filho_descricao_to+"/"+site_descricao_to + "' efetuado com sucesso!");
					voRetorno.setDescricao("Sucesso");
					voRetorno.setCode("200");
					gson = new Gson();
					retorno = gson.toJson(voRetorno);
					retorno += "\n";
				} else {
					dao.connection.rollback();
					LOGGER.error("Falha ao tentar executar um UPDATE na tabela de AssociacoesColecaoColecao, tentativa de UPDATE para os mesmos valores existentes!");
					throw new Exception("Falha ao tentar executar um UPDATE na tabela de AssociacoesColecaoColecao, tentativa de UPDATE para os mesmos valores existentes!");
				}
			} else if (operacao.toUpperCase().contentEquals("DELETE")) {
				if (colecao_pai_descricao==null||colecao_filho_descricao==null||site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um DELETE na tabela de AssociacoesColecaoColecao, colecao_pai_descricao e ou colecao_filho_descricao e ou site_descricao não informado(s)!");
					throw new Exception("Erro ao tentar executar um DELETE na tabela de AssociacoesColecaoColecao, colecao_pai_descricao e ou colecao_filho_descricao e ou site_descricao não informado(s)!");
				}
				dao.deleteAssociacoesColecaoColecao(colecao_pai_descricao, colecao_filho_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Delete de AssociacoesColecaoColecao '" + colecao_pai_descricao + "'/'" + colecao_filho_descricao + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("CLEAN")) {
				if (site_descricao==null) {
					dao.connection.rollback();
					LOGGER.error("Erro ao tentar executar um CLEAN na tabela de AssociacoesColecaoColecao, site_descricao não informado!");
					throw new Exception("Erro ao tentar executar um CLEAN na tabela de AssociacoesColecaoColecao, site_descricao não informado!");
				}
				if (colecao_pai_descricao==null) {
					colecao_pai_descricao="";
				}
				if (colecao_filho_descricao==null) {
					colecao_filho_descricao="";
				}
				dao.cleanAssociacoesColecaoColecao(colecao_pai_descricao, colecao_filho_descricao, site_descricao);
				voRetorno.setJsonOuTxtComplemento("Clean de AssociacoesColecaoColecao '" + colecao_pai_descricao + "'/'" + colecao_filho_descricao + "' efetuado com sucesso!");
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else if (operacao.toUpperCase().contentEquals("HELP")) {
				String [] methodMapping = new Object(){}.getClass().getEnclosingMethod().getAnnotation(RequestMapping.class).value();
				String pattern = "";
				for(int i=0;i<methodMapping.length;i++) {
					pattern += "curl localhost:8080" +methodMapping[i] + "; ";
				}
				pattern = pattern.substring(0, pattern.length() - 2);
				voRetorno.setJsonOuTxtComplemento("Sintax(es): " + pattern);
				voRetorno.setDescricao("Sucesso");
				voRetorno.setCode("200");
				gson = new Gson();
				retorno = gson.toJson(voRetorno);
				retorno += "\n";
			} else {
				dao.connection.rollback();
				LOGGER.error("Operacao '" + operacao + "' Inválida " + "Operações válidas são INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP, para maiores detalhes execute curl localhost:8080/associacao/colecao/help");
				throw new Exception("Operacao '" + operacao + "' Inválida " + "Operações válidas são INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP, para maiores detalhes execute curl localhost:8080/associacao/colecao/help");
			}
			dao.connection.commit();
		}catch (Exception e) {		
			dao.connection.rollback();
			LOGGER.error("Erro ao tentar executar um INSERT/SELECT/UPDATE/DELETE/CLEAN/HELP na tabela de AssociacoesColecaoColecao!", e);
			throw e;
		}
		//dao.Finalizar();
		return retorno;
	}

}
