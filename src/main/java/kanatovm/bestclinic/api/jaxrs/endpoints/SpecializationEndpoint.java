package kanatovm.bestclinic.api.jaxrs.endpoints;

import kanatovm.bestclinic.api.jaxrs.ApiConstants;
import kanatovm.bestclinic.model.entities.Specialization;
import kanatovm.bestclinic.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Kanatov Marat
 * @package_name kanatovm.bestclinic.api.rest.jaxrs
 * @date 05.12.2022
 */

@Component
@Path(value = ApiConstants.VERSION + SpecializationEndpoint.RESOURCE_KEY) // /api/public/v1/specialization
@Produces("application/json")
public class SpecializationEndpoint {

    public static final String RESOURCE_KEY = "/specialization";

    @Autowired
    private SpecializationRepository specializationRepository;

    @GET
    public List<Specialization> getAll() {
        return specializationRepository.findAll();
    }

    @Path("/{id}")
    @GET
    public Response getEntityById(@PathParam("id") Long id) {
        return Response.ok(specializationRepository.findById(id)).build();
    }

    @POST
    @Consumes("application/json")
    public Response createEntity(Specialization specialization) {
        return Response.ok().entity(specializationRepository.save(specialization)).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes("application/json")
    public Response updateEntity(@PathParam("id") Long id, Specialization specialization) {
        specialization.setId(id);
        Specialization specialization1 = specializationRepository.findById(id).orElseGet(null);
        if (specialization1 != null)
            return Response.ok().entity(specializationRepository.save(specialization)).build();
        else
            return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteConfiguration(@PathParam("id") Long id){
        Specialization specialization = specializationRepository.findById(id).orElseGet(null);
        if (specialization != null)
            specializationRepository.delete(specialization);
        else
            return Response.noContent().build();
        return Response.ok().entity("OK! Deleted by id: " + id).build();
    }
}
