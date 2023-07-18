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

public class Daily extends Ticket {

	private LocalDate obliterateDate;
	private LocalDate expiryDate;

	public Daily(Long _ticketId, LocalDate _issueDate, Reseller _reseller, LocalDate _obliterateDate,
			LocalDate _expiryDate) {
		super(_ticketId, _issueDate, _reseller);
		this.obliterateDate = _obliterateDate;
		this.expiryDate = _expiryDate;
	}

	@Override
	public String toString() {
		return "Biglietto giornaliero [ID Biglietto =" + getTicketId() + ", Data di emissione =" + getIssueDate()
				+ ", Rivenditore = " + getReseller() + ", Data obliterazione =" + obliterateDate + "Data di scadenza ="
				+ expiryDate;
	}

}
