package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.User;

public class UserDAO {

	private final EntityManager em;

	public UserDAO(EntityManager _entityManager) {
		this.em = _entityManager;
	}

	// - - - - - - - - - - - - - - - - - - - - save
	public void save(User _user) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(_user);
		et.commit();
		System.out.println("Utente salvato correttamente");
	}

	// - - - - - - - - - - - - - - - - - - - - findById
	public User findById(long _id) {
		User du = em.find(User.class, _id);
		return du;
	}

	// - - - - - - - - - - - - - - - - - - - - findByIdAndDelete
	public void findByIdAndDelete(long _id) {
		User du = em.find(User.class, _id);
		if (du != null) {
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.remove(du);
			et.commit();

		} else System.out.println("Utente non trovato");
	}
}
