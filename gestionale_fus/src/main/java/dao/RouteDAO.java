package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Route;

public class RouteDAO {

	private final EntityManager em;

	public RouteDAO(EntityManager _entityManager) {
		this.em = _entityManager;
	}
	
	public void save(Route route) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(route);
		et.commit();
		System.out.println("Utente salvato correttamente");
	}

	public Route findByName(String routeName) {
		Route r = em.find(Route.class, routeName);
		return r;
	}
	
	
}
