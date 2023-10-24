package tp.appliSpring.exemplev2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExempleAppV2 {

	public static void main(String[] args) {
		
		//System.setProperty("spring.profiles.active", "V1");
		System.setProperty("spring.profiles.active", "V2,perf");
		//System.setProperty("spring.profiles.active", "V2");

		
		ApplicationContext contextSpring = new
				 AnnotationConfigApplicationContext(ExempleConfigExplicite.class);
		
		System.out.println("en mode config explicite ...");
		
		//contextSpring représente un ensemble de composants pris en charge par spring
		//et qui est initialisé selon une ou plusieurs classes de configuration.
		
		MonCalculateur monCalculateur = contextSpring.getBean(MonCalculateur.class);
		
		
		System.out.println("res calcul="+monCalculateur.calculer(4));//4*4=16.0 ou autre
		
		Coordinateur coordinateurPrisEnChargeParSpring =
				 contextSpring.getBean(Coordinateur.class);
				coordinateurPrisEnChargeParSpring.calculerEtAfficher();
		
		((AnnotationConfigApplicationContext) contextSpring).close();


	}

}
