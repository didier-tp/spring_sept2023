package tp.appliSpring.core.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tp.appliSpring.core.dao.DaoCompte;
import tp.appliSpring.core.dao.RepositoryCompte;
import tp.appliSpring.core.entity.Compte;

@Service // classe de Service prise en charge par spring
//@Transactional possible ici pour ne pas l'oublier
public class ServiceCompteImpl implements ServiceCompte {
	//@Qualifier("jpa")
	@Autowired
	//private DaoCompte daoCompte = null;
	private RepositoryCompte daoCompte = null;

	public Compte rechercherCompteParNumero(long numero) {
		//return daoCompte.findById(numero).orElse(null);
		return daoCompte.findById(numero).get();//retourne exception si pas trouvé
	}

	public Compte sauvegarderCompte(Compte compte) {
		return daoCompte.save(compte);
	}

	public List<Compte> rechercherTousComptes() {
		return (List<Compte>) daoCompte.findAll();
	}

	public List<Compte> rechercherComptesDuClient(long numClient) {
		//return null; //version zero
		return this.rechercherTousComptes(); // V1 (provisoire)
		//future version V2 (via un nouvel appel sur DAO exploitant @ManyToOne ou bien ...)
	}

	public void supprimerCompte(long numCpt) {
		daoCompte.deleteById(numCpt);
	}

	@Transactional(/* propagation = Propagation.REQUIRED */) // REQUIRED par defaut
	public void transferer(double montant, long numCptDeb, long numCptCred) {
		try {
			// transaction globale initialisée dès le début de l'exécution de transferer
			Compte cptDeb = this.daoCompte.findById(numCptDeb).get();
			// le dao exécute son code dans la grande transaction
			//commencée par le service sans la fermer et l'objet cptDeb remonte à l'état persistant
			cptDeb.setSolde(cptDeb.getSolde() - montant);
			//this.daoCompte.save(cptDeb); //facultatif si @Transactional
			//idem pour compte à créditer
			Compte cptCred = this.daoCompte.findById(numCptCred).get();
			cptCred.setSolde(cptCred.getSolde() + montant);
			//this.daoCompte.save(cptCred); //facultatif si @Transactional
			//en fin de transaction réussie (sans exception) , toutes les modification effectuées
			//sur les objets à l'état persistant seront répercutées en base (.save() automatiques)
		} catch (Exception e) {
			throw new RuntimeException("echec virement " + e.getMessage(), e);
			//rollback se fait de façon fiable
			//ou bien throw new
			//ClasseExceptionPersonnaliseeHeritantDeRuntimeException("echec virement" , e);
		}
	}
}
