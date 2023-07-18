package dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Daily;
import entities.Route;
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

	// * * * * * TIMBRA TICKET * * * * *
	// * * * * * WORK IN PROGRESS * * * * *
	public void timbraTicket(Ticket _ticket, LocalDate _dataTimbratura) {

		if (ticket.getDataTimbratura() != null) {
			System.out.println("Il biglietto è già stato timbrato.");
		}

		ticket.setDate(dataTimbratura)

	}

	// total obliterated daily ticket
	// * * * * * WORK IN PROGRESS * * * * *
	public int obliteratedDaily(Vehicle _vehicle) {

		TypedQuery<Ticket> query = em.createQuery(
				"SELECT t FROM Ticket t WHERE t.vehicle = :_vehicle AND t.dataTimbratura IS NOT NULL", Ticket.class);
		query.setParameter("_vehicle", _vehicle);

		List<Ticket> tickets = query.getResultList();

		return tickets.size();
	}

	// daily ticket between dates
	// * * * * * WORK IN PROGRESS * * * * *
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
		System.out.println(
				(vehicle.getRoute() == null) ? "Rotta assegnata con successo" : "Rotta modificata con successo");
		vehicle.setRoute(route);
	}
}