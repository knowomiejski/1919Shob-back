package Security;

import Model.User;
import io.dropwizard.auth.Authorizer;

public class AuthorizerService implements Authorizer<User> {
    @Override
    public boolean authorize(User user, String role) {
        return user.hasRole("Admin");
    }

}
