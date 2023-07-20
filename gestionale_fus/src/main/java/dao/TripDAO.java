package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Route;
import entities.Trip;
import entities.Vehicle;

public class TripDAO {

	private final EntityManager em;

	public TripDAO(EntityManager _entityManager) {
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
			System.err.println("Errore durante il salvataggio del viaggio: " + ex.getMessage());
		}
	}

	// total trip per vehicle
	// * * * * * WORK IN PROGRESS * * * * *
	public String tripsPerVehicle(Vehicle _vehicle) {

		TypedQuery<Trip> query = em
				.createQuery("SELECT t FROM Trip t WHERE t.vehicle = :_vehicle AND t.tripTime IS NOT NULL", Trip.class);
		query.setParameter("_vehicle", _vehicle);

		TypedQuery<Trip> query2 = em.createQuery("SELECT t.tripTime FROM Trip t WHERE t.vehicle = :_vehicle",
				Trip.class);
		query.setParameter("_vehicle", _vehicle);
		
		List<Trip> trips = query.getResultList();
		List<Trip> times = query2.getResultList();

		String out = "Numero percorrenze: " + trips.size() + " (";
		for (Trip t : times)
			out += t.getTripTime() + ", ";
		out += ")";

		return out;
	}

}
