package tp.appliSpring.exemple;

import org.springframework.stereotype.Component;

@Component
//@Component("monCalculateurCarre") //l'id par defaut = NomClasse avec minuscule sur première lettre
//@Component("idQueJaimepourMonCalculateurCarre") //id spécifique
public class MonCalculateurCarre implements MonCalculateur {

	@Override
	public double calculer(double x) {
		return x*x;
	}

}
