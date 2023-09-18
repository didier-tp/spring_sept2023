package tp.appliSpring.exemplev2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "tp.appliSpring.exemplev2" })
public class ExempleConfigExplicite {
/* @ComponentScan() pour demander à spring de parcourir les classes de certains
packages pour y trouver des annotations @Component , @Service , @Autowired à
analyser et interpréter */
	
	//@Bean //id/nom par defaut = nom de la methode = "calculateurCarre"
	@Bean("monCalculateurCarre")
	public MonCalculateur calculateurCarre() {
		return new MonCalculateurCarre();
	}
	
	
}