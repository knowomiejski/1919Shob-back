package Resource;

import Model.User;
import Persistance.UserDao;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserDao userDao;

    public UserResource(UserDao userDao) {
        this.userDao = userDao;
    }

    @POST
    @UnitOfWork
    @RolesAllowed("Admin")
    public User add(User user){
        System.out.println("got good request");
        return userDao.insert(user);
    }

    @GET
    @UnitOfWork
    @RolesAllowed("Admin")
    public User get(@Auth User user){
        return userDao.selectByUsername(user.getName());
    }

}
