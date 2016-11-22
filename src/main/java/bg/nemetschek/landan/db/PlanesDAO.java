package bg.nemetschek.landan.db;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import bg.nemetschek.landan.api.Gate;
import bg.nemetschek.landan.api.Plane;
import bg.nemetschek.landan.api.Status;
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
		return list(namedQuery("bg.nemetschek.landan.api.Plane.getPlanes"));
	}
	
	public void updateStatus(String key, String statusName) {
		System.err.println("KEY: " + key);
		Criteria criteria = criteria().add(Restrictions.eq("key", key));
		Plane plane = uniqueResult(criteria);
		System.err.println(plane);
		Status status = (Status) namedQuery("bg.nemetschek.landan.api.Status.getStatusbyName").setParameter("statusName", statusName).uniqueResult();
		plane.setStatus(status);
		persist(plane);
	}

	@SuppressWarnings("unchecked")
	public List<Gate> getGates() {
		return namedQuery("bg.nemetschek.landan.api.Gate.getGates").list();
	}
}
