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
			}else System.out.println("La tessera è ancora valida.");
		}else System.out.println("Tessera non trovata!");
	}
	
	public void editSubscription(Pass pass, String type, LocalDate data) {
		String subTypeString = type;
		SubscriptionType subType = SubscriptionType.valueOf(subTypeString);
		long id = pass.getId();
		
	    EntityTransaction t = em.getTransaction();

	    if(pass.getSubType() == null) {
	    	
	    	t.begin();
	    	Query q = em.createQuery("UPDATE Pass p SET subType = :subType, expireDateSub = :expireDateSub WHERE p.id = :id");
	    	
	    	q.setParameter("subType", subType);
	    	q.setParameter("expireDateSub", subType.equals(SubscriptionType.Weekly)? data.plusWeeks(1) : data.plusMonths(1));
	    	q.setParameter("id", id);
	    	
	    	q.executeUpdate();
	    	
	    	t.commit();
	    	
	    	System.out.println("Tipo di abbonamento aggiornato con successo.");
	    	
	    }
	}

}