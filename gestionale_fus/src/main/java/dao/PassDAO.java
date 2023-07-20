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
				System.out.println("Tessera rinnovata con successo!");
			} else
				System.out.println("La tessera è ancora valida.");
		} else
			System.out.println("Tessera non trovata!");
	}

	public void editSubscription(long passId, String type, LocalDate data) {
		
		EntityTransaction t = em.getTransaction();
		t.begin();

		Query query = em.createQuery("UPDATE Pass p SET p.subType = :subType WHERE p.id = :passId");
		query.setParameter("subType", SubscriptionType.valueOf(type));
		query.setParameter("passId", passId);

		query.executeUpdate();

		t.commit();
		
		System.out.printf("Abbonamento aggiornato con successo a %s.\n", type);
	}

	public void checkSubType(Pass pass) {
		long id = pass.getId();
		SubscriptionType subType = pass.getSubType();

		TypedQuery<Long> query = em.createQuery("SELECT COUNT(p) FROM Pass p WHERE p.id = :id AND p.subType = :subType",
				Long.class);
		query.setParameter("id", id);
		query.setParameter("subType", subType);

		long result = query.getSingleResult();

		if (result > 0) {
			System.out.println("L'abbonamento " + subType + "é valido.");
		} else {
			System.out.println("L'abbonamento " + subType + ". Non è valido.");
		}
	}

}