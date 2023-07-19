package entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import enums.VehicleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Enumerated(EnumType.STRING)
	private VehicleType type;

	@OneToMany(mappedBy = "vehicle")
	private Set<Ticket> tickets;

	@OneToMany(mappedBy = "vehicle")
	private Set<VehicleStatusUpdate> history;

	private int capacity;

	@ManyToOne
	private Route route = null;

	public Vehicle(VehicleType type) {
		this.capacity = (type.equals(VehicleType.Bus)) ? 30 : 50;
		this.type = type;
	}

}
