package tp.appliSpring.core.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tp.appliSpring.core.MySpringApplication;
import tp.appliSpring.core.dao.DaoCompte;
import tp.appliSpring.core.entity.Compte;
import tp.appliSpring.core.entity.Operation;


@ExtendWith(SpringExtension.class) //si junit5/jupiter
@ContextConfiguration(classes= {MySpringApplication.class}) //classe de config principale (au démarrage du test)
public class TestCompteRepository {
	
	private static Logger logger = LoggerFactory.getLogger(TestCompteRepository.class);
	
	@Autowired
	private RepositoryCompte daoCompte; //à tester
	
	@Autowired
	private RepositoryOperation daoOperations; //à aider à tester les comptes
	
	@Test
	public void testCompteAvecOperations() {
		Compte compteX = this.daoCompte.save( new Compte(null,"compteX",50.0));
		Operation op1=new Operation(null,"achat ..." , -5.0);
		op1.setCompte(compteX);
		daoOperations.save(op1);
		Operation op2=new Operation(null,"autre achat ..." , -15.0);
		op2.setCompte(compteX);
		daoOperations.save(op2);
		
		this.daoCompte.save( new Compte(null,"compteY",150.0));
		
		//Compte compteXRelu = this.daoCompte.findById(compteX.getNumero()).get(); //avec LazyException dans boucle for plus bas
		Compte compteXRelu = this.daoCompte.findByIdWithOperations(compteX.getNumero()).get(); //ok
		System.out.println("compteXRelu=" + compteXRelu.toString());
		for(Operation op : compteXRelu.getOperations()) {
			System.out.println("\t op=" + op.toString());
		}
	}
	
	@Test
	public void testCompteAvecSoldeMini() {
		this.daoCompte.save( new Compte(null,"compteB1",50.0));
		this.daoCompte.save( new Compte(null,"compteB2",-50.0));
		this.daoCompte.save( new Compte(null,"compteB3",250.0));
		this.daoCompte.save( new Compte(null,"compteB4",-60.0));
		
		List<Compte> comptes = daoCompte.findBySoldeGreaterThanEqual(0.0);
		logger.debug("comptes=" + comptes);
		Assertions.assertTrue(comptes.size()>=2);
	}
	
	 @Test
	 public void testAjoutEtRelectureEtSuppression() {
		//hypothese : base avec tables vides et existantes au lancement du test
		 Compte compte = new Compte(null,"compteA",100.0);
		 Compte compteSauvegarde = this.daoCompte.save(compte); //INSERT INTO
		 logger.debug("compteSauvegarde=" + compteSauvegarde);
		 
		 Compte compteRelu = this.daoCompte.findById(compteSauvegarde.getNumero()).orElse(null); 
		 Assertions.assertEquals("compteA",compteRelu.getLabel());
		 Assertions.assertEquals(100.0,compteRelu.getSolde());
		 logger.debug("compteRelu apres insertion=" + compteRelu);
		 
		 compte.setSolde(150.0); compte.setLabel("compte_a");
		 Compte compteMisAjour = this.daoCompte.save(compte); //UPDATE
		 logger.debug("compteMisAjour=" + compteMisAjour);
		 
		 compteRelu = this.daoCompte.findById(compteSauvegarde.getNumero()).orElse(null); //SELECT
		 Assertions.assertEquals("compte_a",compteRelu.getLabel());
		 Assertions.assertEquals(150.0,compteRelu.getSolde());
		 logger.debug("compteRelu apres miseAjour=" + compteRelu);
		 
		 
		 //+supprimer :
		 this.daoCompte.deleteById(compteSauvegarde.getNumero());
		 //verifier bien supprimé (en tentant une relecture qui renvoi null)
		 Compte compteReluApresSuppression =
		  this.daoCompte.findById(compteSauvegarde.getNumero()).orElse(null); 
		 Assertions.assertTrue(compteReluApresSuppression == null);
		
		 }

}
