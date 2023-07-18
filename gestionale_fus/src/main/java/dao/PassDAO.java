package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Pass;

public class PassDAO {
	private final EntityManager em;
	
	public PassDAO(EntityManager em) {
		this.em = em;
	}
	
	public void createPassTicket(Pass e) {
		EntityTransaction t = em.getTransaction();
		
		t.begin();
		
		em.persist(e);
		
		t.commit();
		System.out.println("Abbonamento creato/salvato con successo");
	}
	
	public void renewalPass() {
		
	}
}
