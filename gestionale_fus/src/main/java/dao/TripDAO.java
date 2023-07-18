package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Route;

public class TripDAO {

	private final EntityManager em;

	public TripDAO(EntityManager _entityManager) {
		this.em = _entityManager;
	}
	
	public void save(Route route) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(route);
		et.commit();
		System.out.println("Utente salvato correttamente");
	}
	
	
	
}
