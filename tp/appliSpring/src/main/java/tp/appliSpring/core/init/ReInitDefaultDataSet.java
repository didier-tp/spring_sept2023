package tp.appliSpring.core.init;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import tp.appliSpring.core.dao.DaoOperation;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.entity.Operation;
import tp.appliSpring.core.service.ServiceCompte;

@Component
@Profile("reInit")
public class ReInitDefaultDataSet {
	
	@Autowired
	ServiceCompte serviceCompte;
	
	@Autowired
	DaoOperation daoOperation;
	
	@PostConstruct
	public void intialiserJeuxDeDonnees() {
		System.out.println("initialisation d'un jeux de données (en mode developpement)");
		Compte cptA = new Compte(null,"compteA",100.0);
		Compte cptA_sauvegarde = serviceCompte.sauvegarderCompte(cptA);
		
        Operation op1 = new Operation(null,"achat 1" , -5.0 , new Date());
        op1.setCompte(cptA_sauvegarde); daoOperation.save(op1);
		Operation op2 = new Operation(null,"achat 2" , -6.0 , new Date());
		op2.setCompte(cptA_sauvegarde);daoOperation.save(op2);
		
		
		Compte cptB = new Compte(null,"compteB",200.0);
		Compte cptB_sauvegarde = serviceCompte.sauvegarderCompte(cptB);
	}

}