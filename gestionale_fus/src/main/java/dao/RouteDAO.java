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
		try {
			et.begin();
			em.persist(route);
			et.commit();
			System.out.println("Utente salvato correttamente");
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
