package bg.nemetschek.landan.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import bg.nemetschek.landan.api.Plane;
import bg.nemetschek.landan.db.PlanesDAO;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/slidealbums")
@Produces(MediaType.APPLICATION_JSON)
public class PlanesResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlanesResource.class);

	private final PlanesDAO dao;
	
	public PlanesResource(PlanesDAO dao) {
		this.dao = dao;
	}
	
	@GET
    @Timed
    @UnitOfWork
    public List<Plane> getSlideAlbums() {
		LOGGER.debug("get slidealbums");
		List<Plane> planes = dao.getPlanes();
		return planes;
	}
}
