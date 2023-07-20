package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Trip;
import entities.Vehicle;

public class TripDAO {

	private final EntityManager em;

	public TripDAO(EntityManager _entityManager) {
		this.em = _entityManager;
	}

	public void save(Trip trip) {
		EntityTransaction et = em.getTransaction();

		try {
		et.begin();
		em.persist(trip);
		et.commit();
		System.out.println("Trip salvato correttamente");

		} catch (Exception ex) {
			et.rollback();
			System.err.println("Errore durante il salvataggio del viaggio: " + ex.getMessage());
		}
		
	}

	public Trip findById(long _id) {
		Trip t = em.find(Trip.class, _id);
		return t;
	}

	// total trip per vehicle
	// * * * * * WORK IN PROGRESS * * * * *
	public String tripsPerVehicle(Vehicle _vehicle) {

		try {
			TypedQuery<Trip> query = em.createQuery(
					"SELECT t FROM Trip t WHERE t.vehicle = :_vehicle AND t.tripTime IS NOT NULL", Trip.class);
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
		} catch (Exception ex) {
			System.err.println(
					"Errore durante il caricamento della lista dei viaggi totali per veicolo: " + ex.getMessage());
			return null;
		}
	}

}
