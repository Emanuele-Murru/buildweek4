package entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Route {

	@Id
	private String routeName;
	
	private String start;
	private String terminal;
	private int avgTime = 0;
	
	public Route(String routeName, String start, String terminal) {
		this.routeName = routeName;
		this.start = start;
		this.terminal = terminal;
	}

	@Override
	public String toString() {
		return "Route [routeName=" + routeName + ", start=" + start + ", terminal=" + terminal + ", avgTime=" + avgTime
				+ "]";
	}
	
}
