package tp.appliSpring.exemplev2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "tp.aspect" , "tp.appliSpring.exemplev2" })
@PropertySource("classpath:exemples.properties") 
public class ExempleConfigExplicite {
	
	@Value("${exemple.calculateur}")
	private String calculateurClassName;
	
	@Bean
	public MonCalculateur monCalculateur(){ 
		//return new MonCalculateurCarre();
		//return new MonCalculateurDouble();
		if(calculateurClassName != null 
				&& calculateurClassName.equals("tp.appliSpring.exemplev2.MonCalculateurCarre"))
			return new MonCalculateurCarre();
		else
			return new MonCalculateurDouble();
	}

	@Bean
	@Profile("V1")
	public MonAfficheur monAfficheurV1() {
		return new MonAfficheurV1();
	}
	
	
	@Bean
	//@Profile("V2")
	//@Profile("!V1")
	@Profile({"!V1","V2"})
	public MonAfficheur monAfficheurV2() {
		return new MonAfficheurV2();
	}
	
	
	
	
	@Bean
	public Coordinateur coordinateur(MonAfficheur afficheur,MonCalculateur calculateur) {
		Coordinateur coordinateur =new Coordinateur();
		//ou bien Coordinateur coordinateur =new Coordinateur(afficheur, calculateur); //injection par constructeur
		coordinateur.setMonAfficheur(afficheur); //injection de dépendance explicite
		coordinateur.setMonCalculateur(calculateur);//injection de dépendance explicite via set...()
		return coordinateur;
	}
}
