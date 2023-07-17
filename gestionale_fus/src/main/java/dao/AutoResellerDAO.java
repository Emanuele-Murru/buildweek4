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
		System.out.println("AutoReseller saved");
	}

	public AutoReseller findById(long _id) {
		AutoReseller autoResellerCercato = entityManager.find(AutoReseller.class, _id);
		return autoResellerCercato;
	}

	public void findByIdAndDelete(long _id) {
		AutoReseller autoResellerCercato = entityManager.find(AutoReseller.class, _id);
		if (autoResellerCercato != null) {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(autoResellerCercato);
			entityTransaction.commit();

		} else {
			System.out.println("AutoReseller not found");
		}
	}
}
