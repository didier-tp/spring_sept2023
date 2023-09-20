package tp.appliSpring.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliSpring.core.entity.Compte;

/*
 NB: avec l'extension spring-data , une classe d'implémentation de cette interface
     est générée automatiquement et c'est injectable avec @Autowired
 */

public interface DaoCompte extends JpaRepository<Compte,Long>{
    /*
     par héritage , on a :
	.save()
	.findById()
	.deleteById()
	....
	*/
	
	//code de la requete dans @NamedQuery("Compte.findWithOperations")
	Compte findWithOperations(long numCompte);
}
