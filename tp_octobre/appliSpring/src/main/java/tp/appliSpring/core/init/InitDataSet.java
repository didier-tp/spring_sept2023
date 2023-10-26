package tp.appliSpring.core.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import tp.appliSpring.core.dao.RepositoryOperation;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.entity.Operation;
import tp.appliSpring.core.service.ServiceCompte;

//avec System.setProperty("spring.profiles.active", "initDataSet"); dans le main()

@Component
@Profile("initDataSet")
public class InitDataSet {
	
	@Autowired
	private ServiceCompte serviceCompte;
	
	@Autowired
	private RepositoryOperation daoOperations;
	
	@PostConstruct
	public void initDefaultDataSet() {
	    Compte compteA = serviceCompte.sauvegarderCompte(new Compte(null,"compteA",100.0));
	    
	    Operation op1=new Operation(null,"achat ..." , -5.0);
		op1.setCompte(compteA);
		daoOperations.save(op1);
		Operation op2=new Operation(null,"autre achat ..." , -15.0);
		op2.setCompte(compteA);
		daoOperations.save(op2);
	   
	   serviceCompte.sauvegarderCompte(new Compte(null,"compteB",150.0));
	   
	}


}
