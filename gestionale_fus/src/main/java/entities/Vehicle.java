package entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import enums.VehicleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class Vehicle {

	@Id
	@GeneratedValue
	private long id;
	
	@Enumerated(EnumType.STRING)
	private VehicleType type;
	
	private int capacity;
	private Set<Daily> daily;
	private Route route = null;
	
	
	
	public Vehicle(VehicleType type) {
		this.capacity = (type.equals(VehicleType.Bus)) ? 30 : 50;
		this.type = type;
	}
	
	
	
}
