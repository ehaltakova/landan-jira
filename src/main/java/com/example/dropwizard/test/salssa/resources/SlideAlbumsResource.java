package com.example.dropwizard.test.salssa.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.example.dropwizard.test.salssa.api.SlideAlbum;
import com.example.dropwizard.test.salssa.db.SlideAlbumDAO;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/slidealbums")
@Produces(MediaType.APPLICATION_JSON)
public class SlideAlbumsResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(SlideAlbumsResource.class);

	private final SlideAlbumDAO dao;
	
	public SlideAlbumsResource(SlideAlbumDAO dao) {
		this.dao = dao;
	}
	
	@GET
    @Timed
    @UnitOfWork
    public List<SlideAlbum> getSlideAlbums() {
		LOGGER.debug("get slidealbums");
		List<SlideAlbum> slidealbums = dao.getSlideAlbums();
		return slidealbums;
	}
}
