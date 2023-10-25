package tp.appliSpring.core.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import tp.appliSpring.core.entity.Compte;

public interface RepositoryCompte extends CrudRepository<Compte,Long>{
	/*
	 principales méthodes héritées:
	 .findById()
	 .save()
	 .deleteById()
	 */
	
    List<Compte> findBySoldeGreaterThanEqual(Double soldeMini); //selon convention de nom de méthode
    
    Optional<Compte> findByIdWithOperations(Long numero);//codé via @NamedQuery("Compte.findByIdWithOperations")
    
}
