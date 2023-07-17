package dao;

import javax.persistence.EntityManager;

public class ResellerDAO {

	private final EntityManager entityManager;

	public ResellerDAO(EntityManager _entityManager) {
		this.entityManager = _entityManager;
	}

}
