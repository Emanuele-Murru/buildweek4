package entities;

import javax.persistence.Entity;

import enums.AutoResellerStatus;

@Entity
public class AutoReseller extends Reseller {

	// - - - - - - - - - - - - - - - - - - - - attributes
	protected AutoResellerStatus status;

	// - - - - - - - - - - - - - - - - - - - - constructors
	public AutoReseller() {

	}

	public AutoReseller(String _name) {
		super(_name);

	}

	public AutoReseller(String _name, AutoResellerStatus _status) {
		super(_name);
		this.status = _status;

	}

	// - - - - - - - - - - - - - - - - - - - - getters & setters
	public String getName() {
		return name;
	}

	public void setTitolo(String _name) {
		this.name = _name;
	}

	public AutoResellerStatus getStatus() {
		return status;
	}

	public void setStatus(AutoResellerStatus _status) {
		this.status = _status;
	}

	@Override
	public String toString() {
		return "AutoReseller [" + name + ", " + status + "]";
	}
}
