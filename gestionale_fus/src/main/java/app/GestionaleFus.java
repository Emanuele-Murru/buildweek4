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

		// - - - - - - - - - - - - - - - DAO
		ResellerDAO rDAO = new ResellerDAO(em);
		AutoResellerDAO arDAO = new AutoResellerDAO(em);
		AuthorizedResellerDAO atzrDAO = new AuthorizedResellerDAO(em);
		DailyDAO dDAO = new DailyDAO(em);
		UserDAO uDAO = new UserDAO(em);
		PassDAO pDAO = new PassDAO(em);
		RouteDAO roDAO = new RouteDAO(em);
		VehicleDAO vDAO = new VehicleDAO(em);
		TripDAO tDAO = new TripDAO(em);

		AuthorizedReseller atzr1 = new AuthorizedReseller("PrimoAtzr");
		atzrDAO.save(atzr1);

		User u1 = new User("Mario", "Rossi", LocalDate.of(2000, 7, 18), "Milano");
		uDAO.save(u1);

		Pass p1 = new Pass(LocalDate.of(2023, 7, 18), atzr1, u1);
		pDAO.createPassTicket(p1);

		pDAO.editSubscription(57, "Weekly", LocalDate.of(2023, 7, 20));

		pDAO.checkSubType(p1);

		// - - - - - - - - - - - - - - - RESELLER
//		AutoReseller ar1 = new AutoReseller("Primo", AutoResellerStatus.ACTIVE);
//		arDAO.save(ar1);
//
//		Daily d1 = new Daily(LocalDate.of(2023, 7, 18), ar1);
//		dDAO.createDailyTicket(d1);
//
//		Daily d2 = new Daily(LocalDate.of(2023, 7, 18), ar1);
//		dDAO.createDailyTicket(d2);
//
//		Daily d3 = new Daily(LocalDate.of(2023, 7, 18), ar1);
//		dDAO.createDailyTicket(d2);
//
//		List<Ticket> tickets = rDAO.getResellerTicketsByTime(ar1, LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 31));
//
//		if (!tickets.isEmpty()) {
//			for (Ticket ticket : tickets) {
//				System.out.println(ticket.toString());
//			}
//		} else {
//			System.out.println("Nessun biglietto trovato");
//		}

//		AuthorizedReseller atzr1 = new AuthorizedReseller("PrimoAtzr");
//		atzrDAO.save(atzr1);
//
//		// - - - - - - - - - - - - - - - TICKET > DAILY
//		Daily d1 = new Daily(LocalDate.of(2023, 7, 18), atzr1);
//		dDAO.createDailyTicket(d1);
//
//		// - - - - - - - - - - - - - - - USER
//		User u1 = new User("Mario", "Rossi", LocalDate.of(2000, 7, 18), "Milano");
//		uDAO.save(u1);
//
//		// - - - - - - - - - - - - - - - TICKET > PASS
//		Pass p1 = new Pass(LocalDate.of(2023, 7, 18), atzr1, u1)
//		pDAO.createPassTicket(p1);
//
//		pDAO.editSubscription(p1, "Weekly", LocalDate.of(2023, 7, 18));
//
//		// - - - - - - - - - - - - - - - ROUTE
//		Route r1 = new Route("First Route", "First Start", "First Terminal");
//		rDAO.save(r1);
//		Route r2 = new Route("Second Route", "Second Start", "Second Terminal");
//		rDAO.save(r2);
//		Route r3 = new Route("Third Route", "Third Start", "Third Terminal");
//		rDAO.save(r3);
//
//		// - - - - - - - - - - - - - - - VEHICLE
//		Vehicle v1 = new Vehicle(VehicleType.Bus);
//		vDAO.saveVehicle(v1);
//
//		Vehicle v2 = new Vehicle(VehicleType.Tram);
//		vDAO.saveVehicle(v2);
//
//		Vehicle v3 = new Vehicle(VehicleType.Bus);
//		vDAO.saveVehicle(v3);
//
//		Vehicle v4 = new Vehicle(VehicleType.Tram);
//		vDAO.saveVehicle(v4);
//
//		// - - - - - - - - - - - - - - - DEFINEROUTE
//		vDAO.defineRoute(v1, r1);
//		vDAO.defineRoute(v2, r1);
//		vDAO.defineRoute(v3, r2);
//		vDAO.defineRoute(v4, r2);
//
//		// - - - - - - - - - - - - - - - TRIP
//		Trip t1 = new Trip(v1, r1, 30);
//		tDAO.save(t1);
//
//		Trip t2 = new Trip(v2, r1, 20);
//		tDAO.save(t2);
//
//		Trip t3 = new Trip(v3, r2, 20);
//		tDAO.save(t3);
//
//		Trip t4 = new Trip(v4, r2, 10);
//		tDAO.save(t4);

//		AutoReseller ar1 = new AutoReseller("Primo", AutoResellerStatus.ACTIVE);
//		arDAO.save(ar1);

//		ResellerDAO rDAO = new ResellerDAO(em);
//		System.out.println(rDAO.findById(1));

		em.close();
		entityManagerFactory.close();
	}

}
