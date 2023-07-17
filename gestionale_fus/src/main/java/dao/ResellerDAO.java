package dao;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import entities.Reseller;

public class ResellerDAO {

	private final EntityManager entityManager;

	public ResellerDAO(EntityManager _entityManager) {
		this.entityManager = _entityManager;
	}

	public list<Ticket> getResellerTicketsByTime(Reseller _reseller, LocalDate _startDate, LocalDate _endDate) {

	}

}
