package tp.appliSpring.exemplev2;



import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


public class Coordinateur {

		private MonAfficheur monAfficheur=null; //référence vers afficheur à injecter
		
		private MonCalculateur monCalculateur=null;//référence vers calculateur à injecter
		
		
		
		public void setMonAfficheur(MonAfficheur monAfficheur) {
			this.monAfficheur = monAfficheur;
		}



		public void setMonCalculateur(MonCalculateur monCalculateur) {
			this.monCalculateur = monCalculateur;
		}

		
		


		public Coordinateur() {
			super();
		}



		public Coordinateur(MonAfficheur monAfficheur, MonCalculateur monCalculateur) {
			super();
			this.monAfficheur = monAfficheur;
			this.monCalculateur = monCalculateur;
		}



		public void calculerEtAfficher() {
			double x=4;
			double res =monCalculateur.calculer(x); //x*x ou bien 2*x ou bien ...
			monAfficheur.afficher("res="+res);// >> res=16 en v1 ou bien ** res=16
		}


}
