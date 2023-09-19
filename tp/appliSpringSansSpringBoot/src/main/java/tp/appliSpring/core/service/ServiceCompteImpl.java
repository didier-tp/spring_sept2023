package tp.appliSpring.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tp.appliSpring.core.dao.DaoCompte;
import tp.appliSpring.core.entity.Compte;

@Service //@Component de type Service
//@Transactional
public class ServiceCompteImpl implements ServiceCompte {
	
	@Autowired
	@Qualifier("jpa")
	private DaoCompte daoCompte;

	//@Transactional
	public void transferer(double montant, long numCptDeb, long numCptCred) {
		Compte cptDeb = daoCompte.findById(numCptDeb);
		cptDeb.setSolde(cptDeb.getSolde() - montant);
		daoCompte.save(cptDeb);
		
		Compte cptCred = daoCompte.findById(numCptCred);
		cptCred.setSolde(cptCred.getSolde() + montant);
		daoCompte.save(cptCred);
	}

	@Override
	public Compte rechercherCompte(long numCpt) {
		return daoCompte.findById(numCpt);
	}

	@Override
	public Compte sauvegarderCompte(Compte cpt) {
		return daoCompte.save(cpt);
	}

}
