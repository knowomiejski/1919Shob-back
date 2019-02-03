package Security;

import Model.User;
import Persistance.UserDao;
import Utilities.PasswordUtility;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Singleton;
import java.util.Optional;


@Singleton
public class AuthenticationService implements Authenticator<BasicCredentials, User> {

    UserDao userDao;

    public AuthenticationService (UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    @UnitOfWork
    public Optional<User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        User user = userDao.selectByUsername(basicCredentials.getUsername());
        System.err.println("User Passwd: "+ user.getPassword());

        boolean samepasswd = PasswordUtility.checkHash(user.getPassword(), basicCredentials.getPassword());

        if(samepasswd){
            System.err.println("User ID: " + user.getUserID());
            if (user.getUserID() == 2){
                String[] adminroles = {"Admin", "Guest"};
                user.setRoles(adminroles);
            }
            else {
                String[] guestroles = {"Guest"};
                user.setRoles(guestroles);
                System.err.println("In here");
            }

            return Optional.of(user);
        }

        return Optional.empty();
    }
}
