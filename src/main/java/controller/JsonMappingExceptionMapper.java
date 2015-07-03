package controller;
import org.codehaus.jackson.map.JsonMappingException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {
	private static final String MESSAGE = "Sorry, Sir. I don't understand your idioma.";

    @Override public Response toResponse(JsonMappingException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(MESSAGE).type(MediaType.TEXT_PLAIN).build();
    }
}