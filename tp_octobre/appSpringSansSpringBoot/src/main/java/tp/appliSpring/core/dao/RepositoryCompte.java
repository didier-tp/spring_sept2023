package tp.appliSpring.core.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tp.appliSpring.core.entity.Compte;

public interface RepositoryCompte extends CrudRepository<Compte,Long>{
    List<Compte> findBySoldeGreaterThanEqual(Double soldeMini); //selon convention de nom de méthode
}
