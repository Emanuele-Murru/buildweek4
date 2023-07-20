package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Daily;

public class DailyDAO {
	private final EntityManager em;

	public DailyDAO(EntityManager em) {
		this.em = em;
	}

	public void createDailyTicket(Daily e) {
		EntityTransaction t = em.getTransaction();

		try {
			t.begin();

			em.persist(e);

			t.commit();
			
			TypedQuery<Long> query = em.createQuery("SELECT MAX (id) FROM Ticket", Long.class);
			long id = query.getSingleResult();
			System.out.printf("Ticket giornaliero creato/salvato id: %d\n", id);
			
		} catch (Exception ex) {
			t.rollback();
			System.err.println("Errore durante la creazione del ticket" + ex.getMessage());
		}
	}
}
