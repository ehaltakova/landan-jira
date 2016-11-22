package bg.nemetschek.landan.api;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Status class
 */
@Entity
@Table(name = "gate")

@NamedQueries({ @NamedQuery(name = "bg.nemetschek.landan.api.Gate.getGates", query = "SELECT g FROM Gate g") })

public class Gate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "coordinates", nullable = false)
	private String coordinates;

	public Gate() {
		super();
	}

	@JsonProperty
	public long getId() {
		return this.id;
	}

	@JsonProperty
	public String getName() {
		return this.name;
	}

	@JsonProperty
	public String getCoordinates() {
		return this.coordinates;
	}
}