package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Daily;

public class DailyDAO {
	private final EntityManager em;

	public DailyDAO(EntityManager em) {
		this.em = em;
	}

	public void createDailyTicket(Daily e) {
		EntityTransaction t = em.getTransaction();

		t.begin();
		
		em.persist(e);
		
		t.commit();
		System.out.println("Ticket giornaliero creato/salvato con successo");

	}
}
