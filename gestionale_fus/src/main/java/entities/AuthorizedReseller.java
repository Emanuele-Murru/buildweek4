package entities;

import javax.persistence.Entity;

@Entity
public class AuthorizedReseller extends Reseller {

	// - - - - - - - - - - - - - - - - - - - - attributes

	// - - - - - - - - - - - - - - - - - - - - constructors
	public AuthorizedReseller() {

	}

	public AuthorizedReseller(String _name) {
		super(_name);

	}

	// - - - - - - - - - - - - - - - - - - - - getters & setters
	public String getName() {
		return name;
	}

	public void setTitolo(String _name) {
		this.name = _name;
	}

	@Override
	public String toString() {
		return "AuthorizedReseller [" + name + "]";
	}
}
