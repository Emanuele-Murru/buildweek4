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

	public Daily(LocalDate _issueDate, Reseller _reseller, LocalDate expiryDate) {
		super(_issueDate, _reseller);
		this.expiryDate = _issueDate.plusDays(2);
	}

	@Override
	public String toString() {
		return "Biglietto giornaliero [ID Biglietto =" + getId() + ", Data di emissione =" + getIssueDate()
				+ ", Rivenditore = " + getReseller() + ", Data obliterazione =" + obliterateDate + "Data di scadenza ="
				+ expiryDate;
	}



	

}
