package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.AutoReseller;

public class AutoResellerDAO {

	private final EntityManager entityManager;

	public AutoResellerDAO(EntityManager _entityManager) {
		this.entityManager = _entityManager;
	}

	public void save(AutoReseller _autoReseller) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(_autoReseller);
		entityTransaction.commit();
		System.out.println("Distributore automatico salvato correttamente");
	}

	public AutoReseller findById(long _id) {
		AutoReseller desiredAutoResellerCercato = entityManager.find(AutoReseller.class, _id);
		return desiredAutoResellerCercato;
	}

	public void findByIdAndDelete(long _id) {
		AutoReseller desiredAutoResellerCercato = entityManager.find(AutoReseller.class, _id);
		if (desiredAutoResellerCercato != null) {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(desiredAutoResellerCercato);
			entityTransaction.commit();

		} else {
			System.out.println("Distributore automatico non trovato");
		}
	}
}
