package bg.nemetschek.landan.resources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    @Path("/change")
    @UnitOfWork
    public void changeStatus(String payload) {
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = new HashMap<String, String>();
		try {
			map = mapper.readValue(payload, new TypeReference<Map<String, String>>(){});
			String key = map.get("key");
			String status = map.get("status");
			dao.updateStatus(key, status);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
