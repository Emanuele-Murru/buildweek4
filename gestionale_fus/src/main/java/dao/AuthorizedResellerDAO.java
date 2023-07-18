package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.AuthorizedReseller;

public class AuthorizedResellerDAO {

	private final EntityManager entityManager;

	public AuthorizedResellerDAO(EntityManager _entityManager) {
		this.entityManager = _entityManager;
	}

	// - - - - - - - - - - - - - - - - - - - - save
	public void save(AuthorizedReseller _authorizedReseller) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(_authorizedReseller);
		entityTransaction.commit();
		System.out.println("Rivenditore autorizzato salvato correttamente");
	}

	// - - - - - - - - - - - - - - - - - - - - findById
	public AuthorizedReseller findById(long _id) {
		AuthorizedReseller desiredAuthorizedReseller = entityManager.find(AuthorizedReseller.class, _id);
		return desiredAuthorizedReseller;
	}

	// - - - - - - - - - - - - - - - - - - - - findByIdAndDelete
	public void findByIdAndDelete(long _id) {
		AuthorizedReseller desiredAuthorizedReseller = entityManager.find(AuthorizedReseller.class, _id);
		if (desiredAuthorizedReseller != null) {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(desiredAuthorizedReseller);
			entityTransaction.commit();

		} else {
			System.out.println("Rivenditore autorizzato non trovato");
		}
	}
}
