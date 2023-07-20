package app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.AuthorizedResellerDAO;
import dao.AutoResellerDAO;
import dao.DailyDAO;
import dao.PassDAO;
import dao.ResellerDAO;
import dao.RouteDAO;
import dao.TripDAO;
import dao.UserDAO;
import dao.VehicleDAO;
import entities.AuthorizedReseller;
import entities.Pass;
import entities.User;
import utils.JpaUtil;

public class GestionaleFus {

	private static EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {

		EntityManager em = entityManagerFactory.createEntityManager();

		
		em.close();
		entityManagerFactory.close();
	}

}
