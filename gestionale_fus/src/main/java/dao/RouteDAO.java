package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Route;

public class RouteDAO {

	private final EntityManager em;

	public RouteDAO(EntityManager _entityManager) {
		this.em = _entityManager;
	}

	public void saveRoute(Route route) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(route);
			et.commit();
			
			System.out.printf("La tratta %s è stata creata.\n", route.getRouteName());
		} catch (Exception ex) {
			et.rollback();
		}
	}

	public Route findByName(String routeName) {
		try {
			Route r = em.find(Route.class, routeName);
			return r;
		} catch (Exception ex) {
			System.err.println("Errore durante la ricerca del percorso: " + ex.getMessage());
			return null;
		}
	}
}
