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

		try {
			t.begin();

			em.persist(e);

			t.commit();
			System.out.println("Tessera creato/salvato con successo");
		} catch (Exception ex) {
			t.rollback();
			System.err.println("Errore durante la creazione della tessera: " + ex.getMessage());
		}
	}

	public Pass findPassByUserId(User user) {
		try {
			TypedQuery<Pass> query = em.createQuery("SELECT p FROM Pass p WHERE p.user.id = :user.id", Pass.class);
			query.setParameter("user", user);
			return query.getSingleResult();
		} catch (Exception e) {
			System.out.println("Errore durante la ricerca della tessera per l'utente: " + e.getMessage());
			return null;
		}
	}

	public void renewalPass(Pass pass) {
		if (pass != null) {
			if (pass.getExpiryDatePass().getYear() != LocalDate.now().getYear()) {

				try {
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

				} catch (Exception ex) {
					System.err.println("Errore durante il rinnovo della tessera: " + ex.getMessage());
				}
			} else {
				System.out.println("La tessera è ancora valida.");
			}
		} else {
			System.out.println("Tessera non trovata!");
		}
	}

	public void editSubscription(long id, String type, LocalDate data) {
		String subTypeString = type;
		SubscriptionType subType = SubscriptionType.valueOf(subTypeString);

		TypedQuery<Pass> query = em.createQuery("SELECT p FROM Pass p WHERE p.id = :id", Pass.class);

		Pass pass = query.getResultList().get(0);

		EntityTransaction t = em.getTransaction();

		if (pass.getSubType() == null) {
			t.begin();

			try {
				Query q = em.createQuery(
						"UPDATE Pass p SET subType = :subType, expireDateSub = :expireDateSub WHERE p.id = :id");

				q.setParameter("subType", subType);
				q.setParameter("expireDateSub",
						subType.equals(SubscriptionType.Weekly) ? data.plusWeeks(1) : data.plusMonths(1));
				q.setParameter("id", id);

				q.executeUpdate();

				t.commit();

				System.out.printf("Tipo di abbonamento aggiornato con successo a %s.\n", type);

			} catch (Exception ex) {
				t.rollback();
				System.err.println("Errore durante l'aggiornamento del tipo di abbonamento: " + ex.getMessage());
			}

		} else {
			System.out.println("Il Pass con ID " + id + " ha già un tipo di abbonamento.");
		}
	}

	public void checkSubType(Pass pass) {
		long id = pass.getId();
		SubscriptionType subType = pass.getSubType();

		try {
			TypedQuery<Long> query = em
					.createQuery("SELECT COUNT(p) FROM Pass p WHERE p.id = :id AND p.subType = :subType", Long.class);
			query.setParameter("id", id);
			query.setParameter("subType", subType);

			long result = query.getSingleResult();

			if (result > 0) {
				System.out.println("L'abbonamento " + subType + "é valido.");
			} else {
				System.out.println("L'abbonamento " + subType + ". Non è valido.");
			}
		} catch (Exception ex) {
			System.err.println("Errore durante il controllo dell'abbonamento: " + ex.getMessage());
		}
	}
}