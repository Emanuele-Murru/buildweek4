package dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Pass;
import entities.User;
import entities.Vehicle;
import enums.SubscriptionType;

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

	public Pass findPassByUserId(User user) {
		TypedQuery<Pass> query = em.createQuery("SELECT p FROM Pass p WHERE p.user.id = :user.id", Pass.class);
		query.setParameter("user", user);
		List<Pass> passList = query.getResultList();
		return passList.isEmpty() ? null : passList.get(0);
	}

	public void renewalPass(User user, SubscriptionType type) {
	    Pass pass = findPassByUserId(user);

	    if (pass == null) {
	        pass = user.getPass();
	        LocalDate newExpiryDate = pass.getExpiryDatePass().plusMonths(1);
	        pass.setExpiryDatePass(newExpiryDate);

	        EntityTransaction t = em.getTransaction();
	        t.begin();
	        em.merge(pass); // Qui si usa merge e non persist per aggiornare l'entità esistente nel database
	        t.commit();

	        System.out.println("L'abbonamento è stato rinnovato con successo");
	    } else {
	        System.out.println("L'utente non ha ancora un abbonamento");
	    }
	}
}
