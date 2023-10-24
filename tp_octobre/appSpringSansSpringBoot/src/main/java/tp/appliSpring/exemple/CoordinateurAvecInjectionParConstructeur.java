package tp.appliSpring.exemple;



import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CoordinateurAvecInjectionParConstructeur {

	
		private MonAfficheur monAfficheur=null; //référence vers afficheur à injecter
		
		
		private MonCalculateur monCalculateur=null;//référence vers calculateur à injecter
		
		//injection de dépendance par constructeur
		//@Autowired
		public CoordinateurAvecInjectionParConstructeur(
				@Qualifier("monAfficheurV1") MonAfficheur monAfficheur,
				@Qualifier("monCalculateurCarre") MonCalculateur monCalculateur) {
			this.monAfficheur = monAfficheur;
			this.monCalculateur = monCalculateur;
		}
		
		
		
		public void calculerEtAfficher() {
			double x=4;
			double res =monCalculateur.calculer(x); //x*x ou bien 2*x ou bien ...
			monAfficheur.afficher("res="+res);// >> res=16 en v1 ou bien ** res=16
		}


}
