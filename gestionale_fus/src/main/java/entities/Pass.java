package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import enums.SubscriptionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Pass extends Ticket{
	
	private User userId;
	@Enumerated(EnumType.STRING)
	private SubscriptionType subType;
	private LocalDate expireDateSub;
	private LocalDate expiryDatePass; 
	
	public Pass(Long _ticketId, LocalDate _issueDate, Reseller _reseller, User _userId, SubscriptionType _subType, LocalDate _expiryDateSub, LocalDate _expireDatePass) {
		
		super(_ticketId, _issueDate, _reseller);
		this.userId = _userId;
		this.expireDateSub = _expiryDateSub;
		this.expiryDatePass = _expireDatePass;
	}
	
	@Override
	public String toString() {
		return "Ticket [ID Biglietto =" + getTicketId() + ", Data di emissione =" + getIssueDate() + ", Rivenditore = " + getReseller() + "ID User =" + userId + ", Tipo di abbonamento =" + subType + ", Data scadenza abbonamento =" + expireDateSub + ", Data scadenza Tessera =" + expiryDatePass;
	}
}
