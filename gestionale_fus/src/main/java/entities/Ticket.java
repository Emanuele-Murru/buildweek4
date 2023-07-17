package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Ticket")
@Getter
@Setter
@NoArgsConstructor

public abstract class Ticket {

	@Id
	@GeneratedValue
	private Long ticketId;
	private LocalDate issueDate;
	private Reseller reseller;

	public Ticket(Long _ticketId, LocalDate _issueDate, Reseller _reseller) {
		this.ticketId = _ticketId;
		this.issueDate = _issueDate;
		this.reseller = _reseller;
	}
	
	@Override
	public String toString() {
		return "Ticket [ID Biglietto =" + ticketId + ", data di emissione =" + issueDate + ", Rivenditore = " + reseller;
	}
	
}
