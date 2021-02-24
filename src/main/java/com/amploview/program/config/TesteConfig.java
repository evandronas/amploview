package com.amploview.program.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amploview.program.entities.Associacoes;
import com.amploview.program.entities.Atributos;
import com.amploview.program.entities.Colecoes;
import com.amploview.program.entities.Elementos;
import com.amploview.program.entities.Grupos;
import com.amploview.program.entities.Sites;
import com.amploview.program.entities.Valores;
import com.amploview.program.entities.enums.TipoAssociacao;
import com.amploview.program.repositories.AssociacoesRepository;
import com.amploview.program.repositories.AtributosRepository;
import com.amploview.program.repositories.ColecoesRepository;
import com.amploview.program.repositories.ElementosRepository;
import com.amploview.program.repositories.GruposRepository;
import com.amploview.program.repositories.SitesRepository;
import com.amploview.program.repositories.ValoresRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {

	@Autowired
	private SitesRepository siteRepository;

	@Autowired
	private GruposRepository grupoRepository;

	@Autowired
	private ElementosRepository elementoRepository;

	@Autowired
	private AtributosRepository atributoRepository;

	@Autowired
	private ValoresRepository valorRepository;

	@Autowired
	private ColecoesRepository colecaoRepository;

	@Autowired
	private AssociacoesRepository associacaoRepository;
		
	@Override
	public void run(String... args) throws Exception {
		
		Sites imobiliaria = new Sites(null, "Imobiliaria");
		Sites agencia = new Sites(null, "Agencia Viagem");

		siteRepository.saveAll(Arrays.asList(imobiliaria, agencia));
		
		Grupos estacio = new Grupos(null,"Estacio",imobiliaria);
		Grupos riocomprido = new Grupos(null,"Rio Comprido",imobiliaria);
		Grupos buzios = new Grupos(null,"Buzios",agencia);
				
		grupoRepository.saveAll(Arrays.asList(estacio, riocomprido,buzios));
		
		Elementos haddock = new Elementos(null,"Rua Haddock Lobo, 61",estacio);
		Elementos bispo = new Elementos(null,"Rua do Bispo, 160",riocomprido);
		Elementos forno = new Elementos(null,"Praia do Forno",buzios);

		elementoRepository.saveAll(Arrays.asList(haddock,bispo,forno));
		
		Atributos deschaddock = new Atributos(null,"Descricao",haddock);
		Atributos descbispo = new Atributos(null,"Descricao",bispo);
		Atributos descforno = new Atributos(null,"Descricao",forno);
		Atributos imagemhaddock = new Atributos(null,"Imagem",haddock);
		Atributos imagembispo = new Atributos(null,"Imagem",bispo);
		Atributos imagemforno = new Atributos(null,"Imagem",forno);
		
		atributoRepository.saveAll(Arrays.asList(deschaddock,descbispo,descforno,imagemhaddock,imagembispo,imagemforno));
		
		Valores valordeschaddok1 = new Valores(null,"Primeira foto",deschaddock);
		Valores valordeschaddok2 = new Valores(null,"Segunda foto",deschaddock);
		Valores valordeschaddok3 = new Valores(null,"Terceira foto",deschaddock);
		Valores valordescbispo1 = new Valores(null,"Primeira foto",descbispo);
		Valores valordescbispo2 = new Valores(null,"Segunda foto",descbispo);
		Valores valordescbispo3 = new Valores(null,"Terceira foto",descbispo);
		Valores valordescforno1 = new Valores(null,"Primeira foto",descforno);
		Valores valordescforno2 = new Valores(null,"Segunda foto",descforno);
		Valores valordescforno3 = new Valores(null,"Terceira foto",descforno);
		Valores valordescforno4 = new Valores(null,"Quarta foto",descforno);
		Valores valorimagemhaddock1 = new Valores(null,"R0015669_20191207064703.JPG",imagemhaddock);
		Valores valorimagemhaddock2 = new Valores(null,"R0015662_20191207062225.JPG",imagemhaddock);
		Valores valorimagemhaddock3 = new Valores(null,"R0015663_20191207062948.JPG",imagemhaddock);
		Valores valorimagembispo1 = new Valores(null,"R0015654_20191130062822.JPG",imagembispo);
		Valores valorimagembispo2 = new Valores(null,"R0015655_20191130063251.JPG",imagembispo);
		Valores valorimagembispo3 = new Valores(null,"R0015651_20191130060905.JPG",imagembispo);
		Valores valorimagemforno1 = new Valores(null,"IMG-20190628-WA0013.jpg",imagemforno);
		Valores valorimagemforno2 = new Valores(null,"IMG-20190628-WA0021.jpg",imagemforno);
		Valores valorimagemforno3 = new Valores(null,"IMG-20190628-WA0017.jpg",imagemforno);
		Valores valorimagemforno4 = new Valores(null,"IMG-20190628-WA0026.jpg",imagemforno);

		valorRepository.saveAll(Arrays.asList(valordeschaddok1,valordeschaddok2,valordeschaddok3,valordescbispo1,valordescbispo2,valordescbispo3,valordescforno1,valordescforno2,valordescforno3,valordescforno4,valorimagemhaddock1,valorimagemhaddock2,valorimagemhaddock3,valorimagembispo1,valorimagembispo2,valorimagembispo3,valorimagemforno1,valorimagemforno2,valorimagemforno3,valorimagemforno4));
		
		Colecoes RJ = new Colecoes(null,"Estado do Rio de Janeiro");
		Colecoes riodejaneiro = new Colecoes(null, "Cidade do Rio de Janeiro");
		Colecoes regiaodoslagos = new Colecoes(null, "Regiao dos Lagos");
		Colecoes zonanorte = new Colecoes(null, "Zona Norte");
		
		colecaoRepository.saveAll(Arrays.asList(RJ,riodejaneiro,regiaodoslagos,zonanorte));
		
		Associacoes associacaoRJriodejaneiro = new Associacoes(null,RJ.getId(),riodejaneiro.getId(),TipoAssociacao.valueOf("Colecoes").getTipo());
		Associacoes associacaoRJregiaodoslagos = new Associacoes(null,RJ.getId(),regiaodoslagos.getId(),TipoAssociacao.valueOf("Colecoes").getTipo());
		Associacoes associacaoriodejaneirozonanorte = new Associacoes(null,riodejaneiro.getId(),zonanorte.getId(),TipoAssociacao.valueOf("Colecoes").getTipo());
		Associacoes associacaozonanorteestacio = new Associacoes(null,zonanorte.getId(),estacio.getId(),TipoAssociacao.valueOf("Grupos").getTipo());
		Associacoes associacaozonanorteriocomprido = new Associacoes(null,zonanorte.getId(),riocomprido.getId(),TipoAssociacao.valueOf("Grupos").getTipo());
		Associacoes associacaoregiaodoslagosbuzios = new Associacoes(null,regiaodoslagos.getId(),buzios.getId(),TipoAssociacao.valueOf("Grupos").getTipo());
		
		associacaoRepository.saveAll(Arrays.asList(associacaoRJriodejaneiro,associacaoRJregiaodoslagos,associacaoriodejaneirozonanorte,associacaozonanorteestacio,associacaozonanorteriocomprido,associacaoregiaodoslagosbuzios));		
	}

}
