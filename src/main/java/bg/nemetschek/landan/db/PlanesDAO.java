package bg.nemetschek.landan.db;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import bg.nemetschek.landan.api.Plane;
import io.dropwizard.hibernate.AbstractDAO;

public class PlanesDAO extends AbstractDAO<Plane> {

	public PlanesDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Plane getPlane(String key) {
		Criteria criteria = criteria().add(Restrictions.eq("key", key));
		return uniqueResult(criteria);
	}
	
	public Plane getPlaneById(Long id) {
		return get(id);
	}

	public List<Plane> getPlanes() {
		//return list(namedQuery("bg.nemetschek.landan.api.Plane.getPlanes"));
		return new ArrayList<Plane>();
	}
}
