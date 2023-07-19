package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.AutoResellerDAO;
import entities.AutoReseller;
import enums.AutoResellerStatus;
import utils.JpaUtil;

public class GestionaleFus {

	private static EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {

		EntityManager em = entityManagerFactory.createEntityManager();

		AutoReseller ar1 = new AutoReseller("Primo", AutoResellerStatus.ACTIVE);

		AutoResellerDAO arDAO = new AutoResellerDAO(em);
		arDAO.save(ar1);

		// Daily d1 = new Daily(LocalDate.of(2023, 7, 18), );

	}

}
