package Resource;

import Model.Address;
import Model.User;
import Persistance.AddressDao;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/address")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AddressResource {

    private final AddressDao addressDao;

    public AddressResource(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @GET
    @UnitOfWork
    public Address get(@Auth User user) {
        return addressDao.findById(user.getUserID());
    }
}
