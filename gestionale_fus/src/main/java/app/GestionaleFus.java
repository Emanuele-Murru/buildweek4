package app;

import java.time.LocalDate;
import java.util.Scanner;

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
import entities.AutoReseller;
import entities.Daily;
import entities.Pass;
import entities.Reseller;
import entities.Route;
import entities.User;
import entities.Vehicle;
import enums.AutoResellerStatus;
import enums.VehicleType;
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
		int c3 = 0;
		int c4 = 0;
		int c5 = 0;
		
		// MANAGED ENTITIES-----------------------------------------------------------------
		
		ResellerDAO resellerDAO = new ResellerDAO(em);
		AutoResellerDAO autoResellerDAO = new AutoResellerDAO(em);
		AuthorizedResellerDAO authorizedResellerDAO = new AuthorizedResellerDAO(em);
		DailyDAO dailyDAO = new DailyDAO(em);
		UserDAO userDAO = new UserDAO(em);
		PassDAO passDAO = new PassDAO(em);
		VehicleDAO vehicleDAO = new VehicleDAO(em);
		RouteDAO routeDAO = new RouteDAO(em);
		
		// MAIN MENU------------------------------------------------------------------------
		
		do {
			System.out.println("-------------------------GESTIONALE F.U.S. TRASPORTI-------------------------\n");
			System.out.println("Selezionare tipo di profilo");
			System.out.println("1 - Utenti");
			System.out.println("2 - Admin\n");
			System.out.println("0 - Chiudi Programma");

			c1 = Integer.parseInt(scanner.nextLine());
			
			System.out.println();
			
			if(c1 != 0) {
				switch(c1) {
					case 1:
						
						System.out.println("Quale operazione vuoi effettuare?");
						System.out.println("1 - Crea nuovo utente");
						System.out.println("2 - Accedi");
						System.out.println("3 - Crea biglietto giornaliero\n");
						System.out.println("0 - Torna al menù");
						
						c2 = Integer.parseInt(scanner.nextLine());
						
						System.out.println();
						
						if(c2 != 0) {
							switch(c2) {
								case 1:
									// ---------------------------------------------------------------NEW USER
									System.out.print("Inserire nome nuovo utente: ");
									String _name = scanner.nextLine();
									System.out.print("Inserire cognome nuovo utente: ");
									String _surname = scanner.nextLine();
									System.out.print("Inserire nuova password: ");
									String _password = scanner.nextLine();
									System.out.print("Inserire data nascita (yyyy-mm-dd): ");
									String _birth = scanner.nextLine();
									System.out.print("Inserire luogo di nascita: ");
									String _birthPlace = scanner.nextLine();
									System.out.println();
									userDAO.save(new User(_name, _surname, _password, LocalDate.parse(_birth), _birthPlace));
									System.out.println();
									break;
									
								case 2:
									// ---------------------------------------------------------------LOG IN USER
									System.out.print("Inserisci il tuo ID: ");
									long _idLog = Long.parseLong(scanner.nextLine());
									System.out.print("Inserisci la tua password: ");
									String _passwordLog = scanner.nextLine();
									
									System.out.println();
									
									User _actualUser = userDAO.findById(_idLog);
									long _actualId = _actualUser.getId();
									
									if(_actualUser != null && _actualUser.getPassword().equals(_passwordLog)) {
										
										do {
											System.out.println("Quale operazione vuoi effettuare?");
											System.out.println("1 - Visualizza i tuoi dati");
											System.out.println("2 - Sottoscrivi tessera");
											System.out.println("3 - Verifica validità tessera");
											System.out.println("4 - Rinnova tessera");
											System.out.println("5 - Rinnova abbonamento");
											System.out.println("6 - Elimina profilo\n");
											System.out.println("0 - Log Out e torna al menù");
											
											c3 = Integer.parseInt(scanner.nextLine());
											
											System.out.println();
											
											if(c3 != 0) {
												switch(c3) {
													case 1:
														System.out.println(_actualUser.toString());
														break;
													case 2:
														if(_actualUser.getPass() == null) {
															System.out.print("Dove stai creando la tessera: ");
															long _idReseller = Long.parseLong(scanner.nextLine());
															System.out.println();
															// pass creation
															Pass localPass = new Pass(LocalDate.now(), resellerDAO.findById(_idReseller), _actualUser);
															passDAO.savePass(localPass);
															System.out.println();
															// assign to user
															userDAO.assignPass(_actualUser.getId(), passDAO.findById(localPass.getId()));
															System.out.println();
														}else {
															if(_actualUser.getPass().getExpiryDatePass().getYear() == LocalDate.now().getYear())
																System.err.println("Tessera già presente e in corso di validità.");
															else
																System.err.println("Tessera già presente, ma scaduta. Effettuare rinnovo.");
														}
							
														break;
													case 3:
														if(_actualUser.getPass() != null)
															System.out.println((_actualUser.getPass().getExpiryDatePass().getYear() == LocalDate.now().getYear()) ? "Tessera Valida" : "Tessera scaduta");
														else
															System.err.println("Nessuna tessera registrata presso questo utente.");
														break;
													case 4:
														System.out.println();
														if(_actualUser.getPass() != null) {
															if(_actualUser.getPass().getExpiryDatePass().getYear() == LocalDate.now().getYear()){
																passDAO.renewalPass(passDAO.findPassByUserId(_actualUser));
															}else
																System.err.println("Tessera ancora valida, non è necessario rinnovare.");
														}else
															System.err.println("Nessuna tessera registrata presso questo utente.");
														
														break;
													case 5:
														if(_actualUser.getPass() != null) {
															if(_actualUser.getPass().getSubType() == null) {
																System.out.print("Scegliere il tipo di abbonamento da sottoscrivere (Weekly/Monthly): ");
																String sub = scanner.nextLine();
																passDAO.editSubscription(_actualUser.getPass().getId(), sub, LocalDate.now());
																System.out.println();
															}else {
																System.err.printf("La tessera presenta un abbonamento %s in corso.\n", _actualUser.getPass().getSubType().toString());
																System.err.println("Attendere la fine dell'abbonamento corrente per rinnovare.");
															}
														}else
															System.err.println("Nessuna tessera registrata presso questo utente.");
														
														
														break;
													case 6:
														userDAO.findByIdAndDelete(_actualUser.getId());
														c3 = 0;
														break;
													default:
														System.out.println("Comando non valido.");
															
												}
											
											}
											
											
											System.out.println();
											System.out.print("Premi invio per effettuare altre operazioni");
											scanner.nextLine();
											System.out.println("\n");											
											
										}while(c3 != 0);
										
									}else {
										System.err.println("ID o password errati.\n");
										System.out.print("Premi invio per tornare al menù");
										scanner.nextLine();
										System.out.println("\n");
									}
									
								break;
								
								case 3:
									// ---------------------------------------------------------------CREATE DAILY TICKET
									System.out.print("Dove stai creando il biglietto: ");
									long _idReseller = Long.parseLong(scanner.nextLine());
									System.out.println();
									dailyDAO.createDailyTicket(new Daily(LocalDate.now(), resellerDAO.findById(_idReseller)));
									System.out.println();
									System.out.println();
									System.out.print("Premi invio per effettuare altre operazioni");
									scanner.nextLine();
									System.out.println("\n");
									break;
								
								default:
									System.err.println("Comando non valido.");
									break;
							}
							
						}
						
						break;
				
					case 2:
						System.out.println("Scegli sezione");
						System.out.println("1 - Creazione");
						System.out.println("2 - Gestione\n");
						System.out.println("0 - Torna al menù");
						
						c4 = Integer.parseInt(scanner.nextLine());
						
						System.out.println();
						switch(c4) {
							case 1:
								System.out.println("Quale operazione vuoi effettuare?");
								System.out.println("1 - Crea reseller");
								System.out.println("2 - Crea mezzo");
								System.out.println("3 - Creare tratta");
								
								c5 = Integer.parseInt(scanner.nextLine());
								
								System.out.println();
								switch(c5) {
									case 1:
										int i1 = 0;

										System.out.println("Scegliere tipologia reseller");
										System.out.println("1 - Automatico");
										System.out.println("2 - Autorizzato");
										
										i1 = Integer.parseInt(scanner.nextLine());
										
										System.out.println();
										
										switch(i1) {
											case 1:
												System.out.print("Definire il nome del reseller: ");
												String _autoResellerName = scanner.nextLine();
												System.out.println();
												autoResellerDAO.save(new AutoReseller(_autoResellerName, AutoResellerStatus.ACTIVE));
												System.out.println();
												break;
											case 2:
												System.out.print("Definire il nome del reseller: ");
												String _resellerName = scanner.nextLine();
												System.out.println();
												authorizedResellerDAO.save(new AuthorizedReseller(_resellerName));
												System.out.println();
												break;
										}
										System.out.println();
										System.out.print("Premi invio per effettuare altre operazioni");
										scanner.nextLine();
										System.out.println("\n");	
										break;
									case 2:
										int i2 = 0;

										System.out.println("Scegliere tipologia di mezzo");
										System.out.println("1 - Bus");
										System.out.println("2 - Tram");
										
										i2 = Integer.parseInt(scanner.nextLine());
										
										System.out.println();
										
										switch(i2) {
											case 1:
												System.out.println();
												vehicleDAO.saveVehicle(new Vehicle(VehicleType.Bus));
												System.out.println();
												break;
											case 2:
												System.out.println();
												vehicleDAO.saveVehicle(new Vehicle(VehicleType.Tram));
												System.out.println();
												break;
										}
										System.out.println();
										System.out.print("Premi invio per effettuare altre operazioni");
										scanner.nextLine();
										System.out.println("\n");
										break;
									case 3:
										System.out.print("Inserire nome rotta: ");
										String routeName = scanner.nextLine();
										System.out.print("Inserire inizio tratta: ");
										String startName = scanner.nextLine();
										System.out.print("Inserire fine tratta: ");
										String terminalName = scanner.nextLine();
										System.out.println();
										routeDAO.saveRoute(new Route(routeName, startName, terminalName));
										System.out.println();
										break;
									default:
										System.err.println("Comando non valido.");
										break;
								}
								
								break;
							case 2:
								System.out.println("Quale operazione vuoi effettuare?");
								System.out.println("1 - Stampa numero totale biglietti e abbonamenti emessi di un reseller");
								System.out.println("2 - Stampa numero biglietti e abbonamenti di un reseller in un periodo");
								System.out.println("3 - Attiva/Disattiva un rivenditore automatico");
								System.out.println("4 - ");
								
								
								c5 = Integer.parseInt(scanner.nextLine());
								
								System.out.println();
								break;
							default:
								System.err.println("Comando non valido.");
								break;
						}
						break;
					
					default:
						System.err.println("Comando non valido.");
						System.out.println();
						System.out.print("Premi invio per tornare al menù");
						scanner.nextLine();
						System.out.println("\n");
						break;	
				}
			}
		}while(c1 != 0);
		
		em.close();
		entityManagerFactory.close();
		
		// scanner close
		scanner.close();
		
		System.out.println("Pogramma chiuso con successo.");
		
		
	}

}
