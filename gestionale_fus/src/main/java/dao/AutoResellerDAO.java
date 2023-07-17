package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.AutoReseller;

public class AutoResellerDAO {

	private final EntityManager entityManager;

	public AutoResellerDAO(EntityManager _entityManager) {
		this.entityManager = _entityManager;
	}

	// - - - - - - - - - - - - - - - - - - - - save
	public void save(AutoReseller _autoReseller) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(_autoReseller);
		entityTransaction.commit();
		System.out.println("Distributore automatico salvato correttamente");
	}

	// - - - - - - - - - - - - - - - - - - - - findById
	public AutoReseller findById(long _id) {
		AutoReseller desiredAutoReseller = entityManager.find(AutoReseller.class, _id);
		return desiredAutoReseller;
	}

	// - - - - - - - - - - - - - - - - - - - - findByIdAndDelete
	public void findByIdAndDelete(long _id) {
		AutoReseller desiredAutoReseller = entityManager.find(AutoReseller.class, _id);
		if (desiredAutoReseller != null) {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(desiredAutoReseller);
			entityTransaction.commit();

		} else {
			System.out.println("Distributore automatico non trovato");
		}
	}
}
