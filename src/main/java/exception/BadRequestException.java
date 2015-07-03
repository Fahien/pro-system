package exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BadRequestException extends WebApplicationException {
	private static final long serialVersionUID = 7816096282166048990L;

	public BadRequestException(String message) {
        super(Response.status(Response.Status.BAD_REQUEST)
            .entity(message).type(MediaType.TEXT_PLAIN).build());
    }
}