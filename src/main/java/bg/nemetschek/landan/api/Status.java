package bg.nemetschek.landan.api;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Status class
 */
@Entity
@Table(name = "status")

@NamedQueries({ @NamedQuery(name = "bg.nemetschek.landan.api.Status.getStatusbyName", query = "SELECT s FROM Status s where s.name = :statusName") })

public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "name", nullable = false)
	private String name;

	public Status() {
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
}