package Resource;


import Model.Address;
import Model.Registration;
import Model.User;
import Persistance.AddressDao;
import Persistance.UserDao;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/register")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegisterResource {
    private final UserDao userDao;
    private final AddressDao addressDao;

    public RegisterResource(UserDao userDao, AddressDao addressDao) {
        this.userDao = userDao;
        this.addressDao = addressDao;
    }

    @POST
    @UnitOfWork
    public User add(Registration registration) {
        User newUser = new User();
        newUser.setEmail(registration.getEmail());
        newUser.setPassword(registration.getPasswd());
        User registerdUser = userDao.insert(newUser);

        Address newAddress = new Address();
        newAddress.setAddressID(registerdUser.getUserID());
        newAddress.setProvince(registration.getProvince());
        newAddress.setCity(registration.getCity());
        newAddress.setStreet(registration.getStreet());
        newAddress.setHousenr(registration.getHousenr());
        addressDao.insert(newAddress);

        return registerdUser;
    }
}
