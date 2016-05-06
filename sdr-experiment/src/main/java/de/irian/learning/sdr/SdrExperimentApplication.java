package de.irian.learning.sdr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;

import de.irian.learning.sdr.security.data.SecurityEvaluationContextExtension;

@SpringBootApplication
public class SdrExperimentApplication {

	@Bean
	public EvaluationContextExtension securityExtension() {
		return new SecurityEvaluationContextExtension();
	}

	public static void main(String[] args) {
		SpringApplication.run(SdrExperimentApplication.class, args);
	}
}
