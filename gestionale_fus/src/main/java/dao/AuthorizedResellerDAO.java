package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.AuthorizedReseller;

public class AuthorizedResellerDAO {

	private final EntityManager em;

	public AuthorizedResellerDAO(EntityManager _entityManager) {
		this.em = _entityManager;
	}

	public void save(AuthorizedReseller _authorizedReseller) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(_authorizedReseller);
			et.commit();
			System.out.println("Rivenditore autorizzato salvato correttamente");
		} catch (Exception ex) {
			et.rollback();
		}

	}

	public AuthorizedReseller findById(long _id) {
		try {
			AuthorizedReseller dar = em.find(AuthorizedReseller.class, _id);
			return dar;
		} catch (Exception ex) {
			System.err.println("Errore durante la ricerca del rivenditore autorizzato per ID: " + ex.getMessage());
			return null;
		}
	}

	public void findByIdAndDelete(long _id) {
		EntityTransaction et = em.getTransaction();

		try {
			AuthorizedReseller dar = em.find(AuthorizedReseller.class, _id);
			if (dar != null) {
				et.begin();
				em.remove(dar);
				et.commit();

			} else {
				System.err.println("Rivenditore autorizzato non trovato");
			}
		} catch (Exception ex) {
			et.rollback();
			System.out.println("Errore durante la rimozione del rivenditore autorizzato:" + ex.getMessage());
		}
	}
}
