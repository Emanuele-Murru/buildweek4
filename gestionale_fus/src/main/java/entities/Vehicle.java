package entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import enums.VehicleStatus;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Enumerated(EnumType.STRING)
	private VehicleType type;
	
	@OneToMany
	private Set<Daily> daily;
	
	@OneToMany(mappedBy = "vehicle")
	private Set<VehicleStatusUpdate> maintainenceHistory;

	private VehicleStatus vehicleStatus = VehicleStatus.Service;
	private int capacity;
	private Route route = null;
	
	
	public Vehicle(VehicleType type) {
		this.capacity = (type.equals(VehicleType.Bus)) ? 30 : 50;
		this.type = type;
	}


	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", type=" + type + ", daily=" + daily + ", vehicleStatus=" + vehicleStatus
				+ ", maintainenceHistory=" + maintainenceHistory + ", capacity=" + capacity + ", route=" + route + "]";
	}
	
	
	
}
