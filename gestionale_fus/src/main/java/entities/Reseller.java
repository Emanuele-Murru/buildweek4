package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Reseller {

	// - - - - - - - - - - - - - - - - - - - - attributes
	@Id
	@GeneratedValue
	private long id;

	protected String name;

	// @OneToMany(mappedBy = "reseller") // TO HAVE HERE
	// protected Set<Ticket> tickets;

	// @ManyToOne // TO HAVE IN TICKET
	// @JoinColumn(name = "ticket_id")
	// protected Reseller reseller;

	// - - - - - - - - - - - - - - - - - - - - constructors
	public Reseller() {

	}

	public Reseller(String _name) {
		this.name = _name;

	}

	// - - - - - - - - - - - - - - - - - - - - getters & setters
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String _name) {
		this.name = _name;
	}

	@Override
	public String toString() {
		return "Reseller [" + id + ", " + name + "]";
	}

}
