package app;

import java.time.LocalDate;
import java.util.Scanner;

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

		// entity manager 
		EntityManager em = entityManagerFactory.createEntityManager();
		
		// scanner
		Scanner scanner = new Scanner(System.in);
		
		// while flags variables
		int c1 = 0;
		int c2 = 0;
		
		// MANAGED ENTITIES-----------------------------------------------------------------
		
		AutoResellerDAO autoReseller = new AutoResellerDAO(em);
		AuthorizedResellerDAO authorizedReseller = new AuthorizedResellerDAO(em);
		DailyDAO daily = new DailyDAO(em);
		UserDAO user = new UserDAO(em);
		PassDAO pass = new PassDAO(em);
		
		// MAIN MENU------------------------------------------------------------------------
		
		do {
			System.out.println("-------------------------GESTIONALE F.U.S. TRASPORTI-------------------------\n");
			System.out.println("Dove vuoi effettuare operazioni? (Completare un'operazione riporta a questo menù)");
			System.out.println("1 - Utenti");
			System.out.println("2 - Rivenditori");
			System.out.println("3 - Biglietti/Tessere");
			System.out.println("4 - Mezzi");
			System.out.println("5 - Tratte\n");
			System.out.println("0 - Chiudi Programma");

			c1 = Integer.parseInt(scanner.nextLine());
			
			System.out.println();
			if(c1 != 0) {
				switch(c1) {
				case 1:
					System.out.println("Quale operazione vuoi effettuare?");
					System.out.println("1 - Inserire nuovo utente");
					System.out.println("2 - Cerca utente tramite ID");
					System.out.println("3 - Elimina utente tramite ID");
					System.out.println("4 - Validità della tessera di un utente\n");
					System.out.println("0 - Torna al menù");
					
					c2 = Integer.parseInt(scanner.nextLine());
					
					System.out.println();

					if(c2 != 0) {
						switch(c2) {
							case 1:
								System.out.println("Funziona");
								System.out.println();

								break;
						}
					}
					
					break;
					
				case 2:
					
					break;
					
				case 3:
						
					break;
										
				case 4:
					
					break;
					
				case 5:
						
					break;
				}
			}
			
			
		}while(c1 != 0);
		
//		AutoReseller ar1 = new AutoReseller("Primo", AutoResellerStatus.ACTIVE);
//		autoReseller.save(ar1);
//
//		AuthorizedReseller atzr1 = new AuthorizedReseller("PrimoAtzr");
//		
//		atzrDAO.save(atzr1);
//
//		Daily d1 = new Daily(LocalDate.of(2023, 7, 18), atzr1);
//		
//		dDAO.createDailyTicket(d1);
//
//		User u1 = new User("Mario", "Rossi", LocalDate.of(2000, 7, 18), "Milano");
//		
//		uDAO.save(u1);
//
//		Pass p1 = new Pass(LocalDate.of(2023, 7, 18), atzr1, u1);
//		
//		pDAO.createPassTicket(p1);

		// pDAO.editSubscription(p1, "Weekly", LocalDate.of(2023, 7, 18));

		// entity manager close
		em.close();
		entityManagerFactory.close();
		
		// scanner close
		scanner.close();
		
		System.out.println("Pogramma chiuso con successo.");
		
		
	}

}
