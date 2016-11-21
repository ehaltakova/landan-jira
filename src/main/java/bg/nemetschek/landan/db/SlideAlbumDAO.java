package bg.nemetschek.landan.db;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import bg.nemetschek.landan.api.SlideAlbum;
import io.dropwizard.hibernate.AbstractDAO;

// AbstractDAO class contains type-safe wrappers for most of SessionFactory‘s common operations
public class SlideAlbumDAO extends AbstractDAO<SlideAlbum> {

	public SlideAlbumDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public SlideAlbum getSlideAlbum(String title, String customer) {
		Criteria criteria = criteria().add(Restrictions.eq("title", title)).add(Restrictions.eq("customer", customer));
		return uniqueResult(criteria);
	}
	
	public SlideAlbum getSlideAlbumById(Long id) {
		return get(id);
	}

	public List<SlideAlbum> getSlideAlbums() {
		return list(namedQuery("com.example.dropwizard.test.salssa.api.SlideAlbum.getSlideAlbums"));
	}
}
