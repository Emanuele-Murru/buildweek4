package app;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.AuthorizedResellerDAO;
import dao.AutoResellerDAO;
import dao.DailyDAO;
import dao.PassDAO;
import dao.RouteDAO;
import dao.UserDAO;
import dao.VehicleDAO;
import entities.AuthorizedReseller;
import entities.AutoReseller;
import entities.Daily;
import entities.Pass;
import entities.Route;
import entities.User;
import entities.Vehicle;
import enums.AutoResellerStatus;
import enums.VehicleType;
import utils.JpaUtil;

public class GestionaleFus {

	private static EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {

		EntityManager em = entityManagerFactory.createEntityManager();

		// - - - - - - - - - - - - - - - RESELLER
		AutoReseller ar1 = new AutoReseller("Primo", AutoResellerStatus.ACTIVE);
		AutoResellerDAO arDAO = new AutoResellerDAO(em);
//		arDAO.save(ar1);

		AuthorizedReseller atzr1 = new AuthorizedReseller("PrimoAtzr");
		AuthorizedResellerDAO atzrDAO = new AuthorizedResellerDAO(em);
//		atzrDAO.save(atzr1);

		// - - - - - - - - - - - - - - - TICKET > DAILY
		Daily d1 = new Daily(LocalDate.of(2023, 7, 18), atzr1);
		DailyDAO dDAO = new DailyDAO(em);
//		dDAO.createDailyTicket(d1);

		// - - - - - - - - - - - - - - - USER
		User u1 = new User("Mario", "Rossi", LocalDate.of(2000, 7, 18), "Milano");
		UserDAO uDAO = new UserDAO(em);
//		uDAO.save(u1);

		// - - - - - - - - - - - - - - - TICKET > PASS
		Pass p1 = new Pass(LocalDate.of(2023, 7, 18), atzr1, u1);
		PassDAO pDAO = new PassDAO(em);
//		pDAO.createPassTicket(p1);

//		pDAO.editSubscription(p1, "Weekly", LocalDate.of(2023, 7, 18));

		// - - - - - - - - - - - - - - - ROUTE
		Route r1 = new Route("First Route", "First Start", "First Terminal");
		RouteDAO rDAO = new RouteDAO(em);
		rDAO.save(r1);
		Route r2 = new Route("Second Route", "Second Start", "Second Terminal");
		rDAO.save(r2);
		Route r3 = new Route("Third Route", "Third Start", "Third Terminal");
		rDAO.save(r3);

		// - - - - - - - - - - - - - - - VEHICLE
		Vehicle v1 = new Vehicle(VehicleType.Bus);
		VehicleDAO vDAO = new VehicleDAO(em);
		vDAO.saveVehicle(v1);

		Vehicle v2 = new Vehicle(VehicleType.Tram);
		vDAO.saveVehicle(v2);

		Vehicle v3 = new Vehicle(VehicleType.Bus);
		vDAO.saveVehicle(v3);

		Vehicle v4 = new Vehicle(VehicleType.Tram);
		vDAO.saveVehicle(v4);

		// - - - - - - - - - - - - - - - DEFINEROUTE
		vDAO.defineRoute(v1, r1);

		// - - - - - - - - - - - - - - - TRIP
//		Trip t1 = new Trip(v1, 30);
//		TripDAO tDAO = new TripDAO(em);
////		tDAO.save(t1);
//
//		Trip t2 = new Trip(v2, 20);
////		tDAO.save(t2);
//
//		Trip t3 = new Trip(v3, 20);
////		tDAO.save(t3);
//
//		Trip t4 = new Trip(v4, 10);
////		tDAO.save(t4);

		em.close();
		entityManagerFactory.close();
	}

}
