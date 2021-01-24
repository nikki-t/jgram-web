package jgram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import api.document.DocumentStorageProperties;

@SpringBootApplication
@ComponentScan(basePackages = "api")
@EnableConfigurationProperties({
	DocumentStorageProperties.class
})
public class JgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(JgramApplication.class, args);
	}
	
}
