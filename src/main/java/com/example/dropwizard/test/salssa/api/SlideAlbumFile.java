package com.example.dropwizard.test.salssa.api;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Slide Album File representation class
 * @author Elitza Haltakova
 *
 */
@Entity
@Table(name = "file")
public class SlideAlbumFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "ext", nullable = false)
	private String ext;
	@Column(name = "name", nullable = false)
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "slidealbum_id", nullable = false)
	private SlideAlbum slidealbum;
	
	public SlideAlbumFile() {
		super();
	}
	
	public SlideAlbumFile(String extension, String name, SlideAlbum slidealbum) {
		this.ext = extension;
		this.name = name;
		this.slidealbum = slidealbum;
	}
	
	@JsonIgnore
	public long getId() {
		return id;
	}

	@JsonProperty
	public String getExt() {
		return ext;
	}

	@JsonProperty
	public String getName() {
		return name;
	}
	
	@JsonIgnore
	public SlideAlbum getSlidealbum() {
		return slidealbum;
	}
}