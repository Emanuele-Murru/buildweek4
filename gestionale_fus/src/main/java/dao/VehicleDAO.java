package dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Daily;
import entities.Route;
import entities.Ticket;
import entities.Vehicle;
import enums.VehicleType;

public class VehicleDAO {

	private final EntityManager em;

	public VehicleDAO(EntityManager em) {
		this.em = em;
	}

	// save vehicle
	public void saveVehicle(Vehicle vehicle) {

		EntityTransaction t = em.getTransaction();

		t.begin();

		em.persist(vehicle);

		t.commit();

		System.out.println(
				(vehicle.getType().equals(VehicleType.Bus)) ? "Bus salvato con successo" : "Tram salvato con successo");

	}

	public Vehicle findById(long _id) {
		Vehicle v = em.find(Vehicle.class, _id);
		return v;
	}

	// validate daily ticket
	public void validateDaily(Daily daily, LocalDate obliterateDate) {

		if (daily.getObliterateDate() != null) {

			if (daily.getExpiryDate() != obliterateDate && daily.getExpiryDate().isAfter(obliterateDate)) {

				EntityTransaction t = em.getTransaction();
				t.begin();
				Query q = em.createQuery("UPDATE Daily d SET d.obliterateDate = :obliterateDate WHERE d.id = :id");
				q.setParameter("obliterateDate", obliterateDate);
				q.setParameter("daily.id", daily.getId());

				q.executeUpdate();

				t.commit();
				System.out.println("Biglietto timbrato con successo");

			} else {
				System.out.println("Biglietto scaduto");

			}
		} else {
			System.out.println("Biglietto già timbrato");
		}

	}

	// total obliterated daily ticket
	public int obliteratedDaily(Vehicle _vehicle) {

		TypedQuery<Ticket> query = em.createQuery(
				"SELECT t FROM Ticket t WHERE t.vehicle = :_vehicle AND t.dataTimbratura IS NOT NULL", Ticket.class);
		query.setParameter("_vehicle", _vehicle);

		List<Ticket> tickets = query.getResultList();

		return tickets.size();
	}

	// daily ticket between dates
	public int dailyBetweenDates(Vehicle _vehicle, LocalDate _startDate, LocalDate _endDate) {

		TypedQuery<Ticket> query = em.createQuery(
				"SELECT t FROM Ticket t WHERE t.vehicle = :_vehicle AND t.dataTimbratura BETWEEN :_startDate AND :_endDate",
				Ticket.class).setParameter("_vehicle", _vehicle).setParameter("_startDate", _startDate)
				.setParameter("_endDate", _endDate);

		List<Ticket> tickets = query.getResultList();

		return tickets.size();

	}

	// define route
	public void defineRoute(Vehicle vehicle, Route route) {

		EntityTransaction t = em.getTransaction();
		t.begin();

		Query query = em.createQuery("UPDATE Vehicle v SET v.route = :route WHERE v.id = :vehicleId");
		query.setParameter("route", route);
		query.setParameter("vehicleId", vehicle.getId());

		int routeUpdate = query.executeUpdate();

		t.commit();

//		if (routeUpdate > 0) {
//			System.out.println("Rotta assegnata con successo");
//		} else {
//			System.out.println("Rotta non assegnata: vehicle non trovato o route non valida");
//		}

		System.out.println(
				(routeUpdate > 0) ? "Rotta assegnata con successo" : "Veicolo non trovato e/o rotta non valida");

//		System.out.println(
//				(vehicle.getRoute() == null) ? "Rotta assegnata con successo" : "Rotta modificata con successo");
//		vehicle.setRoute(route);
	}
}
