package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import enums.VehicleStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VehicleStatusUpdate {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
//	@JoinColumn(name = "id")
	private Vehicle vehicle;
	
	private LocalDate start;
	private LocalDate end;
	private VehicleStatus maintenanceWork;
	
	public VehicleStatusUpdate(Vehicle vehicle, LocalDate start, LocalDate end, VehicleStatus maintenanceWork) {
		this.vehicle = vehicle;
		this.start = start;
		this.end = end;
		this.maintenanceWork = maintenanceWork;
	}

	@Override
	public String toString() {
		return "VehicleStatusUpdate [id=" + id + ", vehicleId=" + vehicle + ", start=" + start + ", end=" + end
				+ ", maintenanceWork=" + maintenanceWork + "]";
	}
	
	
	
	
}
