package app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.AuthorizedResellerDAO;
import dao.AutoResellerDAO;
import dao.DailyDAO;
import dao.PassDAO;
import dao.UserDAO;
import entities.AuthorizedReseller;
import entities.AutoReseller;
import entities.Daily;
import entities.Pass;
import entities.User;
import enums.AutoResellerStatus;
import utils.JpaUtil;

public class GestionaleFus {

	private static EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {

		EntityManager em = entityManagerFactory.createEntityManager();

		AutoReseller ar1 = new AutoReseller("Primo", AutoResellerStatus.ACTIVE);
		AutoResellerDAO arDAO = new AutoResellerDAO(em);
		arDAO.save(ar1);

		AuthorizedReseller atzr1 = new AuthorizedReseller("PrimoAtzr");
		AuthorizedResellerDAO atzrDAO = new AuthorizedResellerDAO(em);
		atzrDAO.save(atzr1);

		Daily d1 = new Daily(LocalDate.of(2023, 7, 18), atzr1);
		DailyDAO dDAO = new DailyDAO(em);
		dDAO.createDailyTicket(d1);

		User u1 = new User("Mario", "Rossi", LocalDate.of(2000, 7, 18), "Milano");
		UserDAO uDAO = new UserDAO(em);
		uDAO.save(u1);

		Pass p1 = new Pass(LocalDate.of(2023, 7, 18), atzr1, u1);
		PassDAO pDAO = new PassDAO(em);
		pDAO.createPassTicket(p1);

		pDAO.editSubscription(p1, "Weekly", LocalDate.of(2023, 7, 18));

		em.close();
		entityManagerFactory.close();
	}

}
