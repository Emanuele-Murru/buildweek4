package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entities.AutoReseller;

public class AutoResellerDAO {

	private final EntityManager em;

	public AutoResellerDAO(EntityManager _entityManager) {
		this.em = _entityManager;
	}

	public void save(AutoReseller _autoReseller) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(_autoReseller);
		et.commit();
		System.out.println("Distributore automatico salvato con successo");
	}

	public AutoReseller findById(long _id) {
		AutoReseller dar = em.find(AutoReseller.class, _id);
		return dar;
	}

	public void findByIdAndDelete(long _id) {
		AutoReseller dar = em.find(AutoReseller.class, _id);
		if (dar != null) {
			EntityTransaction entityTransaction = em.getTransaction();
			entityTransaction.begin();
			em.remove(dar);
			entityTransaction.commit();

		} else System.out.println("Distributore automatico non trovato");
	}

	public void changeStatus(long _id, String _status) {
		AutoReseller dar = em.find(AutoReseller.class, _id);
		if (dar != null) {
			EntityTransaction et = em.getTransaction();
			et.begin();

			Query query = em.createQuery("UPDATE AutoReseller a SET a.status = :_status WHERE a.id = :_id")
					.setParameter("_status", _status);

			query.executeUpdate();

			et.commit();
			System.out.println("Lo stato del distributore automatico è stato aggiornato con successo");

		} else System.out.println("Distributore automatico non trovato");
	}
}
