package bg.nemetschek.landan;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;

import bg.nemetschek.landan.api.SlideAlbum;
import bg.nemetschek.landan.api.SlideAlbumFile;
import bg.nemetschek.landan.db.SimpleSlideAlbumDAO;
import bg.nemetschek.landan.db.SlideAlbumDAO;
import bg.nemetschek.landan.resources.SimpleSlideAlbumResource;
import bg.nemetschek.landan.resources.SimpleSlideAlbumsResource;
import bg.nemetschek.landan.resources.SlideAlbumResource;
import bg.nemetschek.landan.resources.SlideAlbumsResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.migrations.MigrationsBundle;

public class LandanApp extends Application<LandanAppConfiguration>{
	
	public static void main(String[] args) throws Exception {
		new LandanApp().run(args);
	}
	
	@Override
	public String getName() {
		return "salssa";
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
		final DBIFactory factory = new DBIFactory();
		// creates managed instrumented DBI instance + health check for connectivity
		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2"); 
		SimpleSlideAlbumDAO simpleDao = jdbi.onDemand(SimpleSlideAlbumDAO.class); // obtain and release connection automatically
		SlideAlbumDAO dao = new SlideAlbumDAO(hibernateBundle.getSessionFactory());
		
		// register resources
		environment.jersey().register(new SlideAlbumResource(dao));
		environment.jersey().register(new SlideAlbumsResource(dao));
		environment.jersey().register(new SimpleSlideAlbumResource(simpleDao));
		environment.jersey().register(new SimpleSlideAlbumsResource(simpleDao));
	}
	
	// create a new managed connection pool to the database, a health check for connectivity to the database, 
	// and a new SessionFactory instance for you to use in the DAO class
	HibernateBundle<LandanAppConfiguration> hibernateBundle = new HibernateBundle<LandanAppConfiguration>(SlideAlbum.class, SlideAlbumFile.class) { // mapped classes here, not in config!
		public DataSourceFactory getDataSourceFactory(LandanAppConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}
	};
		
	@Override
	public void initialize(Bootstrap<LandanAppConfiguration> bootstrap) {
		
		// static files serving
		bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
		
		// JDBI
		bootstrap.addBundle(new DBIExceptionsBundle()); // unwrap any thrown SQLException or DBIException instances automatically 		
		
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
