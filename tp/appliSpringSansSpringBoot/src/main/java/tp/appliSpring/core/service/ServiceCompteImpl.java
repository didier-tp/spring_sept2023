package tp.appliSpring.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.appliSpring.core.dao.DaoCompte;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.exception.BankException;
import tp.appliSpring.core.exception.NotFoundException;

@Service //@Component de type Service
//@Transactional
public class ServiceCompteImpl implements ServiceCompte {
	
	@Autowired
	@Qualifier("jpa")
	private DaoCompte daoCompte;

	@Transactional()
	//@Transactional(propagation = Propagation.REQUIRED)par défaut
	public void transferer(double montant, long numCptDeb, long numCptCred)throws BankException {
		try {
			Compte cptDeb = daoCompte.findById(numCptDeb);
			cptDeb.setSolde(cptDeb.getSolde() - montant);
			daoCompte.save(cptDeb);
			
			Compte cptCred = daoCompte.findById(numCptCred);
			cptCred.setSolde(cptCred.getSolde() + montant);
			daoCompte.save(cptCred);
		} catch (Exception e) {
			throw new BankException("echec virement",e);
		}
	}

	@Override
	public Compte rechercherCompte(long numCpt)throws NotFoundException {
		try {
			return daoCompte.findById(numCpt);
		} catch (Exception e) {
			throw new NotFoundException("account not found with numCpt="+numCpt,e);
		}
	}

	@Override
	public Compte sauvegarderCompte(Compte cpt) {
		return daoCompte.save(cpt);
	}

}
