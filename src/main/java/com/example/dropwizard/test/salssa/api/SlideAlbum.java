package com.example.dropwizard.test.salssa.api;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Slide Album Hibernate class.
 * @author Elitza Haltakova
 *
 */
@Entity
@Table(name = "slidealbum")
@NamedQueries(
    {
        @NamedQuery(
            name = "com.example.dropwizard.test.salssa.api.SlideAlbum.getSlideAlbums",
            query = "SELECT s FROM SlideAlbum s"
        )
    }
)
public class SlideAlbum {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "title", nullable = false)
	private String title;
	@Column(name = "customer", nullable = false)
	private String customer;
	@Column(name = "modificationDate", nullable = false)
	private long modificationDate;
	@Column(name = "locked", nullable = true)
	private String locked;
	@Column(name = "svg", nullable = false)
	private String svg;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "slidealbum")
	private Set<SlideAlbumFile> files = new HashSet<SlideAlbumFile>();
	
	public SlideAlbum() {
		super();
	}
	
	public SlideAlbum(String title, String customer, long modificationDate, String locked, String svg, Set<SlideAlbumFile> files) {
		super();
		this.title = title;
		this.customer = customer;
		this.modificationDate = modificationDate;
		this.locked = locked;
		this.svg = svg;
		this.files = files;
	}
	
	@JsonIgnore
	public long getId() {
		return id;
	}
	
	@JsonProperty
	public String getTitle() {
		return title;
	}

	@JsonProperty
	public String getCustomer() {
		return customer;
	}

	@JsonProperty
	public long getModificationDate() {
		return modificationDate;
	}

	@JsonProperty("locked")
	public String lockedBy() {
		return locked;
	}

	@JsonProperty
	public String getSvg() {
		return svg;
	}
	
	@JsonProperty
	public Set<SlideAlbumFile> getFiles() {
		return files;
	}

	public String toString() {
		return "Slide album " + title + " [" + customer + "]"; 
	}
}
