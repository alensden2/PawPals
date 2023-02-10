package com.asdc.pawpals;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PawpalsApplication {

	// private static final Logger logger = LogManager.getLogger(PawpalsApplication.class);
	public static void main(String[] args) {
		// logger.info("PawPalsApplication :: Main :: starting the spring boot application");
		SpringApplication.run(PawpalsApplication.class, args);
	}

}
