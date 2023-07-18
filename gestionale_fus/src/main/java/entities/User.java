package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
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

}
