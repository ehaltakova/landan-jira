package com.example.dropwizard.test.salssa.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.example.dropwizard.test.salssa.views.IndexView;

@Path("/index")
public class IndexResource {

	public IndexResource() {
	}

	@GET
    @Timed
    @Produces(MediaType.TEXT_HTML)
    public IndexView serveLoginPage() {
		return new IndexView("");
	}

}
