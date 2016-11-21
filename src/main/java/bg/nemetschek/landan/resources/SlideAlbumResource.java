package bg.nemetschek.landan.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import bg.nemetschek.landan.api.SlideAlbum;
import bg.nemetschek.landan.db.SlideAlbumDAO;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/slidealbum")
@Produces(MediaType.APPLICATION_JSON)
public class SlideAlbumResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(SlideAlbumResource.class);

	private final SlideAlbumDAO dao;
	
	public SlideAlbumResource(SlideAlbumDAO dao) {
		this.dao = dao;
	}
	
	@GET
    @Timed
    @UnitOfWork
    public SlideAlbum getSlideAlbum(@QueryParam("title") String title, @QueryParam("customer") String customer) {
		LOGGER.debug("get slidealbum by title: " + title);
		SlideAlbum slidealbum = dao.getSlideAlbum(title, customer);
		return slidealbum;
	}
	
	@GET
	@Path("{id}")
    @Timed
    @UnitOfWork
    public SlideAlbum getSlideAlbumById(@PathParam("id") long id) {
		LOGGER.debug("get slidealbum by id : " + id);
		SlideAlbum slidealbum = dao.getSlideAlbumById(id);
		LOGGER.debug("slidealbum: " + slidealbum);
		return slidealbum;
	}
	
	// NOTE: use of the @UnitOfWork annotation will do the following:
	// automatically open a session, begin a transaction, call corresponding dao method, 
	// commit the transaction, and finally close the session. If an exception is thrown, the transaction is rolled back.
}
