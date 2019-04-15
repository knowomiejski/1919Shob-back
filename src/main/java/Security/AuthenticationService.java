package Security;

import Model.User;
import Persistance.UserDao;
import Utilities.PasswordUtility;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;

import java.util.Optional;

//import javax.inject.Singleton;


//@Singleton
public class AuthenticationService implements Authenticator<BasicCredentials, User> {

    UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    @UnitOfWork
    public Optional<User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        User user = userDao.selectByUsername(basicCredentials.getUsername());

        boolean samepasswd = PasswordUtility.checkHash(user.getPassword(), basicCredentials.getPassword());

        if (samepasswd) {
            if (user.getUserID() == 1) {
                String[] adminroles = {"Admin", "Guest"};
                user.setRoles(adminroles);
            }
            else {
                String[] normalroles = {"Guest"};
                user.setRoles(normalroles);
            }

            return Optional.of(user);
        }

        return Optional.empty();
    }

}
