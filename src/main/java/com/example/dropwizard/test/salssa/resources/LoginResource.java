package com.example.dropwizard.test.salssa.resources;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.http.client.HttpClient;

import com.codahale.metrics.annotation.Timed;
import com.example.dropwizard.test.salssa.SalssaAppConfiguration;
import com.example.dropwizard.test.salssa.api.UserContext;
import com.example.dropwizard.test.salssa.views.LoginView;

@Path("/login")
public class LoginResource {

	private final Client jerseyHttpClient;
	private final SalssaAppConfiguration configuration;

	public LoginResource(Client client, SalssaAppConfiguration configuration, HttpClient httpClient) {
		this.jerseyHttpClient = client;
		this.configuration = configuration;
	}

	@GET
    @Timed
    @Produces(MediaType.TEXT_HTML)
    public LoginView serveLoginPage() {
		return new LoginView("");
	}
	
	@POST
	@Timed
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON)
	public Object doLogin(@FormParam("username") String username, @FormParam("password") String password) { // use for more parameters MultivaluedMap<String, String> formParams)
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("username", username);
		data.put("password", password);

		WebTarget target = jerseyHttpClient.target(configuration.getAuthApiUrl()).path("/login");
		UserContext context = target.request()
			.accept(MediaType.APPLICATION_JSON_TYPE)
			.header("Content-Type", "application/x-www-form-urlencoded")
			.header("charset", "utf-8")
		.post(Entity.json(data), UserContext.class);	
		if(context == null) {
			return new LoginView("Invalid credentials. Please, try again.");
		} else {
			URI uri = UriBuilder.fromUri("/api/index").build();
	        return Response.seeOther(uri).build();
		}
	}
}
