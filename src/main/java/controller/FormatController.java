package controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import service.FormatService;
import model.Format;

@Path("/format")
@Produces("application/json")
public class FormatController {
	FormatService formatService = FormatService.getInstance();

	@GET public List<Format> getFormats() {
		List<Format> formats = formatService.selectAll();
		return formats;
	}

	@POST public Format newFormat(Format format) {
		formatService.insert(format);
		return format;
	}

	@Path("{id}")
	@GET public Format selectById(@PathParam("id") long id) {
		Format format = formatService.selectById(id);
		return format;
	}

	@PUT public Format update(Format format) {
		format = formatService.update(format);
		return format;
	}

	@Path("{id}")
	@DELETE public boolean delete(@PathParam("id") long id) {
		return formatService.delete(id);
	}
}
