package bg.nemetschek.landan.api;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Plane Hibernate class.
 * @author Elitza Haltakova
 *
 */
@Entity
@Table(name = "plane")

//@NamedQueries(
//    {
//        @NamedQuery(
//            name = "bg.nemetschek.landan.api.Plane.getPlanes",
//            query = "SELECT p FROM plane p join status on p.status = status.id join gate on p.gate = gate.id "
//        )
//    }
//)
public class Plane {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "key", nullable = false)
	private String key;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "position", nullable = false)
	private String position;
	@Column(name = "heading", nullable = false)
	private long heading;
	@OneToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="gate", referencedColumnName="id")
	private Gate gate;
	@OneToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="status", referencedColumnName="id")
	private Status status;
	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private Type type;
	@Enumerated(EnumType.STRING)
	@Column(name = "priority", nullable = false)
	private Priority priority;
	@Enumerated(EnumType.STRING)
	@Column(name = "destination", nullable = false)
	private Destination destination;
	
	public Plane() {
		super();
	}
	
	@JsonProperty
	public long getId() {
		return id;
	}
	
	@JsonProperty
	public String getKey() {
		return key;
	}
	
	@JsonProperty
	public String getName() {
		return name;
	}

	@JsonProperty
	public String getPosition() {
		return position;
	}

	@JsonProperty
	public long getHeading() {
		return heading;
	}
	
	@JsonProperty
	public Priority getPriority() {
		return priority;
	}
	
	@JsonProperty
	public Type getType() {
		return type;
	}
	
	@JsonProperty
	public Gate getGate() {
		return gate;
	}
	
	@JsonProperty
	public Destination getDestination() {
		return destination;
	}
	
	@JsonProperty
	public Status getStatus() {
		return status;
	}

	/**
	 * Priority enum
	 */
	public enum Priority {	
		Normal, High, Urgent
	}
	
	/**
	 * Type enum
	 */
	public enum Type {
		Landing, Passing
	}
	
	/**
	 * Destination enum
	 */
	public enum Destination {
		Sofia, Paris, Rome, Berlin, Athens, Philadelphia
	}
	
	/**
	 * Status class
	 */
	@Entity
	@Table(name = "status")
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
	
	/**
	 * Status class
	 */
	@Entity
	@Table(name = "gate")
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
}
