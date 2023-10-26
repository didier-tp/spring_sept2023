package tp.appliSpring.core.service;

import java.util.List;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.exception.NotFoundException;

public interface ServiceCompte {
	Compte rechercherCompteParNumero(long numero) throws NotFoundException;
	Compte rechercherCompteParNumeroAvecOperations(long numero);

	List<Compte> rechercherTousComptes();
	List<Compte> rechercherComptesAvecSoldeMini(Double soldeMini);

	List<Compte> rechercherComptesDuClient(long numClient);

	Compte sauvegarderCompte(Compte compte);

	void supprimerCompte(long numCpt);

	void transferer(double montant, long numCptDeb, long numCptCred);
}
