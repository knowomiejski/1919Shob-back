package Security;

import io.dropwizard.auth.UnauthorizedHandler;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UnAuthorizedHandler implements UnauthorizedHandler {

    @Override
    public Response buildResponse(String prefix, String realm) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .type(MediaType.TEXT_PLAIN_TYPE)
                .entity("Not authorized :(")
                .build();
    }
}
