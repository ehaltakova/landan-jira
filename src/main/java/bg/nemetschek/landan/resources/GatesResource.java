package bg.nemetschek.landan.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import bg.nemetschek.landan.api.Gate;
import bg.nemetschek.landan.db.PlanesDAO;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/gates")
@Produces(MediaType.APPLICATION_JSON)
public class GatesResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(GatesResource.class);

	private final PlanesDAO dao;
	
	public GatesResource(PlanesDAO dao) {
		this.dao = dao;
	}
	
	@GET
    @Timed
    @UnitOfWork
    public List<Gate> getGates() {
		LOGGER.debug("get gates");
		List<Gate> gates = dao.getGates();
		return gates;
	}
}
