package entities;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Daily extends Ticket{
	
	private LocalDate validationDate;
	private LocalDate expiryDate;
	
	public Daily(Long _ticketId, LocalDate _issueDate, Reseller _reseller, LocalDate _validationDate, LocalDate _expiryDate) {
		super(_ticketId, _issueDate, _reseller);
		this.validationDate = _validationDate;
		this.expiryDate = _expiryDate;
	}
	
	@Override
	public String toString() {
		return "Biglietto giornaliero [ID Biglietto =" + getTicketId() + ", Data di emissione =" + getIssueDate() + ", Rivenditore = " + getReseller() + ", Data obliterazione =" + validationDate + "Data di scadenza =" + expiryDate;
	}
	
}
