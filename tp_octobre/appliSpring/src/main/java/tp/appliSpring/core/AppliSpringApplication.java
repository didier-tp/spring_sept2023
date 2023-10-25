package tp.appliSpring.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppliSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppliSpringApplication.class, args);
		System.out.println("http://localhost:8080/appliSpring");
		// index.html est normalement placée src/main/resources/static
	}

}
