package tp.appliSpring.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliSpring.core.entity.Compte;

public interface DaoCompte extends JpaRepository<Compte,Long>{
    /*
     par héritage , on a :
	.save()
	.findById()
	.deleteById()
	....
	*/
	
}
