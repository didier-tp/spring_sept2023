package tp.appliSpring.exemple;



import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Coordinateur {

		@Autowired 
		//@Qualifier("V1")
		@Qualifier("monAfficheurV1")
		private MonAfficheur monAfficheur=null; //référence vers afficheur à injecter
		
		@Autowired
		@Qualifier("monCalculateurDouble")
		private MonCalculateur monCalculateur=null;//référence vers calculateur à injecter
		
		public Coordinateur() {
			System.out.println("dans constructeur de Coordinateur,monAfficheur= " + this.monAfficheur);
			//monAfficheur.afficher("blabla BOOM BOOM , null pointer exception ");
		}
		
		@PostConstruct
		public void initialiser() {
			System.out.println("dans initialiser() prefixee par @PostConstruct ,monAfficheur= " + this.monAfficheur);
			monAfficheur.afficher("monAfficheur enfin préparé et utilisable ");
		}
		
		public void calculerEtAfficher() {
			double x=4;
			double res =monCalculateur.calculer(x); //x*x ou bien 2*x ou bien ...
			monAfficheur.afficher("res="+res);// >> res=16 en v1 ou bien ** res=16
		}


}
