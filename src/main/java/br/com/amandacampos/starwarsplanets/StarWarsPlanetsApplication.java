package br.com.amandacampos.starwarsplanets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
public class StarWarsPlanetsApplication {
	public static void main(String[] args) {
		SpringApplication.run(StarWarsPlanetsApplication.class, args);
	}
}
