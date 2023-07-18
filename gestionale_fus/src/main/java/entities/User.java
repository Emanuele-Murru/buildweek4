package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	// - - - - - - - - - - - - - - - - - - - - attributes
	@Id
	@GeneratedValue
	private long id;

	protected String name;
	protected String surname;

	// @OneToOne // * * * * * * TO HAVE HERE
	// protected Pass pass;

	// @OneToOne(mappedBy = "pass") // * * * * * * TO HAVE IN PASS
	// protected User user;

	protected LocalDate birthDate;
	protected String birthPlace;

	// - - - - - - - - - - - - - - - - - - - - constructors
	public User() {

	}

	public User(String _name, String _surname, LocalDate _birthDate, String _birthPlace) {
		this.name = _name;
		this.surname = _surname;
		this.birthDate = _birthDate;
		this.birthPlace = _birthPlace;

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String _surname) {
		this.surname = _surname;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate _birthDate) {
		this.birthDate = _birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String _birthPlace) {
		this.birthPlace = _birthPlace;
	}

	@Override
	public String toString() {
		return "User [" + name + ", " + surname + ", " + birthDate + ", " + birthPlace + "]";
	}

}
