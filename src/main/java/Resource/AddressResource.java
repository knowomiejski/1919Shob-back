package Resource;

import Model.Address;
import Persistance.AddressDao;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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

    @POST
    @UnitOfWork
    public Address add(Address address) {
        System.out.println("got good request");
        return addressDao.insert(address);
    }
}
