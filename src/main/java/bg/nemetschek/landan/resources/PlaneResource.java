package bg.nemetschek.landan.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import bg.nemetschek.landan.api.Plane;
import bg.nemetschek.landan.db.PlanesDAO;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/plane")
@Produces(MediaType.APPLICATION_JSON)
public class PlaneResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlaneResource.class);

	private final PlanesDAO dao;
	
	public PlaneResource(PlanesDAO dao) {
		this.dao = dao;
	}
	
	@GET
    @Timed
    @UnitOfWork
    public Plane getSlideAlbum(@QueryParam("key") String key) {
		LOGGER.debug("get plane by key: " + key);
		Plane plane = dao.getPlane(key);
		return plane;
	}
	
	@POST
    @Timed
    @UnitOfWork
    @Consumes("application/x-www-form-urlencoded")
    public void changeStatus(MultivaluedMap<String, String> formParams) {
		LOGGER.debug("update plane status: " + formParams.getFirst("status"));
		dao.updateStatus(formParams.getFirst("key"), formParams.getFirst("status"));
	}
	
	@GET
	@Path("{id}")
    @Timed
    @UnitOfWork
    public Plane getPlaneById(@PathParam("id") long id) {
		LOGGER.debug("get plane by id : " + id);
		Plane plane = dao.getPlaneById(id);
		LOGGER.debug("plane: " + plane);
		return plane;
	}
}
