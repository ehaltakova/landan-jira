package com.example.dropwizard.test.salssa.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.example.dropwizard.test.salssa.api.SimpleSlideAlbum;
import com.example.dropwizard.test.salssa.db.SimpleSlideAlbumDAO;

@Path("/simple/slidealbums")
@Produces(MediaType.APPLICATION_JSON)
public class SimpleSlideAlbumsResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSlideAlbumsResource.class);

	private final SimpleSlideAlbumDAO dao;
	
	public SimpleSlideAlbumsResource(SimpleSlideAlbumDAO dao) {
		this.dao = dao;
	}
	
	@GET
    @Timed
    public List<SimpleSlideAlbum> getSlideAlbums() {
		LOGGER.debug("get slidealbums");
		List<SimpleSlideAlbum> slidealbums = dao.getSlideAlbums();
		return slidealbums;
	}
}
