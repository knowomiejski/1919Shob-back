package Resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/profile")
@Produces(MediaType.TEXT_PLAIN)
public class UserResource {
    private final String uname;
    private final String defaultName;
    private final String passwd;
    private final String email;


    public UserResource(String uname, String defaultName, String passwd, String email) {
        this.uname = uname;
        this.defaultName = defaultName;
        this.passwd = passwd;
        this.email = email;
    }

    @GET
    public String getUname(@QueryParam("uname")Optional<String> uname){
        final String valUname = String.format(uname.orElse(defaultName));
        return valUname;
    }
}
