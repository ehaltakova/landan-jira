package bg.nemetschek.landan;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import bg.nemetschek.landan.api.Plane;
import bg.nemetschek.landan.db.PlanesDAO;
import bg.nemetschek.landan.resources.PlaneResource;
import bg.nemetschek.landan.resources.PlanesResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;

public class LandanApp extends Application<LandanAppConfiguration>{
	
	public static void main(String[] args) throws Exception {
		new LandanApp().run(args);
	}
	
	@Override
	public String getName() {
		return "landan";
	}
	
	@Override
	public void run(LandanAppConfiguration configuration, Environment environment) throws Exception {
		
		// enable CORS
		final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
		cors.setInitParameter("allowedOrigins", "*");
		cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
		cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
		cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

		// db access
		PlanesDAO dao = new PlanesDAO(hibernateBundle.getSessionFactory());
		
		// register resources
		environment.jersey().register(new PlaneResource(dao));
		environment.jersey().register(new PlanesResource(dao));
	}
	
	// create a new managed connection pool to the database, a health check for connectivity to the database, 
	// and a new SessionFactory instance for you to use in the DAO class
	HibernateBundle<LandanAppConfiguration> hibernateBundle = new HibernateBundle<LandanAppConfiguration>(Plane.class, Plane.Status.class, Plane.Gate.class) { // mapped classes here, not in config!
		public DataSourceFactory getDataSourceFactory(LandanAppConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}
	};
		
	@Override
	public void initialize(Bootstrap<LandanAppConfiguration> bootstrap) {
				
		// migrations support
		bootstrap.addBundle(new MigrationsBundle<LandanAppConfiguration>() {
			public DataSourceFactory getDataSourceFactory(LandanAppConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});
		
		// Hibernate 		
		bootstrap.addBundle(hibernateBundle);
	}
}
