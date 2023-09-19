package tp.appliSpring.core.service;

import tp.appliSpring.core.entity.Compte;

//par defaut , les méthodes peuvent renvoyer RuntimeException
public interface ServiceCompte {
	public void transferer(double montant,long numCptDeb,long numCptCred);
	public Compte rechercherCompte(long numCpt);
	public Compte sauvegarderCompte(Compte cpt);
	//...
}
