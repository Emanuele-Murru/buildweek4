package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.AuthorizedReseller;

public class AuthorizedResellerDAO {

	private final EntityManager em;

	public AuthorizedResellerDAO(EntityManager _entityManager) {this.em = _entityManager;}

	public void save(AuthorizedReseller _authorizedReseller) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(_authorizedReseller);
		et.commit();
		System.out.println("Rivenditore autorizzato salvato correttamente");
	}

	public AuthorizedReseller findById(long _id) {
		AuthorizedReseller dar = em.find(AuthorizedReseller.class, _id);
		return dar;
	}

	public void findByIdAndDelete(long _id) {
		AuthorizedReseller dar = em.find(AuthorizedReseller.class, _id);
		if (dar != null) {
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.remove(dar);
			et.commit();

		} else System.out.println("Rivenditore autorizzato non trovato");
	}
}
