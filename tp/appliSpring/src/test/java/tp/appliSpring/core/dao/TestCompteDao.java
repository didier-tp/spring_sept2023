package tp.appliSpring.core.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import tp.appliSpring.core.entity.Compte;


@SpringBootTest
@ActiveProfiles({  "dev" })
public class TestCompteDao {
	
    private static Logger logger = LoggerFactory.getLogger(TestCompteDao.class);
	
	@Autowired
	private DaoCompte daoCompte; //à tester
	

	
	@Test
	public void testAjoutEtRelectureEtSuppression() {
		//hypothese : base avec tables vides au lancement du test
		Compte compte = new Compte(null,"compteA",100.0);
		Compte compteSauvegarde = this.daoCompte.save(compte); //INSERT INTO
		logger.debug("compteSauvegarde=" + compteSauvegarde);
		
		this.daoCompte.save(new Compte(null,"compteAa",10.0));
		this.daoCompte.save(new Compte(null,"compteAaa",1.0));
		this.daoCompte.save(new Compte(null,"compteAaaa",60.0));
		
		List<Compte> comptes = this.daoCompte.findBySoldeGreaterThanEqual(50.0);
		logger.debug("comptes avec soldeMini=50 " + comptes);
		
		Compte compteRelu = this.daoCompte.findById(compteSauvegarde.getNumero()).get(); //SELECT
		Assertions.assertEquals("compteA",compteRelu.getLabel());
		Assertions.assertEquals(100.0,compteRelu.getSolde());
		logger.debug("compteRelu apres insertion=" + compteRelu);
		
		compte.setSolde(150.0); compte.setLabel("compte_a");
		Compte compteMisAjour = this.daoCompte.save(compte); //UPDATE
		logger.debug("compteMisAjour=" + compteMisAjour);
		
		compteRelu = this.daoCompte.findById(compteSauvegarde.getNumero()).get(); //SELECT
		Assertions.assertEquals("compte_a",compteRelu.getLabel());
		Assertions.assertEquals(150.0,compteRelu.getSolde());
		logger.debug("compteRelu apres miseAjour=" + compteRelu);
		/*
		//+supprimer :
		this.daoCompte.deleteById(compteSauvegarde.getNumero());
		
		//verifier bien supprimé (en tentant une relecture qui renvoi null)
		Compte compteReluApresSuppression = this.daoCompte.findById(compteSauvegarde.getNumero()).orElse(null); 
		Assertions.assertTrue(compteReluApresSuppression == null);
		*/
	}

}
