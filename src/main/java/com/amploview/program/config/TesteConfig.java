package com.amploview.program.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amploview.program.entities.Grupos;
import com.amploview.program.entities.Sites;
import com.amploview.program.repositories.GruposRepository;
import com.amploview.program.repositories.SitesRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {

	@Autowired
	private SitesRepository siteRepository;

	@Autowired
	private GruposRepository grupoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Sites s1 = new Sites(null, "RioKasa");
		Sites s2 = new Sites(null, "JorgeAlmirGoncalves");
		Sites s3 = new Sites(null, "QuintoAndar");

		siteRepository.saveAll(Arrays.asList(s1, s2, s3));
		
		Grupos g1s1 = new Grupos(null,"Centro",s1);
		Grupos g2s1 = new Grupos(null,"Zona Norte",s1);
		Grupos g3s1 = new Grupos(null,"Zona Sul",s1);
		
		Grupos g1s2 = new Grupos(null,"Niteroi",s2);
		Grupos g2s2 = new Grupos(null,"Barra",s2);
		Grupos g3s2 = new Grupos(null,"Baixada",s2);
		
		Grupos g1s3 = new Grupos(null,"Nilopolis",s3);
		Grupos g2s3 = new Grupos(null,"Petropolis",s3);
		Grupos g3s3 = new Grupos(null,"Zona Oeste",s3);
		
		grupoRepository.saveAll(Arrays.asList(g1s1, g2s1, g3s1));
		grupoRepository.saveAll(Arrays.asList(g1s2, g2s2, g3s2));
		grupoRepository.saveAll(Arrays.asList(g1s3, g2s3, g3s3));

	}

}
