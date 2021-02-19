package com.amploview.program.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amploview.program.entities.Sites;
import com.amploview.program.repositories.SitesRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {

	@Autowired
	private SitesRepository siteRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Sites s1 = new Sites(null, "RioKasa");
		Sites s2 = new Sites(null, "JorgeAlmirGoncalves");
		Sites s3 = new Sites(null, "QuintoAndar");

		siteRepository.saveAll(Arrays.asList(s1, s2, s3));
	}

}
