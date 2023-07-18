package dao;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Pass;
import entities.User;
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
		System.out.println("Tessera creato/salvato con successo");
	}

	public Pass findPassByUserId(User user) {
		TypedQuery<Pass> query = em.createQuery("SELECT p FROM Pass p WHERE p.user.id = :user.id", Pass.class);
		query.setParameter("user", user);
		return query.getSingleResult();
	}

	public void renewalPass(Pass pass) {
		if (pass != null) {
			if (pass.getExpiryDatePass().getYear() != LocalDate.now().getYear()) {

				long id = pass.getId();
				LocalDate data = LocalDate.of(LocalDate.now().getYear(), 12, 31);

				EntityTransaction t = em.getTransaction();
				t.begin();
				Query q = em.createQuery("UPDATE Pass p SET expiryDatePass = :expiryDatePass WHERE id = :id");
				q.setParameter("expiryDatePass", data);
				q.setParameter("id", id);

				q.executeUpdate();

				t.commit();
			}
		} else
			System.out.println("Tessera non trovata!");
	}

	public void renewalPass(User user, SubscriptionType type) {
		Pass pass = findPassByUserId(user);

		if (pass == null) {
			pass = user.getPass();
			LocalDate newExpiryDate = pass.getExpiryDatePass().plusMonths(1);
			pass.setExpiryDatePass(newExpiryDate);

			EntityTransaction t = em.getTransaction();
			t.begin();
			em.merge(pass);
			t.commit();

			System.out.println("L'abbonamento è stato rinnovato con successo");
		} else {
			System.out.println("L'utente non ha ancora un abbonamento");
		}
	}
}
