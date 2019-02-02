package Resource;

import Model.Registration;
import Service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/settings")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserService service;

    public UserResource(UserService service) {
        this.service = service;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("Guest")
    public String registerUser(Registration registration){
        return service.registerUser(registration);
    }
}
