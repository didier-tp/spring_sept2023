package tp.appliSpring.exemple;

import org.springframework.stereotype.Component;

@Component()//id par defaut = monCalculateurCarre = NomClasse avec minuscule au debut
public class MonCalculateurCarre implements MonCalculateur {

	@Override
	public double calculer(double x) {
		return x*x;
	}

}
