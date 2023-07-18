package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import enums.SubscriptionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Pass extends Ticket{
	
	@OneToOne(mappedBy = "pass")
	private User user;
	@Enumerated(EnumType.STRING)
	private SubscriptionType subType = null;
	private LocalDate expireDateSub = null;
	private LocalDate expiryDatePass; 
		
	public Pass(LocalDate _issueDate, Reseller _reseller, User user, LocalDate expiryDatePass) {
		super(_issueDate, _reseller);
		this.user = user;
		this.expiryDatePass = LocalDate.of(_issueDate.getYear(), 12, 31);
	}
	
	@Override
	public String toString() {
		return "Ticket [ID Biglietto =" + getId() + ", Data di emissione =" + getIssueDate() + ", Rivenditore = " + getReseller() + "ID User =" + user + ", Tipo di abbonamento =" + subType + ", Data scadenza abbonamento =" + expireDateSub + ", Data scadenza Tessera =" + expiryDatePass;
	}



	
}
