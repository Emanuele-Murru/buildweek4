package dao;

import javax.persistence.EntityManager;

public class ResellerDAO {

	private final EntityManager entityManager;

	public ResellerDAO(EntityManager _entityManager) {
		this.entityManager = _entityManager;
	}

	// - - - - - - - - - - - - - - - - - - - - getResellerTicketsByTime
//	public list<Ticket> getResellerTicketsByTime(Reseller _reseller, LocalDate _startDate, LocalDate _endDate) {
//
//		TypedQuery<Ticket> query = entityManager.createQuery(
//				"SELECT t FROM Ticket t WHERE t.reseller = :_reseller AND t.issueDate >= :_startDate AND t.issueDat <= :_endDate",
//				Ticket.class).setParameter("_reseller", _reseller).setParameter("_startDate", _startDate)
//				.setParameter("_endDate", _endDate);
//
//		List<Ticket> results = query.getResultList();
//
//		return results;
//	}

}
