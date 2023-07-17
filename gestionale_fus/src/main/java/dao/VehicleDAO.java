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

	public VehicleDAO(EntityManager em) {this.em = em;}
	
	// save vehicle
	public void saveVehicle(Vehicle vehicle) {
		
		EntityTransaction t = em.getTransaction();

		t.begin(); 

		em.persist(vehicle);		

		t.commit();
		
		System.out.println((vehicle.getType().equals(VehicleType.Bus)) ? "Bus salvato con successo" : "Tram salvato con successo");
		
	}
	
	// total obliterated daily ticket 
	public List<Daily> obliteratedDaily(Vehicle vehicle) {
		TypedQuery<Daily> query = em.createQuery("SELECT l FROM Lettura l WHERE l.anno = :anno", Daily.class);
		query.setParameter("vehicle", vehicle);
		return query.getResultList();
	}
	
	// daily ticket between dates
	public List<Daily> dailyBetweenDates(Vehicle vehicle, LocalDate start, LocalDate end) {
		TypedQuery<Daily> query = em.createQuery("SELECT l FROM Lettura l WHERE l.anno = :anno", Daily.class);
		query.setParameter("vehicle", vehicle);
		return query.getResultList();
	}

	// define route
	public void defineRoute(Vehicle vehicle, Route route) {
		System.out.println((vehicle.getRoute() == null) ? "Rotta assegnata con successo" : "Rotta modificata con successo");
		vehicle.setRoute(route);
	}
}
