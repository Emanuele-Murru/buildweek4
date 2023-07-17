package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

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
		System.out.println("Distributore automatico salvato con successo");
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

	// - - - - - - - - - - - - - - - - - - - - changeStatus
	public void changeStatus(long _id, String _status) {
		AutoReseller desiredAutoReseller = entityManager.find(AutoReseller.class, _id);
		if (desiredAutoReseller != null) {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			Query query = entityManager.createQuery("").setParameter("_status", _status);

			entityTransaction.commit();
			System.out.println("Lo stato del distributore automatico è stato aggiornato con successo");

		} else {
			System.out.println("Distributore automatico non trovato");
		}
	}
}
