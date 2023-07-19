package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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

		TypedQuery<Long> query = em.createQuery("SELECT MAX (id) FROM User", Long.class);
		long id = query.getSingleResult();
		System.out.printf("L'utente %s %s è stato salvato con id: %d\n", _user.getName(), _user.getSurname(), id);
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

		} else
			System.out.println("Utente non trovato");
	}
}
