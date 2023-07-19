package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Vehicle;
import entities.VehicleStatusUpdate;

public class VehicleStatusUpdateDAO {

	private final EntityManager em;

	public VehicleStatusUpdateDAO(EntityManager _entityManager) {
		this.em = _entityManager;
	}
	
	// - - - - - - - - - - - - - - - - - - - - save
	public void save(VehicleStatusUpdate update) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(update);
		et.commit();
		
		
		System.out.println("Status aggiornato correttamente");
	}
	
	// - - - - - - - - - - - - - - - - - - - - findById
	public VehicleStatusUpdate findById(long _id) {
		VehicleStatusUpdate du = em.find(VehicleStatusUpdate.class, _id);
		return du;
	}
	
	// - - - - - - - - - - - - - - - - - - - - findByIdAndDelete
	public void findByIdAndDelete(long _id) {
		VehicleStatusUpdate du = em.find(VehicleStatusUpdate.class, _id);
		if (du != null) {
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.remove(du);
			et.commit();

		} else System.out.println("Status non trovato");
	}
	
	public List<VehicleStatusUpdate> maintenanceHistory(Vehicle vehicle) {
		TypedQuery<VehicleStatusUpdate> query = em.createQuery(
				"SELECT v FROM VehicleStatusUpdate v WHERE v.vehicle = :_vehicle", VehicleStatusUpdate.class);
		query.setParameter("_vehicle", vehicle);
		
		return query.getResultList();
	}
}