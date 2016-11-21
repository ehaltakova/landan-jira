package bg.nemetschek.landan;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class LandanAppConfiguration extends Configuration { 

	@Valid
	@NotNull
	private DataSourceFactory database = new DataSourceFactory();
	@NotNull
	
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

    @JsonProperty
    public String getAuthApiUrl() {
        return authApiUrl;
    }

    @JsonProperty
    public void setAuthApiUrl(String authApiUrl) {
        this.authApiUrl = authApiUrl;
    }
}
