package kanatovm.bestclinic.api.jaxrs.endpoints;

import kanatovm.bestclinic.api.jaxrs.ApiConstants;
import kanatovm.bestclinic.model.entities.Specialization;
import kanatovm.bestclinic.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * @author Kanatov Marat
 * @package_name kanatovm.bestclinic.api.rest.jaxrs
 * @date 05.12.2022
 */

@Component
@Path(value = ApiConstants.VERSION) // /api/public/v1/specialization/all
public class SpecializationEndpoint {

    public static final String RESOURCE_KEY = "/specialization";

    @Autowired
    private SpecializationRepository specializationRepository;

    @GET
    @Path(value = SpecializationEndpoint.RESOURCE_KEY)
    @Produces("application/json")
    public List<Specialization> getAll() {
        return specializationRepository.findAll();
    }

}
