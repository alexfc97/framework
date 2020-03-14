package dtu.bachelor.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FrameworkApplication {

	private static final Logger log = LoggerFactory.getLogger(FrameworkApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FrameworkApplication.class, args);
	}
}
