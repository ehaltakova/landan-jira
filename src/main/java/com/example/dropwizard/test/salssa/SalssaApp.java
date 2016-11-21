package com.example.dropwizard.test.salssa;

import java.util.EnumSet;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.client.Client;

import org.apache.http.client.HttpClient;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;

import com.example.dropwizard.test.salssa.api.SlideAlbum;
import com.example.dropwizard.test.salssa.api.SlideAlbumFile;
import com.example.dropwizard.test.salssa.db.SimpleSlideAlbumDAO;
import com.example.dropwizard.test.salssa.db.SlideAlbumDAO;
import com.example.dropwizard.test.salssa.resources.IndexResource;
import com.example.dropwizard.test.salssa.resources.LoginResource;
import com.example.dropwizard.test.salssa.resources.SimpleSlideAlbumResource;
import com.example.dropwizard.test.salssa.resources.SimpleSlideAlbumsResource;
import com.example.dropwizard.test.salssa.resources.SlideAlbumResource;
import com.example.dropwizard.test.salssa.resources.SlideAlbumsResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.migrations.MigrationsBundle;

public class SalssaApp extends Application<SalssaAppConfiguration>{
	
	public static void main(String[] args) throws Exception {
		new SalssaApp().run(args);
	}
	
	@Override
	public String getName() {
		return "salssa";
	}
	
	@Override
	public void run(SalssaAppConfiguration configuration, Environment environment) throws Exception {
	
		// http client - managed, instrumented instance
		HttpClient httpClient = new HttpClientBuilder(environment).using(configuration.getHttpClientConfiguration()).build("salssaClient");

		// jersey http client - managed, instrumented instance
		JerseyClientConfiguration jerseyClientConfig = configuration.getJerseyClientConfiguration();
		final Client client = new JerseyClientBuilder(environment).using(jerseyClientConfig).build("salssaJerseyClient");
		
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
		environment.jersey().register(new LoginResource(client, configuration, httpClient));
		environment.jersey().register(new IndexResource());	
	}
	
	// create a new managed connection pool to the database, a health check for connectivity to the database, 
	// and a new SessionFactory instance for you to use in the DAO class
	HibernateBundle<SalssaAppConfiguration> hibernateBundle = new HibernateBundle<SalssaAppConfiguration>(SlideAlbum.class, SlideAlbumFile.class) { // mapped classes here, not in config!
		public DataSourceFactory getDataSourceFactory(SalssaAppConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}
	};
		
	@Override
	public void initialize(Bootstrap<SalssaAppConfiguration> bootstrap) {
		
		// static files serving
		bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
		
		// JDBI
		bootstrap.addBundle(new DBIExceptionsBundle()); // unwrap any thrown SQLException or DBIException instances automatically 		
		
		// migrations support
		bootstrap.addBundle(new MigrationsBundle<SalssaAppConfiguration>() {
			public DataSourceFactory getDataSourceFactory(SalssaAppConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});
		
		// Hibernate 		
		bootstrap.addBundle(hibernateBundle);
		
		// multi-part forms support 
		bootstrap.addBundle(new MultiPartBundle());
		
		// Mustache view templates 
		bootstrap.addBundle(new ViewBundle<SalssaAppConfiguration>() {
			@Override
			public Map<String, Map<String, String>> getViewConfiguration(SalssaAppConfiguration configuration) {
				return configuration.getViewRendererConfiguration();
			}
		});
	}
}
