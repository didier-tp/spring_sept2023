package tp.appliSpring.core.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tp.appliSpring.core.entity.Compte;

@Repository // @Component de type DAO/Repository
@Qualifier("jpa")
public class DaoCompteJpa implements DaoCompte {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Compte findById(Long numCpt) {
		return entityManager.find(Compte.class, numCpt);// SELECT par primary key
	}

	/*
	 * public Compte save(Compte compte) { try {
	 * entityManager.getTransaction().begin(); if(compte.getNumero()==null)
	 * entityManager.persist(compte);//INSERT INTO else
	 * entityManager.merge(compte);//UPDATE entityManager.getTransaction().commit();
	 * } catch (Exception e) { entityManager.getTransaction().rollback();
	 * e.printStackTrace(); } return compte; //avec numero plus null
	 * (auto_incrémenté) }
	 */

	@Override
	@Transactional
	public Compte save(Compte compte) {
		if (compte.getNumero() == null)
			entityManager.persist(compte);// INSERT INTO
		else
			entityManager.merge(compte);// UPDATE
		return compte; // avec numero plus null (auto_incrémenté)
	}

	@Override
	public List<Compte> findAll() {
		return entityManager.createQuery("SELECT c FROM Compte c", Compte.class).getResultList();
	}

	@Override
	@Transactional
	public void deleteById(Long numCpt) {
		Compte cpt = entityManager.find(Compte.class, numCpt);
		entityManager.remove(cpt);
	}

	@Override
	public List<Compte> findWithSoldeMini(Double soldeMini) {
		return entityManager.createQuery("SELECT c FROM Compte c WHERE c.solde > ?1", Compte.class)
				.setParameter(1, soldeMini)
				.getResultList();
	}

}
