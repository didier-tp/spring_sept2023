package tp.appliSpring.exemple;

import org.springframework.stereotype.Component;

@Component
//@Component("monCalculateurDouble") //l'id par defaut = NomClasse avec minuscule sur première lettre
//@Qualifier("monCalculateurDouble") //qualifier par defaut
public class MonCalculateurDouble implements MonCalculateur {

	@Override
	public double calculer(double x) {
		return 2*x;
	}

}
