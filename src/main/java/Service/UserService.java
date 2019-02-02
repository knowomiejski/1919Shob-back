package Service;

import Model.Address;
import Model.Registration;
import Model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserService {
    SessionFactory sessionFactory;

    public UserService(SessionFactory session) {
        this.sessionFactory = session;
    }

    public String registerUser(Registration registration) {
        User newUser = new User(
                registration.getEmail(),
                registration.getPasswd()
        );

        Address newAddress = new Address(
                registration.getProvince(),
                registration.getCity(),
                registration.getStreet(),
                registration.getHousenr()
        );

      //  newUser.setAddress(newAddress);

        Session registerSession = sessionFactory.openSession();

        registerSession.beginTransaction();
        registerSession.save(newUser);
        registerSession.getTransaction().commit();
        registerSession.close();

        return "Registration Complete";
    }
}
