package com.example.dropwizard.test.salssa;

import java.util.Collections;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;

import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;

public class SalssaAppConfiguration extends Configuration { 

	@Valid
	@NotNull
	private DataSourceFactory database = new DataSourceFactory();
	@NotNull
    private Map<String, Map<String, String>> viewRendererConfiguration = Collections.emptyMap();
	@JsonProperty
	private HttpClientConfiguration httpClientConfig = new HttpClientConfiguration();
	@JsonProperty
	private JerseyClientConfiguration jerseyClientConfig = new JerseyClientConfiguration();
	
	@NotEmpty
	private String authApiUrl; 
	
    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
    
    @JsonProperty("httpClient")
    public HttpClientConfiguration getHttpClientConfiguration() {
		return httpClientConfig;
	}
    
    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return this.jerseyClientConfig;
    }
    
    @JsonProperty("httpClient")
    public void setHttpClientConfiguration(HttpClientConfiguration httpClientConfig) {
    	this.httpClientConfig = httpClientConfig;
	}
    
    @JsonProperty("viewRendererConfiguration")
    public Map<String, Map<String, String>> getViewRendererConfiguration() {
        return viewRendererConfiguration;
    }

    @JsonProperty("viewRendererConfiguration")
    public void setViewRendererConfiguration(Map<String, Map<String, String>> viewRendererConfiguration) {
        final ImmutableMap.Builder<String, Map<String, String>> builder = ImmutableMap.builder();
        for (Map.Entry<String, Map<String, String>> entry : viewRendererConfiguration.entrySet()) {
            builder.put(entry.getKey(), ImmutableMap.copyOf(entry.getValue()));
        }
        this.viewRendererConfiguration = builder.build();
    }

    @JsonProperty
    public String getAuthApiUrl() {
        return authApiUrl;
    }

    @JsonProperty
    public void setAuthApiUrl(String authApiUrl) {
        this.authApiUrl = authApiUrl;
    }
}
