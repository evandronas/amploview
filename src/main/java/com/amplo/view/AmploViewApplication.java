package com.amplo.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Evandro
 */
@SpringBootApplication
public class AmploViewApplication {

	public static void main(String[] args) {
        System.setProperty("org.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH", "true");
		SpringApplication.run(AmploViewApplication.class, args);
	}
}
