package com.example.dropwizard.test.salssa.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.example.dropwizard.test.salssa.api.SimpleSlideAlbum;
import com.example.dropwizard.test.salssa.db.SimpleSlideAlbumDAO;

@Path("/simple/slidealbum")
@Produces(MediaType.APPLICATION_JSON)
public class SimpleSlideAlbumResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSlideAlbumResource.class);

	private final SimpleSlideAlbumDAO dao;
	
	public SimpleSlideAlbumResource(SimpleSlideAlbumDAO dao) {
		this.dao = dao;
	}
	
	@GET
    @Timed
    public SimpleSlideAlbum getSlideAlbum(@QueryParam("title") String title, @QueryParam("customer") String customer) {
		LOGGER.debug("get slidealbum by title: " + title);
		SimpleSlideAlbum slidealbum = dao.getSlideAlbum(title, customer);
		return slidealbum;
	}
	
	@GET
	@Path("{id}")
    @Timed
    public SimpleSlideAlbum getSlideAlbumById(@PathParam("id") long id) {
		LOGGER.debug("get slidealbum by id : " + id);
		SimpleSlideAlbum slidealbum = dao.getSlideAlbumById(id);
		return slidealbum;
	}
}
