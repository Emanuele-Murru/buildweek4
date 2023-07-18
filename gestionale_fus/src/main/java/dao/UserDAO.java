package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.User;

public class UserDAO {

	private final EntityManager entityManager;

	public UserDAO(EntityManager _entityManager) {
		this.entityManager = _entityManager;
	}

	// - - - - - - - - - - - - - - - - - - - - save
	public void save(User _user) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(_user);
		entityTransaction.commit();
		System.out.println("Utente salvato correttamente");
	}

	// - - - - - - - - - - - - - - - - - - - - findById
	public User findById(long _id) {
		User desiredUser = entityManager.find(User.class, _id);
		return desiredUser;
	}

	// - - - - - - - - - - - - - - - - - - - - findByIdAndDelete
	public void findByIdAndDelete(long _id) {
		User desiredUser = entityManager.find(User.class, _id);
		if (desiredUser != null) {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(desiredUser);
			entityTransaction.commit();

		} else {
			System.out.println("Utente non trovato");
		}
	}
}
