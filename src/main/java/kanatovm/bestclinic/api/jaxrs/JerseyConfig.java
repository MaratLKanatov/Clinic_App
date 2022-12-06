package kanatovm.bestclinic.api.jaxrs;

import kanatovm.bestclinic.api.jaxrs.endpoints.SpecializationEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springdoc.webmvc.api.OpenApiResource;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * @author Kanatov Marat
 * @package_name kanatovm.bestclinic.api.rest.jaxrs
 * @date 05.12.2022
 */
@Component
@ApplicationPath(value = ApiConstants.API_PREFIX)
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        registerEndpoints();
    }

    private void registerEndpoints() {
        register(SpecializationEndpoint.class);
        packages("kanatovm.bestclinic.api.jaxrs");
        register(OpenApiResource.class);
    }
}